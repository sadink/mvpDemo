package com.ooo.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 同服务器接口交互中的数据仓库格式定义
 * Created by Created by lhfBoy 2016/7/4 0004.
 */
public class ResultBean<T> implements Serializable {

    public static final int RESULT_SUCCESS = 200;
    public boolean isValid;
    @SerializedName("error_code")
    private int code;
    @SerializedName("reason")
    private String msg;
    @SerializedName("result")
    private T repData;


    public ResultBean() {}

    public ResultBean(int code, String msg, T repData) {
        this.code = code;
        this.msg = msg;
        this.repData = repData;
    }

    public ResultBean(boolean isValid, String msg) {
        this.isValid = isValid;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getRepData() {
        return repData;
    }


    public void setRepData(T repData) {
        this.repData = repData;
    }


    public boolean isSuccessData() {
        return code == RESULT_SUCCESS && repData != null;
    }


    public boolean isSuccess() {
        return code == RESULT_SUCCESS ;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
