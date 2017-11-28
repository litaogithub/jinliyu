package com.xingyunyicai.jinliyu.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mob.MobSDK;
import com.xingyunyicai.core.delegates.web.event.BaseEvent;
import com.xingyunyicai.core.util.log.DoDoLogger;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.test.demo.main.event
 * 文件名：   ShareEvent
 * 创建者：   DoDo
 * 创建时间： 2017/10/31 23:50
 * 描述：     TODO
 */

public class ShareEvent extends BaseEvent {

    @Override
    public String execute(String params) {

        DoDoLogger.d("ShareEvent", params);

        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");

//        ShareSDK.initSDK();
        MobSDK.init(getContext());
        final OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setText(text);
        oks.setImageUrl(imageUrl);
        oks.setUrl(url);
        oks.show(getContext());

        return null;
    }
}
