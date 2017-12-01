package com.xingyunyicai.ec.main.index_2.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by YuanJun on 2017/12/1 14:21
 */

public class SortEntity implements CustomTabEntity {

    private String title = null;

    public SortEntity(String title) {
        this.title = title;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
