package com.xingyunyicai.core.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.recycler
 * 文件名：   MulHolder
 * 创建者：   DoDo
 * 创建时间： 2017/9/10 3:39
 * 描述：     备用，方便扩展
 */

public class MulHolder extends BaseViewHolder {

    public MulHolder(View view) {
        super(view);
    }

    //TODO 简单工厂模式

    public static MulHolder create(View view) {
        return new MulHolder(view);
    }
}
