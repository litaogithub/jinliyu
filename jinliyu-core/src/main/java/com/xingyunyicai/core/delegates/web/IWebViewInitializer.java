package com.xingyunyicai.core.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates.web
 * 文件名：   IWebViewInitializer
 * 创建者：   DoDo
 * 创建时间： 2017/10/29 16:40
 * 描述：     WebView初始化 接口约束
 */

public interface IWebViewInitializer {

    WebView initWebView(WebView webView);

    WebViewClient initWebViewClient();

    WebChromeClient initWebChromeClient();

}
