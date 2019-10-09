package com.ooo.mvp.config;

import com.ooo.mvp.model.entity.ResultBean;
import com.ooo.mvp.model.entity.Weather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by lhfBoy on 2019/10/8 0008 15:54.
 */
public interface ApiConfig {

    /**
     * 查询天气
     *
     * @param cityName
     * @param key
     * @return
     */
    @GET
    Observable<ResultBean<Weather>> queryWeather(@Url String url, @Query("cityname") String cityName, @Query("key") String key);


}
