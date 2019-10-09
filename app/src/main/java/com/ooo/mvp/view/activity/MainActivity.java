package com.ooo.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ooo.mvp.R;
import com.ooo.mvp.utils.UIHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find View
        btnWeather = findViewById(R.id.btn_weather);

        // setListener
        btnWeather.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_weather:
                UIHelper.showWeatherActivity(this);
                break;
        }
    }
}
