package com.example.commonui.widget.jsbridge;

import android.net.Uri;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * WebChromeClient
 *
 * @author zhangshuai
 */
public class JsBridgeWebChromeClient extends WebChromeClient {

    private JsNativeCallback jsNativeCallBack;

    JsBridgeWebChromeClient(JsNativeCallback jsNativeCallBack) {
        this.jsNativeCallBack = jsNativeCallBack;
    }

    void setJsNativeCallBack(JsNativeCallback jsNativeCallBack) {
        this.jsNativeCallBack = jsNativeCallBack;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (jsNativeCallBack != null) {
            jsNativeCallBack.receiveTitleFromJs(title);
        }
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (jsNativeCallBack != null) {
            jsNativeCallBack.receiveProgressFromJs(newProgress);
        }
    }

    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        callback.invoke(origin, true, false);
    }

    // For Android  >= 5.0
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,
                                     FileChooserParams fileChooserParams) {
        callbackChooseFile(filePathCallback);
        return true;
    }

    // For Android < 3.0
    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        callbackChooseFile(valueCallback);
    }

    // For Android  >= 3.0
    public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType) {
        callbackChooseFile(valueCallback);
    }

    //For Android  >= 4.1
    public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
        callbackChooseFile(valueCallback);
    }

    private void callbackChooseFile(ValueCallback<?> filePathCallback) {
        if (jsNativeCallBack != null) {
            jsNativeCallBack.onShowFileChooser(filePathCallback);
        }
    }
}
