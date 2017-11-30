package com.xingyunyicai.ec.main;

import com.xingyunyicai.core.delegates.bottom.BaseBottomContainerDelegate;
import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.core.delegates.bottom.bean.BaseBottomTabBean;
import com.xingyunyicai.core.delegates.bottom.bean.BottomTabIconTitleBean;
import com.xingyunyicai.core.delegates.bottom.builder.BottomItemBuilder;
import com.xingyunyicai.core.delegates.bottom.builder.BottomTabBeanBuilder;
import com.xingyunyicai.ec.main.index_1.IndexDelegate;
import com.xingyunyicai.ec.main.index_2.TePinDelegate;

import java.util.LinkedHashMap;

/**
 * Created by YuanJun on 2017/11/29 13:06
 */

public class ECBottomDelegate extends BaseBottomContainerDelegate {

    @Override
    public LinkedHashMap<BaseBottomTabBean, BaseBottomItemDelegate> setTabItems(BottomItemBuilder builder) {
        final LinkedHashMap<BaseBottomTabBean, BaseBottomItemDelegate> items = new LinkedHashMap<>();
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new BottomTabIconTitleBean("{fa-android}","首页"))
                .build(), new IndexDelegate());
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new BottomTabIconTitleBean("{fa-android}","特品汇"))
                .build(), new TePinDelegate());
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new BottomTabIconTitleBean("{fa-android}","我的社区"))
                .build(), TestDelegate.create("我的社区"));
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new BottomTabIconTitleBean("{fa-android}","个人中心"))
                .build(), TestDelegate.create("个人中心"));

        return builder.addItems(items).build();
    }

    @Override
    public int setFirstPageIndex() {
        return 1;
    }
}
