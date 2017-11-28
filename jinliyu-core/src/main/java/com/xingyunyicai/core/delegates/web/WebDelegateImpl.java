package com.xingyunyicai.core.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xingyunyicai.core.delegates.web.chromeclient.WebChromeClientImpl;
import com.xingyunyicai.core.delegates.web.client.WebViewClientImpl;
import com.xingyunyicai.core.delegates.web.route.RouteKeys;
import com.xingyunyicai.core.delegates.web.route.Router;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates.web
 * 文件名：   WebDelegateImpl
 * 创建者：   DoDo
 * 创建时间： 2017/10/29 17:29
 * 描述：     WebDelegate 默认实现类
 */

public class WebDelegateImpl extends BaseWebDelegate {

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {



    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);

        //用原生的方式(delegate切换)模拟Web跳转并进行页面加载
        Router.getInstance().loadPage(this, getUrl());
    }


    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().initWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(getPageListener());
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
