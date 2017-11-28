package com.xingyunyicai.core.delegates.bottom.builder;

import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.core.delegates.bottom.bean.BaseBottomTabBean;

import java.util.LinkedHashMap;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates.bottom
 * 文件名：   BottomItemBuilder
 * 创建者：   DoDo
 * 创建时间： 2017/9/7 17:44
 * 描述：     TabBean + ItemDelegate
 */

public final class BottomItemBuilder {

    private final LinkedHashMap<BaseBottomTabBean, BaseBottomItemDelegate> ITEMS = new LinkedHashMap<>();

    public static BottomItemBuilder builder() {
        return new BottomItemBuilder();
    }

    public final BottomItemBuilder addItem(BaseBottomTabBean bean, BaseBottomItemDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final BottomItemBuilder addItems(LinkedHashMap<BaseBottomTabBean,BaseBottomItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BaseBottomTabBean, BaseBottomItemDelegate> build() {
        return ITEMS;
    }

}
