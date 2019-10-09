package com.ooo.mvp.presenter;

/**
 * 天气查询业务功能接口
 * Created by Dongdd on 2019/10/8 0008 9:20.
 */
public interface IWeatherPresenter {

    /**
     * 查询天气信息
     * @param city
     */
    void queryWeather(String city);

}
