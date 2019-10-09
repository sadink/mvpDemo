package com.ooo.mvp.config;

import android.app.Application;
import android.content.Context;

import com.ooo.mvp.BuildConfig;
import com.socks.library.KLog;

/**
 * Created by lhfBoy on 2019/10/8 0008 9:45.
 */
public class BaseApplication extends Application {

    public static Context context = null;

    /**
     * 定义全局单例模式的系统对象
     *
     * @return
     */
    public static synchronized Context context() {
        return (Context) context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        // 初始化日志
        KLog.init(BuildConfig.DEBUG);

    }
}
