package com.xingyunyicai.ec.main.index_2.bean;

import android.widget.LinearLayout;

/**
 * Created by YuanJun on 2017/12/4 14:28
 */

public class SortBean {

    private int type = -1;//类型 0=默认排序 1=销量 2=人气 3=价格
    private LinearLayout container = null;//容器
    private boolean status = false;//选中状态 true=选中 false=未选中
    private int orientation = -1;//排序方向 0=正向 1=逆向 -1=无排序(默认排序)

    public SortBean(int type, LinearLayout container, boolean status, int orientation) {
        this.type = type;
        this.container = container;
        this.status = status;
        this.orientation = orientation;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LinearLayout getContainer() {
        return container;
    }

    public void setContainer(LinearLayout container) {
        this.container = container;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
