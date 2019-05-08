package com.example.commonui.widget.jsbridge;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;

import com.github.lzyzsd.jsbridge.BridgeWebView;

public abstract class AbstractWebView extends BridgeWebView {
    public AbstractWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AbstractWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public AbstractWebView(Context context) {
        super(context);
        init(context);
    }

    protected void init(Context context){
        WebSettings webSettings = getSettings();
        // 设置可以使用localStorage
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //可以请求定位
        webSettings.setGeolocationEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //解决字体显示不正常
        webSettings.setTextZoom(100);
        //清理缓存
        clearCache(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        //webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //设置加载进来的页面自适应手机屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // http://stackoverflow.com/questions/31509277/webview-images-are-not-showing-with-https
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        webSettings.setUserAgentString(buildUserAgent());
    }

    public abstract String buildUserAgent();
}
