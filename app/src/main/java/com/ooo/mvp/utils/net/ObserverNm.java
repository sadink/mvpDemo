package com.ooo.mvp.utils.net;

import android.content.Context;

import com.ooo.mvp.config.BaseConfig;
import com.ooo.mvp.utils.DataFormatUtils;
import com.ooo.mvp.utils.widget.MyProgressCircleDialog;
import com.socks.library.KLog;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 自定义观察者处理
 * Created by Dongdd on 2019/4/21 23:17.
 */
public class ObserverNm<T> implements Observer<T> {

    private static CompositeDisposable compositeDisposable;


    protected ObserverNm(Context context) {
        if (compositeDisposable == null) {
            KLog.e(BaseConfig.LOG, "创建订阅管理器");
            compositeDisposable = new CompositeDisposable();
        }
        MyProgressCircleDialog.getInstance().setContext(context);
        MyProgressCircleDialog.getInstance().setOnProgressListener(new MyProgressCircleDialog.OnProgressListener() {
            @Override
            public void onProgressDismiss() {
                onDismiss();
            }
        });
    }

    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
        if (!MyProgressCircleDialog.getInstance().isShow()) {
            KLog.d(BaseConfig.LOG, "progress onStart():显示默认进度条");
            MyProgressCircleDialog.getInstance().showWaitPrompt();
        }
        // 开始网络请求
    }

    @Override
    public void onNext(T t) {
        if (MyProgressCircleDialog.getInstance().isShow()) {
            MyProgressCircleDialog.getInstance().dismiss();
            KLog.d(BaseConfig.LOG, "progress onNext(): dismiss");
        }
        KLog.e(BaseConfig.LOG, "onNext:" + DataFormatUtils.GsonString(t));

    }

    @Override
    public void onError(Throwable e) {
        if (MyProgressCircleDialog.getInstance().isShow()) {
            MyProgressCircleDialog.getInstance().dismiss(true);
            KLog.d(BaseConfig.LOG, "progress  onError(): dismiss");
        }
        KLog.e(BaseConfig.LOG, "onError:" + e.getMessage());
    }

    @Override
    public void onComplete() {
        if (MyProgressCircleDialog.getInstance().isShow()) {
            MyProgressCircleDialog.getInstance().dismiss();
            KLog.d(BaseConfig.LOG, "progress onCompleted(): dismiss");
        }
    }

    /**
     * 取消请求
     */
    public void onDismiss() {
        if (MyProgressCircleDialog.getInstance().isShow()) {
            MyProgressCircleDialog.getInstance().dismiss();
            KLog.d(BaseConfig.LOG, "progress onCompleted(): dismiss");
        }
        RRWebHelper.getInstance().getOkHttpClient().dispatcher().cancelAll();
        KLog.e(BaseConfig.LOG, "网络请求取消");
    }


    /**
     * 取消所有订阅
     */
    public static void unregisterSubscribe() {
        if (compositeDisposable != null) {
            KLog.e(BaseConfig.LOG, "unregisterSubscribe---取消全部订阅-前size：" + compositeDisposable.size());
            compositeDisposable.clear();
            KLog.e(BaseConfig.LOG, "unregisterSubscribe---取消全部订阅-后size：" + compositeDisposable.size());
        }
    }


}
