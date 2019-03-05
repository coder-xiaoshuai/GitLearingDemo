package com.example.zhangshuai.test.interfaces;

public interface NetCallback<T> {

    void onSuccess(T data);

    void onFail(int errorCode, String message);

}
