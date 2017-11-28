package com.xingyunyicai.core.delegates.bottom.builder;

import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;

import java.util.ArrayList;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates.bottom.builder
 * 文件名：   BottomItemDelegateBuilder
 * 创建者：   DoDo
 * 创建时间： 2017/9/15 15:58
 * 描述：     ItemDelegate
 */

public final class BottomItemDelegateBuilder {

    private final ArrayList<BaseBottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();

    public static BottomItemDelegateBuilder builder() {
        return new BottomItemDelegateBuilder();
    }

    public final BottomItemDelegateBuilder addItemDelegate(BaseBottomItemDelegate delegate) {
        ITEM_DELEGATES.add(delegate);
        return this;
    }

    public final BottomItemDelegateBuilder addItemDelegates(ArrayList<BaseBottomItemDelegate> delegates) {
        ITEM_DELEGATES.addAll(delegates);
        return this;
    }

    public final ArrayList<BaseBottomItemDelegate> build() {
        return ITEM_DELEGATES;
    }

}
