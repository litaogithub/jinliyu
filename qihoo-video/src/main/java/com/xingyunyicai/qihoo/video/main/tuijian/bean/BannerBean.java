package com.xingyunyicai.qihoo.video.main.tuijian.bean;

import java.util.List;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian.bean
 * 文件名：   BannerBean
 * 创建者：   DoDo
 * 创建时间： 2017/10/12 0:37
 * 描述：     Banner轮播图
 */

public class BannerBean {

    private List<String> titles;
    private List<String> images;

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
