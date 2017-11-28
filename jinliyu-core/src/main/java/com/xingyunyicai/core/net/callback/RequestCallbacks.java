package com.xingyunyicai.core.net.callback;

import android.os.Handler;

import com.xingyunyicai.core.app.ConfigKeys;
import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.net.loader.DoDoLoader;
import com.xingyunyicai.core.net.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.net.callback
 * 文件名：   RequestCallbacks
 * 创建者：   DoDo
 * 创建时间： 2017/8/31 4:04
 * 描述：     统一管理处理请求的回调
 */

public final class RequestCallbacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    //static 避免内存泄漏
    private static final Handler HANDLER = DoDo.getHandler();

    public RequestCallbacks(IRequest request,
                            ISuccess success,
                            IFailure failure,
                            IError error,
                            LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        final long delayed = DoDo.getConfiguration(ConfigKeys.LOADER_DELAYED);

        //隐藏进度条
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    DoDoLoader.stopLoading();
                }
            },delayed);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }
}
