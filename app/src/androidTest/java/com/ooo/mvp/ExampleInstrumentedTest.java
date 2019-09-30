package com.ooo.mvp;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.ooo.mvp.config.AppConfig;
import com.ooo.mvp.presenter.ILoginPresenter;
import com.ooo.mvp.presenter.imp.LoginPresenterImpl;
import com.ooo.mvp.view.ILoginView;

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

        ILoginPresenter iLoginPresenter = new LoginPresenterImpl(appContext, new ILoginView() {

            @Override
            public void onClearText() {

            }

            @Override
            public void onLoginResult(Boolean result, int code, String msg) {
                assertEquals(AppConfig.Log, "result：" + result + ",code：" + code + ",msg：" + msg);
            }

            @Override
            public void onSetProgressBarVisibility(int visibility) {

            }
        });

        iLoginPresenter.doLogin("张三", "123456");

    }
}
