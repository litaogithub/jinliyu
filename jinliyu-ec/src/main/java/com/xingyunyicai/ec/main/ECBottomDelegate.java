package com.xingyunyicai.ec.main;

import com.xingyunyicai.core.delegates.bottom.BaseBottomContainerDelegate;
import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.core.delegates.bottom.bean.BaseBottomTabBean;
import com.xingyunyicai.core.delegates.bottom.bean.BottomTabIconTitleBean;
import com.xingyunyicai.core.delegates.bottom.builder.BottomItemBuilder;
import com.xingyunyicai.core.delegates.bottom.builder.BottomTabBeanBuilder;
import com.xingyunyicai.ec.main.index_1.IndexDelegate;

import java.util.LinkedHashMap;

/**
 * Created by YuanJun on 2017/11/29 13:06
 */

public class ECBottomDelegate extends BaseBottomContainerDelegate {

    @Override
    public LinkedHashMap<BaseBottomTabBean, BaseBottomItemDelegate> setTabItems(BottomItemBuilder builder) {
        final LinkedHashMap<BaseBottomTabBean, BaseBottomItemDelegate> items = new LinkedHashMap<>();
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new BottomTabIconTitleBean("{fa-android}","首页1"))
                .build(), new IndexDelegate());
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new BottomTabIconTitleBean("{fa-android}","首页2"))
                .build(), TestDelegate.create("首页2"));
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new BottomTabIconTitleBean("{fa-android}","首页3"))
                .build(), TestDelegate.create("首页3"));
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new BottomTabIconTitleBean("{fa-android}","首页4"))
                .build(), TestDelegate.create("首页4"));
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new BottomTabIconTitleBean("{fa-android}","首页5"))
                .build(), TestDelegate.create("首页5"));

        return builder.addItems(items).build();
    }

    @Override
    public int setFirstPageIndex() {
        return 0;
    }
}
