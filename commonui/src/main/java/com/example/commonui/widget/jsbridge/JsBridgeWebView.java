package com.example.commonui.widget.jsbridge;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import java.util.List;

public class JsBridgeWebView extends AbstractWebView {

    private JsNativeCallback jsNativeCallback;
    private JsBridgeWebChromeClient jsBridgeWebChromeClient;
    private JsBridgeWebViewClient jsBridgeWebViewClient;

    public JsBridgeWebView(Context context) {
        super(context);
    }

    public JsBridgeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JsBridgeWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        jsNativeCallback = new JsNativeCallbackImpl();
        jsBridgeWebViewClient = new JsBridgeWebViewClient(this, jsNativeCallback);
        jsBridgeWebChromeClient = new JsBridgeWebChromeClient(jsNativeCallback);

        super.setWebChromeClient(jsBridgeWebChromeClient);
        super.setWebViewClient(jsBridgeWebViewClient);

        handleJsCallNative();
    }

    public void setJsNativeCallback(JsNativeCallback jsNativeCallback) {
        this.jsNativeCallback = jsNativeCallback;
        jsBridgeWebChromeClient.setJsNativeCallBack(jsNativeCallback);
        jsBridgeWebViewClient.setJsNativeCallBack(jsNativeCallback);
    }

    public String buildUserAgent() {
        return null;
    }

    private void handleJsCallNative() {
        registerHandlers(JsOptionsConstants.HANDLER_NAME_LIST, new INativeCallJsHandler() {
            @Override
            public void OnHandler(String handlerName, String responseData, CallBackFunction function) {
                switch (handlerName) {
                    case JsOptionsConstants.FUNC_CLOSE_PAGE:
                        jsNativeCallback.closePage();
                        break;
                    case JsOptionsConstants.FUNC_SUBMIT_REQUEST:
                        jsNativeCallback.submitRequestFromJs(responseData);
                        break;
                    case JsOptionsConstants.PAGE_LOAD_FINISH:
                        jsNativeCallback.onPageFinished(responseData);
                        break;
                }
            }
        });
    }

    /**
     * 将服务器响应的结果回调给前端
     */
    public void callResponseToJs(String responseJson) {
        this.callHandler(JsOptionsConstants.FUNC_CALL_RESPONSE, responseJson, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Log.e("pan", "数据回调前端成功 ：" + data);
            }
        });
    }

    /**
     * 批量注册 Js 调用 Native 函数
     */
    private void registerHandlers(List<String> handlerNames, INativeCallJsHandler handler) {
        for (String handlerName : handlerNames) {
            registerHandler(handlerName, handler);
        }
    }

    /**
     * 注册 Js 调用 Native 函数
     */
    private void registerHandler(final String handlerName, final INativeCallJsHandler handler) {
        this.registerHandler(handlerName, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (null != handler) {
                    handler.OnHandler(handlerName, data, function);
                }
            }
        });
    }


    @Override
    @Deprecated
    public void setWebViewClient(WebViewClient client) {
        if (client instanceof JsBridgeWebViewClient) {
            super.setWebViewClient(client);
        } else {
            throw new UnsupportedOperationException("do not use");
        }
    }

    @Override
    @Deprecated
    public void setWebChromeClient(WebChromeClient client) {
        if (client instanceof JsBridgeWebChromeClient) {
            super.setWebChromeClient(client);
        } else {
            throw new UnsupportedOperationException("do not use");
        }
    }
}
