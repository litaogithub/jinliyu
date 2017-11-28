package com.xingyunyicai.core.ui.recycler;

import android.support.annotation.LayoutRes;

import java.util.LinkedHashMap;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.recycler
 * 文件名：   ItemTypeBuilder
 * 创建者：   DoDo
 * 创建时间： 2017/9/22 21:49
 * 描述：     TODO
 */

public final class ItemTypeBuilder {

    private final LinkedHashMap<Integer, Integer> ITEM_TYPES = new LinkedHashMap<>();

    public static ItemTypeBuilder builder() {
        return new ItemTypeBuilder();
    }

    public final ItemTypeBuilder addItemType(int type, @LayoutRes int layoutResId) {
        ITEM_TYPES.put(type, layoutResId);
        return this;
    }

    public final ItemTypeBuilder addItemTypes(LinkedHashMap<Integer, Integer> itemTypes) {
        ITEM_TYPES.putAll(itemTypes);
        return this;
    }

    public final LinkedHashMap<Integer, Integer> build() {
        return ITEM_TYPES;
    }

}
