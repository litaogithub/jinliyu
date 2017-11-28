package com.xingyunyicai.core.delegates.bottom.bean;

import android.support.v7.widget.AppCompatImageView;
import android.view.ViewGroup;

import com.xingyunyicai.core.R;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates.bottom.bean
 * 文件名：   BottomTabImageBean
 * 创建者：   DoDo
 * 创建时间： 2017/9/15 19:37
 * 描述：     TODO
 */

public class BottomTabImageBean extends BaseBottomTabBean {

    private AppCompatImageView mImageView = null;

    @Override
    public int getLayoutId() {
        return R.layout.bottom_tab_image;
    }

    @Override
    public void initView(ViewGroup container) {
        if (getImageSelector() == null) {
            throw new RuntimeException("BottomTabImageBean must zhiding a ImageSelector");
        }
        mImageView = (AppCompatImageView) container.getChildAt(0);

        mImageView.setImageDrawable(getImageSelector());
    }

}
