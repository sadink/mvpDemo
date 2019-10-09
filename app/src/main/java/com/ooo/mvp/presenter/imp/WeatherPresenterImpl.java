package com.ooo.mvp.presenter.imp;

import android.content.Context;

import com.google.gson.Gson;
import com.ooo.mvp.config.BaseConfig;
import com.ooo.mvp.model.IWeatherQueryModel;
import com.ooo.mvp.model.entity.ResultBean;
import com.ooo.mvp.model.entity.Weather;
import com.ooo.mvp.model.imp.WeatherQueryModel;
import com.ooo.mvp.presenter.IWeatherPresenter;
import com.ooo.mvp.utils.net.ObserverNm;
import com.ooo.mvp.view.IProgressBarView;
import com.ooo.mvp.view.IWeatherView;
import com.socks.library.KLog;

/**
 * 天气查询业务功能接口
 * Created by lhfBoy on 2019/10/8 0008 9:22.
 */
public class WeatherPresenterImpl implements IWeatherPresenter {

    private Context context;
    private IWeatherView iWeatherView;
    private IProgressBarView iProgressBarView;
    private IWeatherQueryModel iWeatherQueryModel;


    public WeatherPresenterImpl(Context context, final IWeatherView iWeatherView, IProgressBarView iProgressBarView) {
        this.context = context;
        this.iWeatherView = iWeatherView;
        this.iProgressBarView = iProgressBarView;
        iWeatherQueryModel = new WeatherQueryModel();
    }

    @Override
    public void queryWeather(String city) {
        ResultBean resultBean = iWeatherQueryModel.onCheckCity(city);
        if (!resultBean.isValid) {
            iWeatherView.onWeatherResult(-1, "", resultBean.getMsg());
            return;
        }

        iWeatherQueryModel.onWeatherQuery(BaseConfig.URL_WEATHER, city, BaseConfig.KEY_WEATHER, new ObserverNm<ResultBean<Weather>>(context) {

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                KLog.e(BaseConfig.LOG, "onError:" + e.getMessage());
                iWeatherView.onWeatherResult(-1, "", e.getMessage());
            }

            @Override
            public void onNext(ResultBean<Weather> response) {
                super.onNext(response);
                if (response.getRepData() != null) {
                    StringBuilder result = new StringBuilder();
                    for (Weather.InnerWeather.NearestWeather nearestWeather : response.getRepData().getData().getWeather()) {
                        result.append("\n\n").append(new Gson().toJson(nearestWeather));
                    }
                    iWeatherView.onWeatherResult(200, result.toString(), "");
                } else {
                    iWeatherView.onClearCity();
                    iWeatherView.onWeatherResult(-1, "", response.getMsg());
                }
            }
        });
    }

}
