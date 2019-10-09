package com.ooo.mvp.view;

/**
 * 天气查询功能视图接口
 * Created by Dongdd on 2019/10/8 0008 8:55.
 */
public interface IWeatherView {

    /**
     * 清除城市信息
     */
    void onClearCity();

    /**
     * 响应天气查询的结果
     *
     * @param code
     * @param weather
     * @param msg
     */
    void onWeatherResult(int code, String weather, String msg);

}
