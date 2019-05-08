package com.example.commonui.widget.jsbridge;

import android.webkit.WebView;

import com.github.lzyzsd.jsbridge.BridgeWebViewClient;

/**
 * 基于 JsBridge WebView Client
 *
 * @author zhangshuai
 */
public class JsBridgeWebViewClient extends BridgeWebViewClient {

    private JsNativeCallback jsNativeCallBack;

    JsBridgeWebViewClient(JsBridgeWebView webView, JsNativeCallback jsNativeCallBack) {
        super(webView);
        this.jsNativeCallBack = jsNativeCallBack;
    }

    void setJsNativeCallBack(JsNativeCallback jsNativeCallBack) {
        this.jsNativeCallBack = jsNativeCallBack;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        jsNativeCallBack.onPageFinished(url);
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        jsNativeCallBack.onError(errorCode, description);
    }
}
