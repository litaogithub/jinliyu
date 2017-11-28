package com.xingyunyicai.core.delegates.web.event;

import com.xingyunyicai.core.util.log.DoDoLogger;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates.web.event
 * 文件名：   UndefineEvent
 * 创建者：   DoDo
 * 创建时间： 2017/10/30 19:14
 * 描述：     TODO
 */

public class UndefineEvent extends BaseEvent {

    @Override
    public String execute(String params) {
        DoDoLogger.d("UndefineEvent", params);
        return null;
    }
}
