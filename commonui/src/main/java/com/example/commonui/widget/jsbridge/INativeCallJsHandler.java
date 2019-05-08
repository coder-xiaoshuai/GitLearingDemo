package com.example.commonui.widget.jsbridge;

import com.github.lzyzsd.jsbridge.CallBackFunction;

public interface INativeCallJsHandler {
    void OnHandler(String handlerName, String responseData, CallBackFunction function);
}
