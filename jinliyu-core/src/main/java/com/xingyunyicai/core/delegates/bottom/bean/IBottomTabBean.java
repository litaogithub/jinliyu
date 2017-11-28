package com.xingyunyicai.core.delegates.bottom.bean;

import android.view.ViewGroup;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates.bottom.bean
 * 文件名：   IBottomTabBean
 * 创建者：   DoDo
 * 创建时间： 2017/9/15 22:47
 * 描述：     TODO
 */

public interface IBottomTabBean {

    int getLayoutId();

    void initView(ViewGroup container);

//    void setNormalState(ViewGroup container);
//
//    void setSelectedState(ViewGroup container);

}
