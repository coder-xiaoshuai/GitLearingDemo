package com.example.commonui.widget.jsbridge;

import java.util.Arrays;
import java.util.List;

/**
 * 基于JsNativeCallback的操作
 */
public class JsOptionsConstants {

    // WebView 可见时调用
    static final String FUNC_ON_SHOW = "onShow";
    // WebView 不可见时调用
    static final String FUNC_ON_HIDE = "onHide";
    // 关闭当前页面
    static final String FUNC_CLOSE_PAGE = "closePage";
    // 通用的 JS 调用客户端发起请求服务
    static final String FUNC_SUBMIT_REQUEST = "submitRequest";
    // 通用的客户端回调服务器返回的结果给前端
    static final String FUNC_CALL_RESPONSE = "callResponse";

    //页面加载完成
    static final String PAGE_LOAD_FINISH = "pageLoadFinish";
    //打开新的 WebView
    static final String FUNC_OPEN_PAGE = "openPage";

    static final String FUN_PROGRESS = "progress";

    static final String FUN_RECEIVE_TITLE = "title";

    //receiveDataFromJs
    static final String FUN_RECEIVE_DATA = "submitData";

    static final List<String> HANDLER_NAME_LIST =
        Arrays.asList(FUNC_ON_SHOW, FUNC_ON_HIDE, FUNC_CLOSE_PAGE, FUNC_SUBMIT_REQUEST, FUNC_CALL_RESPONSE,
            PAGE_LOAD_FINISH, FUNC_OPEN_PAGE, FUN_RECEIVE_TITLE, FUN_PROGRESS, FUN_RECEIVE_DATA);
}
