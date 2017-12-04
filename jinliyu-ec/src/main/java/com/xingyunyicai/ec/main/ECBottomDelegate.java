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
        return builder
                .addItem(BottomTabBeanBuilder.builder(getContext())
                        .setTabBean(new BottomTabIconTitleBean("{fa-android}", "首页"))
                        .build(), new IndexDelegate())
                .addItem(BottomTabBeanBuilder.builder(getContext())
                        .setTabBean(new BottomTabIconTitleBean("{fa-android}", "特品汇"))
                        .build(), new TePinDelegate())
                .addItem(BottomTabBeanBuilder.builder(getContext())
                        .setTabBean(new BottomTabIconTitleBean("{fa-android}", "我的社区"))
                        .build(), TestDelegate.create("我的社区"))
                .addItem(BottomTabBeanBuilder.builder(getContext())
                        .setTabBean(new BottomTabIconTitleBean("{fa-android}", "个人中心"))
                        .build(), TestDelegate.create("个人中心"))
                .build();
    }

    @Override
    public int setFirstPageIndex() {
        return 0;
    }
}
