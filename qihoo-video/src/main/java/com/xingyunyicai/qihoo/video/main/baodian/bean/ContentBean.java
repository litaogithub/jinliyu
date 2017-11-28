package com.xingyunyicai.qihoo.video.main.baodian.bean;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.baodian.bean
 * 文件名：   ContentBean
 * 创建者：   DoDo
 * 创建时间： 2017/10/15 1:10
 * 描述：     TODO
 */

public class ContentBean {

    private String title;
    private String duration;
    private String cover;//背景图
    private String headImg;
    private String headName;
    private String playCount;//播放次数
    private String commentCount;//评论

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }
}
