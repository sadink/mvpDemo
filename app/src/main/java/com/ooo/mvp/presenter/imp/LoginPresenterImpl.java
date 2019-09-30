package com.ooo.mvp.presenter.imp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.ooo.mvp.R;
import com.ooo.mvp.presenter.ILoginPresenter;
import com.ooo.mvp.view.ILoginView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by Dongdd on 2019/9/30 0030 9:49.
 */
public class LoginPresenterImpl implements ILoginPresenter {

    private Context context;
    private ILoginView iLoginView;

    public LoginPresenterImpl(Context context, ILoginView iLoginView) {
        this.context = context;
        this.iLoginView = iLoginView;
    }

    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLogin(String userName, String pwd) {
        if (TextUtils.isEmpty(userName)) {
            iLoginView.onLoginResult(false, 0, context.getResources().getString(R.string.loginUserName_null));
            return;
        } else if (TextUtils.isEmpty(pwd)) {
            iLoginView.onLoginResult(false, 0, context.getResources().getString(R.string.loginPwd_null));
            return;
        } else if (!(userName.length() >= 8)) {
            iLoginView.onLoginResult(false, 0, context.getResources().getString(R.string.loginUserName_error) + ",长度不够8位");
            return;
        } else if (!(pwd.length() >= 6)) {
            iLoginView.onLoginResult(false, 0, context.getResources().getString(R.string.loginPwd_error) + ",长度不够6位");
            return;
        }

        iLoginView.onSetProgressBarVisibility(View.VISIBLE);
        Observable.timer(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        iLoginView.onSetProgressBarVisibility(View.INVISIBLE);
                        iLoginView.onLoginResult(true, 200, "登录成功");
                    }
                });

    }

}
