package com.ooo.mvp.utils.net;

import android.app.Activity;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ooo.mvp.config.BaseApplication;
import com.ooo.mvp.config.BaseConfig;
import com.ooo.mvp.utils.DataFormatUtils;
import com.ooo.mvp.utils.net.interceptor.RequestInterceptor;
import com.ooo.mvp.utils.net.ssl.TrustAllCertsManager;
import com.ooo.mvp.utils.net.ssl.VerifyEverythingHostnameVerifier;
import com.socks.library.KLog;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Rxjava2.0、Retrofit2.0访问网络工具类
 * Created by Dongdd on 2019/4/21 23:02.
 */
public class RRWebHelper {

    /**
     * 定义网络请求参数
     */
    protected static final long CONNECT_TIMEOUT = 10;  // 链接超时10s
    protected static final int CONNECT_DOWNLOAD_TIMEOUT = 15; // 下载链接为15s

    private static Retrofit retrofit;
    private static PersistentCookieJar cookieJar;
    private static SSLContext sslContext;
    private OkHttpClient okHttpClient;
    public SharedPrefsCookiePersistor sharedPrefsCookiePersistor;

    private static class SingletonHolder {
        private static final RRWebHelper INSTANCE = new RRWebHelper();
    }

    public RRWebHelper() {
    }

    public static final RRWebHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 获取SSLSocketFactory对象
     *
     * @return
     */
    protected SSLSocketFactory getSSLSocketFactory() {
        //  创建信任管理器
        if (sslContext == null) {
            try {
                TrustManager[] trustManager = new TrustManager[]{new TrustAllCertsManager()};
                sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustManager, new SecureRandom());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sslContext.getSocketFactory();
    }

    /**
     * 获取cookieJar
     *
     * @return
     */
    protected PersistentCookieJar getCookieJar() {
        // 持久化Cookie处理
        if (cookieJar == null) {
            sharedPrefsCookiePersistor = new SharedPrefsCookiePersistor(BaseApplication.context());
            cookieJar = new PersistentCookieJar(new SetCookieCache(), sharedPrefsCookiePersistor);
        }
        KLog.e(BaseConfig.LOG, "getCookieJar()—cookies:" + DataFormatUtils.GsonString(cookieJar));
        return cookieJar;
    }

    /**
     * 获取OkHttp客户端对象
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {

            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new RequestInterceptor())
                    .sslSocketFactory(getSSLSocketFactory())
                    .hostnameVerifier(new VerifyEverythingHostnameVerifier())
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .cookieJar(getCookieJar())
                    //其他配置
                    .build();
        }
        return okHttpClient;
    }


    /**
     * 获取Retrofit对象
     *
     * @return
     */
    private static Retrofit getRetrofit() {
        return getRetrofit(BaseConfig.SERVICE);
    }

    /**
     * 获取Retrofit对象
     *
     * @param baseUrl
     * @return
     */
    private static Retrofit getRetrofit(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(RRWebHelper.getInstance().getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * 获取网络请求接口实例
     *
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T getApi(Class<T> service) {
        return getRetrofit().create(service);
    }

    /**
     * 观察者订阅被观察者
     *
     * @param observable
     * @param observer
     */
    public static void RequestServer(Activity activity,Observable observable, Observer observer) {
        // 判断网络是否正常
        boolean netState = NetUtils.checkNetwork(activity);
        if (netState) {
            try {
                if (observer != null && observable != null) {
                    observable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(observer);
                }
            } catch (Exception e) {
                e.printStackTrace();
                KLog.e(BaseConfig.LOG, "网络请求异常：" + e.getMessage());
            }
        }
    }


    public static void RequestServerUnMsg(Observable observable, Observer observer) {
        // 判断网络是否正常
        boolean netState = NetUtils.isNetWorkAvailable(BaseApplication.context());
        if (netState) {
            try {
                if (observer != null && observable != null) {
                    observable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(observer);
                }
            } catch (Exception e) {
                e.printStackTrace();
                KLog.e(BaseConfig.LOG, "网络请求异常：" + e.getMessage());
            }
        }
    }

}
