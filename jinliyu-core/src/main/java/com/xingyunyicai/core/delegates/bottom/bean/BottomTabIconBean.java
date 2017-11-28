package com.xingyunyicai.core.delegates.bottom.bean;

import android.view.ViewGroup;

import com.joanzapata.iconify.widget.IconTextView;
import com.xingyunyicai.core.R;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates.bottom.bean
 * 文件名：   BottomTabIconBean
 * 创建者：   DoDo
 * 创建时间： 2017/9/7 22:01
 * 描述：     TODO
 */

public class BottomTabIconBean extends BaseBottomTabBean {

    private final CharSequence ICON;
    private IconTextView mIconTv = null;

    public BottomTabIconBean(CharSequence icon) {
        this.ICON = icon;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    @Override
    public int getLayoutId() {
        return R.layout.bottom_tab_icon;
    }

    @Override
    public void initView(ViewGroup container) {
        mIconTv = (IconTextView) container.getChildAt(0);

        mIconTv.setText(getIcon());
        mIconTv.setTextSize(getIconSize());

        mIconTv.setTextColor(getIconSelector());
    }
//
//    @Override
//    public void setNormalState(ViewGroup container) {
//        final IconTextView icon = (IconTextView) container.getChildAt(0);
//        icon.setText(getIcon());
//        icon.setTextColor(getNormalTextColor());
//        container.setBackgroundColor(getNormalBackgroundColor());
//    }
//
//    @Override
//    public void setSelectedState(ViewGroup container) {
//        final IconTextView icon = (IconTextView) container.getChildAt(0);
//        icon.setText(getIcon());
//        icon.setTextColor(getSelectedTextColor());
//        container.setBackgroundColor(getSelectedBackgroundColor());
//    }

}
