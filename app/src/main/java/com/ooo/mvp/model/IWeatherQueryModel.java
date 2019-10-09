package com.ooo.mvp.model;

import com.ooo.mvp.model.entity.ResultBean;

import io.reactivex.Observer;

/**
 * 天气查询业务功能接口
 * Created by lhfBoy on 2019/10/8 0008 10:13.
 */
public interface IWeatherQueryModel {

    /**
     * 校验录入的城市名称
     * @param city
     * @return
     */
    ResultBean onCheckCity(String city);

    /**
     * 查询天气信息
     * @param url
     * @param cityName
     * @param key
     * @param observer
     */
    void onWeatherQuery(String url, String cityName, String key, Observer observer);

}
