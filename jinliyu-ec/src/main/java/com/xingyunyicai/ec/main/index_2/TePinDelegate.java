package com.xingyunyicai.ec.main.index_2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.joanzapata.iconify.widget.IconTextView;
import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.ec.R;
import com.xingyunyicai.ec.R2;
import com.xingyunyicai.ec.main.index_2.adapter.TePinPagerAdapter;
import com.xingyunyicai.ec.main.index_2.bean.SortBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by YuanJun on 2017/11/30 10:19
 */

public class TePinDelegate extends BaseBottomItemDelegate {

    private static final String TAG = "TePinDelegate";
    private final List<SortBean> mSortBeans = new ArrayList<>();

    @BindView(R2.id.loading)
    LinearLayout mLoadingView;
    @BindView(R2.id.content)
    LinearLayout mContentView;
    @BindView(R2.id.category)
    SlidingTabLayout mCategoryTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;

    @BindView(R2.id.sort_default)
    LinearLayout mSortDefault = null;
    @BindView(R2.id.sort_sales)
    LinearLayout mSortSales = null;
    @BindView(R2.id.sort_popularity)
    LinearLayout mSortPopularity = null;
    @BindView(R2.id.sort_price)
    LinearLayout mSortPrice = null;

    @OnClick({R2.id.sort_default, R2.id.sort_sales, R2.id.sort_popularity, R2.id.sort_price})
    public void onSortClick(View view) {
        final int id = view.getId();
        if (id == R.id.sort_default) {
//            ToastUtils.showShort("默认排序");
            setSortColor(0);
        } else if (id == R.id.sort_sales) {
//            ToastUtils.showShort("销量");
            setSortColor(1);
        } else if (id == R.id.sort_popularity) {
//            ToastUtils.showShort("人气");
            setSortColor(2);
        } else if (id == R.id.sort_price) {
//            ToastUtils.showShort("价格");
            setSortColor(3);
        } else {
        }
    }

    private void setSortColor(int index) {

        final SortBean sortBean = mSortBeans.get(index);
        final boolean status = sortBean.getStatus();
        final int orientation = sortBean.getOrientation();
        if (status == false) {
            //清除选中状态
            resetSortStatus();

            final LinearLayout container = sortBean.getContainer();
            final int childCount = container.getChildCount();
            for (int j = 0; j < childCount; j++) {
                final View child = container.getChildAt(j);
                if (child instanceof TextView) {
                    ((TextView) child).setTextColor(ContextCompat.getColor(child.getContext(), R.color.red));
                }
            }
            sortBean.setStatus(true);
        } else {
            if (orientation == -1) {
                ToastUtils.showShort("默认");
                return;
            }
            if (orientation == 0) {
                sortBean.setOrientation(1);
            } else if (orientation == 1) {
                sortBean.setOrientation(0);
            } else {
            }
            final LinearLayout container = sortBean.getContainer();
            final int childCount = container.getChildCount();
            for (int j = 0; j < childCount; j++) {
                final View child = container.getChildAt(j);
                if (child instanceof IconTextView) {
                    ((IconTextView) child).setText(sortBean.getOrientation() == 1 ? "{fa-caret-up}" : "{fa-caret-down}");
                }
            }
        }

        if (sortBean.getOrientation() == -1) {
            ToastUtils.showShort("默认");
        } else {
            ToastUtils.showShort(sortBean.getOrientation() == 0 ? "正序" : "倒序");
        }
    }

    private void resetSortStatus() {
        final int size = mSortBeans.size();
        for (int i = 0; i < size; i++) {
            final SortBean sortBean = mSortBeans.get(i);

            final boolean status = sortBean.getStatus();
            if (status == false) {
                continue;
            }

            final LinearLayout container = sortBean.getContainer();
            final int childCount = container.getChildCount();
            for (int j = 0; j < childCount; j++) {
                final View child = container.getChildAt(j);
                if (child instanceof TextView) {
                    ((TextView) child).setTextColor(ContextCompat.getColor(child.getContext(), R.color.test_color_black));
                }
            }
            sortBean.setStatus(false);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_te_pin;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mSortBeans.add(new SortBean(0, mSortDefault, true, -1));
        mSortBeans.add(new SortBean(1, mSortSales, false, 0));
        mSortBeans.add(new SortBean(2, mSortPopularity, false, 0));
        mSortBeans.add(new SortBean(3, mSortPrice, false, 0));
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
        final String[] urls = new String[]{"全部", "农产品", "电子产品", "办公用品", "有机食品", "促销商品"};

        //设置预加载数
        mViewPager.setOffscreenPageLimit(titles.length);//默认是1 默认最多缓存2的1次方+1=3
        mViewPager.setAdapter(new TePinPagerAdapter(getChildFragmentManager(), titles, urls));
        mCategoryTabLayout.setViewPager(mViewPager);
        //隐藏加载布局，显示列表布局
        mLoadingView.setVisibility(View.GONE);
        mContentView.setVisibility(View.VISIBLE);
    }


}
