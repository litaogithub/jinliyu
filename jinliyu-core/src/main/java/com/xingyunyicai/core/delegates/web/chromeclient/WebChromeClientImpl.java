package com.xingyunyicai.core.delegates.web.chromeclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates.web.chromeclient
 * 文件名：   WebChromeClientImpl
 * 创建者：   DoDo
 * 创建时间： 2017/10/29 18:15
 * 描述：     TODO
 */

public class WebChromeClientImpl extends WebChromeClient {

    /**
     * 拦截对话框，可以弹出自己的对话框
     *
     * @param view
     * @param url
     * @param message
     * @param result
     * @return
     */
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
