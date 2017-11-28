package com.xingyunyicai.core.delegates.bottom.bean;

import android.support.v7.widget.AppCompatTextView;
import android.view.ViewGroup;

import com.joanzapata.iconify.widget.IconTextView;
import com.xingyunyicai.core.R;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates.bottom.bean
 * 文件名：   BottomTabIconTitleBean
 * 创建者：   DoDo
 * 创建时间： 2017/9/7 15:24
 * 描述：     tab选项卡
 */

public class BottomTabIconTitleBean extends BaseBottomTabBean {

    /**
     * CharSequence是个接口，String实现了这个接口
     * CharSequence是可读可写序列，String是可读序列
     */

    private final CharSequence ICON;
    private final CharSequence TITLE;
    private IconTextView mIconTv = null;
    private AppCompatTextView mTv = null;

    public BottomTabIconTitleBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }

    @Override
    public int getLayoutId() {
        return R.layout.bottom_tab_icon_text;
    }

    @Override
    public void initView(ViewGroup container) {
        mIconTv = (IconTextView) container.getChildAt(0);
        mTv = (AppCompatTextView) container.getChildAt(1);

        mIconTv.setText(getIcon());
        mTv.setText(getTitle());

        mIconTv.setTextSize(getIconSize());
        mTv.setTextSize(getTextSize());

        mIconTv.setTextColor(getIconSelector());
        mTv.setTextColor(getTextSelector());
    }
//
//    @Override
//    public void setNormalState(ViewGroup container) {
//        final IconTextView icon = (IconTextView) container.getChildAt(0);
//        final AppCompatTextView title = (AppCompatTextView) container.getChildAt(1);
//        icon.setText(getIcon());
//        title.setText(getTitle());
//        icon.setTextColor(getNormalTextColor());
//        title.setTextColor(getNormalTextColor());
//        container.setBackgroundColor(getNormalBackgroundColor());
//    }
//
//    @Override
//    public void setSelectedState(ViewGroup container) {
//        final IconTextView icon = (IconTextView) container.getChildAt(0);
//        final AppCompatTextView title = (AppCompatTextView) container.getChildAt(1);
//        icon.setText(getIcon());
//        title.setText(getTitle());
//        icon.setTextColor(getSelectedTextColor());
//        title.setTextColor(getSelectedTextColor());
//        container.setBackgroundColor(getSelectedBackgroundColor());
//    }
}
