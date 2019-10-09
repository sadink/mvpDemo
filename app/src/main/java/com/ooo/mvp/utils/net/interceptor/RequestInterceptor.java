package com.ooo.mvp.utils.net.interceptor;

import com.ooo.mvp.config.BaseConfig;
import com.ooo.mvp.utils.DataFormatUtils;
import com.socks.library.KLog;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求拦截处理
 * Created by lhfBoy on 2019/4/3 9:34.
 */
public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request()
                .newBuilder()
                .build();

        try {
            KLog.e(BaseConfig.LOG, "request:" + DataFormatUtils.GsonString(request));

            KLog.e(BaseConfig.LOG, "request-url:" + request.url().toString());
            KLog.e(BaseConfig.LOG, "request-scheme:" + request.url().scheme());
            KLog.e(BaseConfig.LOG, "request-host:" + request.url().host());
            KLog.e(BaseConfig.LOG, "request-encodedPath:" + request.url().encodedPath());
            KLog.e(BaseConfig.LOG, "request-port:" + request.url().port());
            KLog.e(BaseConfig.LOG, "request-method:" + request.method());


            KLog.e(BaseConfig.LOG, "request-query:" + request.url().query());

            if (request.body() != null) {
                KLog.e(BaseConfig.LOG, "request-body-contentType:" + request.body().contentType().toString());
                KLog.e(BaseConfig.LOG, "request-body-getClass:" + request.body().getClass().toString());
                KLog.e(BaseConfig.LOG, "request-body:" + DataFormatUtils.GsonString(request.body()));
                if (request.body() instanceof FormBody) {
                    FormBody formBody = (FormBody) request.body();
                    for (int i = 0; i < formBody.size(); i++) {
                        KLog.e(BaseConfig.LOG, "request-body-formBody:" + formBody.encodedName(i) + ":" + URLDecoder.decode(formBody.encodedValue(i), BaseConfig.ENCODING));
                    }
                }
            }

        } catch (Exception e) {
            KLog.e(BaseConfig.LOG, "网络拦截处理异常:" + e.getMessage());
        }
        return chain.proceed(request);
    }
}
