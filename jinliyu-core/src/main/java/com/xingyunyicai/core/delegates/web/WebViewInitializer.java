package com.xingyunyicai.core.delegates.web;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates.web
 * 文件名：   WebViewInitializer
 * 创建者：   DoDo
 * 创建时间： 2017/10/29 18:06
 * 描述：     包装WebView,使其更像原生
 */

public class WebViewInitializer {

    @SuppressLint("SetJavaScriptEnabled")
    public WebView initWebView(WebView webView) {

        //允许调试
        WebView.setWebContentsDebuggingEnabled(true);
        //不能横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        //不能纵向滚动
        webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);
        //屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        //初始化WebSettings
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//JS互调必须设置的
        final String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua + "dodo");
        //隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        //禁止缩放
        settings.setSupportZoom(false);
        //文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//禁止硬件加速，解决闪屏问题

        return webView;
    }

}
