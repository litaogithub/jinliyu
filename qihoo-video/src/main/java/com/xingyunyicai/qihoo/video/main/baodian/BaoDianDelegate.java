package com.xingyunyicai.qihoo.video.main.baodian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flyco.tablayout.SlidingTabLayout;
import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.core.net.RestClient;
import com.xingyunyicai.core.net.callback.ISuccess;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.R2;
import com.xingyunyicai.qihoo.video.constant.ApiConstants;
import com.xingyunyicai.qihoo.video.main.baodian.adapter.BaoDianPagerAdapter;

import butterknife.BindView;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main
 * 文件名：   PinDaoDelegate
 * 创建者：   DoDo
 * 创建时间： 2017/9/18 9:13
 * 描述：     TODO
 */

public class BaoDianDelegate extends BaseBottomItemDelegate {

    @BindView(R2.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.empty)
    LinearLayout mLoading;
    @BindView(R2.id.content)
    LinearLayout mContent;

    @Override
    public Object setLayout() {
        return R.layout.delegate_bao_dian;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        DoDo.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //延时加载，防止动画卡顿
                requestData();

            }
        }, 500);

    }

    private void requestData() {
        RestClient.builder()
                .url(ApiConstants.BAO_DIAN_URL + "tab.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        handleData(response);
                    }
                })
                .build()
                .get();
    }

    private void handleData(String json) {
        final JSONObject data = JSON.parseObject(json).getJSONObject("data").getJSONObject("data");
        final JSONArray dataArray = data.getJSONArray("list");
        final int size = dataArray.size();
        final String[] titles = new String[size];
        final String[] urls = new String[size];
        for (int i = 0; i < size; i++) {
            titles[i] = dataArray.getJSONObject(i).getString("title");
            urls[i] = ApiConstants.BAO_DIAN_URL + "content_" + (i + 1) + ".json";
        }

        mViewPager.setOffscreenPageLimit(titles.length);//默认是1 默认最多缓存2的1次方+1=3
        mViewPager.setAdapter(new BaoDianPagerAdapter(getChildFragmentManager(), titles, urls));
        mTabLayout.setViewPager(mViewPager);
        mLoading.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
    }
}
