package com.ooo.mvp.view;

/**
 * 登录视图功能接口
 * Created by lhfBoy on 2019/9/30 0030 9:05.
 */
public interface ILoginView {

    /**
     * 重置登录信息
     */
    void onClearText();

    /**
     * 响应登录结果
     *
     * @param result
     * @param code
     * @param msg
     */
    void onLoginResult(Boolean result, int code, String msg);

    /**
     * 设置进度条交互效果
     *
     * @param visibility
     */
    void onSetProgressBarVisibility(int visibility);
}
