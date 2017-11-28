package com.xingyunyicai.jinliyu.interceptor;

import com.xingyunyicai.core.net.interceptors.BaseInterceptor;
import com.xingyunyicai.core.util.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.net.interceptors
 * 文件名：   QihooInterceptor
 * 创建者：   DoDo
 * 创建时间： 2017/10/13 7:56
 * 描述：     TODO
 */

public class QihooInterceptor extends BaseInterceptor {

    private final String DEBUG_URL = "qihoo";

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))//直接返回json
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, String filePath) {
        final String json = FileUtil.getAssetsFile(filePath);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)) {
            if (url.contains("tui_jian")) {
                if (url.contains("tab")) {
                    return debugResponse(chain, "qihoo/tui_jian/tab.json");
                }
                if (url.contains("content_1")) {
                    return debugResponse(chain, "qihoo/tui_jian/content_1.json");
                }
                if (url.contains("content_2")) {
                    return debugResponse(chain, "qihoo/tui_jian/content_2.json");
                }
                if (url.contains("content_3")) {
                    return debugResponse(chain, "qihoo/tui_jian/content_3.json");
                }
                if (url.contains("content_4")) {
                    return debugResponse(chain, "qihoo/tui_jian/content_4.json");
                }
                if (url.contains("content_5")) {
                    return debugResponse(chain, "qihoo/tui_jian/content_5.json");
                }
                if (url.contains("content_6")) {
                    return debugResponse(chain, "qihoo/tui_jian/content_6.json");
                }
            }
            if (url.contains("pin_dao")) {
                if (url.contains("channel_labels")) {
                    return debugResponse(chain, "qihoo/pin_dao/channel_labels.json");
                }
            }
            if (url.contains("bao_dian")) {
                if (url.contains("tab")) {
                    return debugResponse(chain, "qihoo/bao_dian/tab.json");
                }
                if (url.contains("content_1")) {
                    return debugResponse(chain, "qihoo/bao_dian/content_1.json");
                }
                if (url.contains("content_2")) {
                    return debugResponse(chain, "qihoo/bao_dian/content_2.json");
                }
                if (url.contains("content_3")) {
                    return debugResponse(chain, "qihoo/bao_dian/content_3.json");
                }
            }
            if (url.contains("shi_ke")) {
                if (url.contains("content_1_1")) {
                    return debugResponse(chain, "qihoo/shi_ke/content_1_1.json");
                }
                if (url.contains("content_1_2")) {
                    return debugResponse(chain, "qihoo/shi_ke/content_1_2.json");
                }
                if (url.contains("content_2")) {
                    return debugResponse(chain, "qihoo/shi_ke/content_2.json");
                }
            }

        }
        return chain.proceed(chain.request());
    }
}
