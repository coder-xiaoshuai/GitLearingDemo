package com.example.commonui.widget.jsbridge;

import android.webkit.ValueCallback;

/**
 * js 调用 原生方法回调
 */
public interface JsNativeCallback {
    /**
     * webiew显示的时候调用
     */
    void onShow(String data);

    /**
     * webView隐藏的时候调用
     */
    void onHide(String data);

    void onPageFinished(String url);

    void onPageLoadFinish(String data);

    /**
     * js调用原生方法去发送请求 需要将请求相关的参数封装成json放到data中
     */
    void submitRequestFromJs(String data);

    /**
     * 接收h5标题
     */
    void receiveTitleFromJs(String title);

    /**
     * 从js接收通用数据
     */
    void receiveDataFromJs(String data);

    /**
     * 从js接收进度
     */
    void receiveProgressFromJs(int progress);

    /**
     * js 通知原生关闭页面
     */
    void closePage();

    /**
     * js页面发生错误
     */
    void onError(int errorCode,String errorMsg);

    /**
     * 通知原生页面打开新的页面
     */
    void openNewPage(String data);

    void onShowFileChooser(ValueCallback<?> filePathCallback);
}
