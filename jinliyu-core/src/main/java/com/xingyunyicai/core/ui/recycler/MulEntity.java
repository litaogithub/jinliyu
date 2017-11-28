package com.xingyunyicai.core.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.recycler
 * 文件名：   MulEntity
 * 创建者：   DoDo
 * 创建时间： 2017/9/10 2:12
 * 描述：     每一个独立的item实体
 */

public class MulEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MUL_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERNECE =
            new SoftReference<>(MUL_FIELDS, ITEM_QUEUE);

    MulEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERNECE.get().putAll(fields);
    }

    public static MulEntityBuilder builder() {
        return new MulEntityBuilder();
    }

    /**
     * dapter会从这里获取到item类型
     *
     */
    @Override
    public int getItemType() {
        return (int) FIELDS_REFERNECE.get().get(MulFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key) {
        return (T) FIELDS_REFERNECE.get().get(key);
    }

    public final LinkedHashMap<?, ?> getFields() {
        return FIELDS_REFERNECE.get();
    }

    public final MulEntity setField(Object key, Object value) {
        FIELDS_REFERNECE.get().put(key, value);
        return this;
    }


}
