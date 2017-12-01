package com.xingyunyicai.ec.main.index_2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.ec.R;
import com.xingyunyicai.ec.R2;
import com.xingyunyicai.ec.main.index_2.entity.SortEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by YuanJun on 2017/11/30 10:19
 */

public class TePinDelegate extends BaseBottomItemDelegate {

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @BindView(R2.id.sort)
    CommonTabLayout mSortTabLayout = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_te_pin;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initSortTab();

    }

    /**
     * 初始化分类列表
     */
    private void initSortTab() {
        initSortData();
        mSortTabLayout.setTabData(mTabEntities);
        mSortTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                ToastUtils.showShort(mTabEntities.get(position).getTabTitle());
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    /**
     * 获取分类数据
     */
    private void initSortData() {
        mTabEntities.add(new SortEntity("全部"));
        mTabEntities.add(new SortEntity("电子商品"));
        mTabEntities.add(new SortEntity("家用电器"));
        mTabEntities.add(new SortEntity("品牌直销"));
    }
}
