package com.xingyunyicai.core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.delegates.web.BaseWebDelegate;
import com.xingyunyicai.core.delegates.web.IPageLoadListener;
import com.xingyunyicai.core.delegates.web.route.Router;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates.web.client
 * 文件名：   WebViewClientImpl
 * 创建者：   DoDo
 * 创建时间： 2017/10/29 17:41
 * 描述：     TODO
 */

public class WebViewClientImpl extends WebViewClient {

    private final BaseWebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = DoDo.getHandler();

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(BaseWebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    /**
     * 拦截浏览器的跳转
     *
     * 为了考虑到兼容性，用了过时的方法
     *
     * @param view
     * @param url
     * @return true=拦截
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
    }
}
