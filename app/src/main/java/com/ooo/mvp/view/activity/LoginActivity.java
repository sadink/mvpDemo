package com.ooo.mvp.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ooo.mvp.R;
import com.ooo.mvp.config.AppConfig;
import com.ooo.mvp.presenter.ILoginPresenter;
import com.ooo.mvp.presenter.imp.LoginPresenterImpl;
import com.ooo.mvp.utils.UIHelper;
import com.ooo.mvp.view.ILoginView;

/**
 * 登录模块
 * Created by lhfBoy on 2019/9/30 0030 8:57.
 */
public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private EditText edtUserName;
    private EditText edtPwd;
    private ProgressBar progresLogin;
    private Button btnLogin;
    private Button btnReset;
    private ILoginPresenter iLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // find View
        edtUserName = findViewById(R.id.edt_userName);
        edtPwd = findViewById(R.id.edt_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnReset = findViewById(R.id.btn_reset);
        progresLogin = findViewById(R.id.progressLogin);

        // set listener
        btnLogin.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        // init
        iLoginPresenter = new LoginPresenterImpl(this, this);
        onSetProgressBarVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_reset:
                iLoginPresenter.clear();
                break;
            case R.id.btn_login:
                btnLogin.setEnabled(false);
                btnReset.setEnabled(false);
                iLoginPresenter.doLogin(edtUserName.getText().toString(), edtPwd.getText().toString());
                break;
        }

    }


    @Override
    public void onClearText() {
        edtUserName.setText("");
        edtPwd.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, int code, String msg) {
        btnLogin.setEnabled(true);
        btnReset.setEnabled(true);
        Log.e(AppConfig.Log, "登录结果：" + result + ",code:" + code + ",msg：" + msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        if (result) {
            UIHelper.showMainActivity(this);
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progresLogin.setVisibility(visibility);
    }


}
