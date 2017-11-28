package com.xingyunyicai.core.delegates.web.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates.web.event
 * 文件名：   EventManager
 * 创建者：   DoDo
 * 创建时间： 2017/10/30 18:54
 * 描述：     TODO
 */

public class EventManager {

    private static final HashMap<String, BaseEvent> EVENTS = new HashMap<>();

    private EventManager() {
    }

    private static class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public final EventManager addEvent(@NonNull String name, @NonNull BaseEvent event) {
        EVENTS.put(name, event);
        return this;
    }

    public BaseEvent getEvent(@NonNull String name) {
        final BaseEvent event = EVENTS.get(name);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }


}
