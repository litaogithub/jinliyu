package com.xingyunyicai.jinliyu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.core.net.RestClient;
import com.xingyunyicai.core.net.callback.IError;
import com.xingyunyicai.core.net.callback.IFailure;
import com.xingyunyicai.core.net.callback.ISuccess;
import com.xingyunyicai.core.util.log.DoDoLogger;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodocomic.example
 * 文件名：   ExampleDelegate
 * 创建者：   DoDo
 * 创建时间： 2017/8/30 16:41
 * 描述：     TODO
 */

public class ExampleDelegate extends DoDoDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com/")
//                .url("intercept")
//                .url("qihoo/tui_jian/tab.json")//修改模拟器wifi代理为：10.0.3.2/本机IPv4地址
//                .url("http://127.0.0.1/index")
                .loader(getContext())//加载进度条
//                .param("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        DoDoLogger.d("AHAHAH",response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "failure", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "error: " + msg, Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }
}
