package com.xingyunyicai.qihoo.video.main.tuijian.event;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian.event
 * 文件名：   SearchHotwordEvent
 * 创建者：   DoDo
 * 创建时间： 2017/10/11 23:30
 * 描述：     TODO
 */

public class SearchHotwordEvent {

    private String searchHotword = null;

    public SearchHotwordEvent(String searchHotword) {
        this.searchHotword = searchHotword;
    }

    public String getSearchHotword() {
        return searchHotword;
    }
}
