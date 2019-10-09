package com.ooo.mvp.model.imp;

import android.text.TextUtils;

import com.ooo.mvp.config.ApiConfig;
import com.ooo.mvp.config.BaseConfig;
import com.ooo.mvp.model.IWeatherQueryModel;
import com.ooo.mvp.model.entity.ResultBean;
import com.ooo.mvp.utils.net.RRWebHelper;
import com.socks.library.KLog;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 天气查询业务功能接口view_dialog
 * Created by Dongdd on 2019/10/8 0008 11:02.
 */
public class WeatherQueryModel implements IWeatherQueryModel {


    @Override
    public ResultBean onCheckCity(String city) {
        ResultBean resultBean = new ResultBean(false, "");
        if (TextUtils.isEmpty(city)) {
            resultBean.setMsg("城市名称不能为空");
            return resultBean;
        }
        resultBean.setValid(true);
        return resultBean;
    }

    @Override
    public void onWeatherQuery(String url, String cityName, String key, Observer observer) {

        KLog.e(BaseConfig.LOG,"url:" + url);
        KLog.e(BaseConfig.LOG,"cityName:" + cityName);
        KLog.e(BaseConfig.LOG,"key:" + key);
        RRWebHelper.getApi(ApiConfig.class)
                .queryWeather(url, cityName, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
