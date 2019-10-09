package com.ooo.mvp.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ooo.mvp.R;
import com.ooo.mvp.config.BaseConfig;
import com.ooo.mvp.presenter.IWeatherPresenter;
import com.ooo.mvp.presenter.imp.WeatherPresenterImpl;
import com.ooo.mvp.view.IProgressBarView;
import com.ooo.mvp.view.IWeatherView;

/**
 * 天气查询视图
 * Created by Dongdd on 2019/10/8 0008 8:47.
 */
public class WeatherActivity extends AppCompatActivity implements IWeatherView, IProgressBarView, View.OnClickListener {

    private EditText edtCity;
    private TextView tvWeatherResult;
    private Button btnClearCity;
    private Button btnQueryWeather;
    private ProgressBar progressQueryWeather;
    private IWeatherPresenter iWeatherPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // find View
        edtCity = findViewById(R.id.edt_city);
        btnQueryWeather = findViewById(R.id.btn_queryWeather);
        btnClearCity = findViewById(R.id.btn_clearCity);
        tvWeatherResult = findViewById(R.id.tv_weatherResult);
        progressQueryWeather = findViewById(R.id.progressQueryWeather);

        // setListener
        btnQueryWeather.setOnClickListener(this);
        btnClearCity.setOnClickListener(this);

        // init
        iWeatherPresenter = new WeatherPresenterImpl(this, this,this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_queryWeather:
                // 禁用按钮状态
                btnQueryWeather.setEnabled(false);
                btnClearCity.setEnabled(false);

                // 查询天气
                iWeatherPresenter.queryWeather(edtCity.getText().toString().trim());
                break;
            case R.id.btn_clearCity:
                onClearCity();
                break;
        }
    }

    @Override
    public void onClearCity() {
        edtCity.setText("");
        tvWeatherResult.setText("");
    }

    @Override
    public void onWeatherResult(int code, String weather, String msg) {
        Log.e(BaseConfig.LOG, "天气结果：" + weather + ",code:" + code + ",msg：" + msg);

        // 恢复按钮状态
        btnQueryWeather.setEnabled(true);
        btnClearCity.setEnabled(true);

        // 显示天气查询结果
        if (code == 200) {
            tvWeatherResult.setText(weather);
        } else {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressQueryWeather.setVisibility(visibility);
    }


}
