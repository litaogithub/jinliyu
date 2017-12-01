package com.xingyunyicai.ec.main.index_2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.ec.R;
import com.xingyunyicai.ec.R2;
import com.xingyunyicai.ec.main.index_2.adapter.TePinPagerAdapter;

import butterknife.BindView;

/**
 * Created by YuanJun on 2017/11/30 10:19
 */

public class TePinDelegate extends BaseBottomItemDelegate {

    @BindView(R2.id.loading)
    LinearLayout mLoadingView;
    @BindView(R2.id.content)
    LinearLayout mContentView;
    @BindView(R2.id.category)
    SlidingTabLayout mCategoryTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;

    @Override
    public Object setLayout() {
        return R.layout.delegate_te_pin;
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
        //请求数据
//        RestClient.builder()
//                .url("特品汇-商品列表.json")
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        handleData(response);
//                    }
//                })
//                .build()
//                .get();
        handleData(null);
    }

    @SuppressWarnings("AlibabaRemoveCommentedCode")
    private void handleData(String json) {
        //解析json获取分类列表  title-url
//        final JSONObject data = JSON.parseObject(json).getJSONObject("data").getJSONObject("data");
//        final JSONArray dataArray = data.getJSONArray("list");
//        final int size = dataArray.size();
//        final String[] titles = new String[size];
//        final String[] urls = new String[size];
//        for (int i = 0; i < size; i++) {
//            titles[i] = dataArray.getJSONObject(i).getString("title");
//            urls[i] = ApiConstants.TUI_JIAN_URL + "content_" + (i + 1) + ".json";
//        }

        final String[] titles = new String[]{"全部", "农产品", "电子产品", "办公用品", "有机食品", "促销商品"};
        final String[] urls = new String[]{"1", "2", "3", "4", "5", "6"};

        //设置预加载数
        mViewPager.setOffscreenPageLimit(6);//默认是1 默认最多缓存2的1次方+1=3
        mViewPager.setAdapter(new TePinPagerAdapter(getChildFragmentManager(), titles, urls));
        mCategoryTabLayout.setViewPager(mViewPager);
        //隐藏加载布局，显示列表布局
        mLoadingView.setVisibility(View.GONE);
        mContentView.setVisibility(View.VISIBLE);
    }


}
