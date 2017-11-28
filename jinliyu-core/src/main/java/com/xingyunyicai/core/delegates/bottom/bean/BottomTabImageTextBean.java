package com.xingyunyicai.core.delegates.bottom.bean;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
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

public class BottomTabImageTextBean extends BaseBottomTabBean {

    private final CharSequence TEXT;
    private AppCompatImageView mImageView = null;
    private AppCompatTextView mText = null;

    public BottomTabImageTextBean(CharSequence text) {
        this.TEXT = text;
    }

    public CharSequence getText() {
        return TEXT;
    }

    @Override
    public int getLayoutId() {
        return R.layout.bottom_tab_image_text;
    }

    @Override
    public void initView(ViewGroup container) {
        if (getImageSelector() == null) {
            throw new RuntimeException("BottomTabImageTextBean must zhiding a ImageSelector");
        }
        mImageView = (AppCompatImageView) container.getChildAt(0);
        mText = (AppCompatTextView) container.getChildAt(1);

        mText.setText(getText());
        mText.setTextSize(getTextSize());

        mText.setTextColor(getTextSelector());
        mImageView.setImageDrawable(getImageSelector());
    }

}
