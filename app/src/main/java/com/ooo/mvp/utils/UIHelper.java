package com.ooo.mvp.utils;

import android.app.Activity;
import android.content.Intent;

import com.ooo.mvp.view.activity.MainActivity;
import com.ooo.mvp.view.activity.WeatherActivity;

/**
 * 视图页面调整
 * Created by lhfBoy on 2019/9/30 0030 10:36.
 */
public class UIHelper {

    /**
     * 打开首页
     *
     * @param activity
     */
    public static void showMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    /**
     * 天气查询视图
     * @param activity
     */
    public static void showWeatherActivity(Activity activity) {
        Intent intent = new Intent(activity, WeatherActivity.class);
        activity.startActivity(intent);
    }
}
