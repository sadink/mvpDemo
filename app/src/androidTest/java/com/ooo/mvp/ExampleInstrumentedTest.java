package com.ooo.mvp;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.ooo.mvp.config.BaseConfig;
import com.ooo.mvp.presenter.ILoginPresenter;
import com.ooo.mvp.presenter.IWeatherPresenter;
import com.ooo.mvp.presenter.imp.LoginPresenterImpl;
import com.ooo.mvp.presenter.imp.WeatherPresenterImpl;
import com.ooo.mvp.view.ILoginView;
import com.ooo.mvp.view.IProgressBarView;
import com.ooo.mvp.view.IWeatherView;
import com.socks.library.KLog;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.ooo.mvp", appContext.getPackageName());

//        IWeatherPresenter iLoginPresenter = new WeatherPresenterImpl(appContext, new IWeatherView() {
//            @Override
//            public void onClearCity() {
//
//            }
//
//            @Override
//            public void onWeatherResult(int code, String weather, String msg) {
//                KLog.e(BaseConfig.LOG,"code:" +code+ ",\nweather:" + weather+"");
//            }
//        }, new IProgressBarView() {
//            @Override
//            public void onSetProgressBarVisibility(int visibility) {
//
//            }
//        });

//        iLoginPresenter.queryWeather("西安");

    }
}
