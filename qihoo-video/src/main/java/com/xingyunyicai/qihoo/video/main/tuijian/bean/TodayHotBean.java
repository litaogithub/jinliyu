package com.xingyunyicai.qihoo.video.main.tuijian.bean;

import java.util.List;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian.bean
 * 文件名：   TodayHotBean
 * 创建者：   DoDo
 * 创建时间： 2017/10/12 17:54
 * 描述：     今日热点
 */

public class TodayHotBean {

    private String tagCover;
    private List<String> titles;

    public String getTagCover() {
        return tagCover;
    }

    public void setTagCover(String tagCover) {
        this.tagCover = tagCover;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
}
