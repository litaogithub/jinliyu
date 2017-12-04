package com.xingyunyicai.ec.main.index_2.bean;

/**
 * Created by YuanJun on 2017/12/4 10:46
 */

public class TePinBean {

    private String cover = null;//展示图
    private String title = null;//标题
    private boolean frank = false;//包邮
    private String sold = null;//销售量
    private String newPrice = null;//现价
    private String oldPrice = null;//原价

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getFrank() {
        return frank;
    }

    public void setFrank(boolean frank) {
        this.frank = frank;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }
}
