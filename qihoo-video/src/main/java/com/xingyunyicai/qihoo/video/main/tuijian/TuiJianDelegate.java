package com.xingyunyicai.qihoo.video.main.tuijian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flyco.tablayout.SlidingTabLayout;
import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.core.net.RestClient;
import com.xingyunyicai.core.net.callback.ISuccess;
import com.xingyunyicai.core.util.toast.ToastUtil;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.R2;
import com.xingyunyicai.qihoo.video.main.tuijian.adapter.TuiJianPagerAdapter;
import com.xingyunyicai.qihoo.video.constant.ApiConstants;
import com.xingyunyicai.qihoo.video.main.tuijian.event.SearchHotwordEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.home
 * 文件名：   TuiJianDelegate
 * 创建者：   DoDo
 * 创建时间： 2017/9/18 9:07
 * 描述：     TODO
 */

public class TuiJianDelegate extends BaseBottomItemDelegate {

    @BindView(R2.id.ll_search)
    LinearLayoutCompat mSearch;
    @BindView(R2.id.rl_game)
    RelativeLayout mHome;
    @BindView(R2.id.rl_history)
    RelativeLayout mHistory;
    @BindView(R2.id.rl_download)
    RelativeLayout mDownload;
    @BindView(R2.id.tv_search)
    AppCompatTextView mSearchHotword;
    @BindView(R2.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.empty)
    LinearLayout mLoading;
    @BindView(R2.id.content)
    LinearLayout mContent;


    @OnClick({R2.id.ll_search, R2.id.rl_game, R2.id.rl_history, R2.id.rl_download})
    public void onViewClicked(View view) {
        final int id = view.getId();
        if (id == R.id.ll_search) {
            ToastUtil.show("搜索");

            final DoDoDelegate delegate = getParentDelegate();
            delegate.start(TestPageDelegate.create("haha"));

        } else if (id == R.id.rl_game) {
            ToastUtil.show("游戏中心");
        } else if (id == R.id.rl_history) {
            ToastUtil.show("观看记录");
        } else if (id == R.id.rl_download) {
            ToastUtil.show("离线缓存");
        } else {
        }
    }

    @OnLongClick({R2.id.ll_search, R2.id.rl_game, R2.id.rl_history, R2.id.rl_download})
    public boolean onViewLongClicked(View view) {
        final int id = view.getId();
        if (id == R.id.ll_search) {
            ToastUtil.show("长按 搜索");
        } else if (id == R.id.rl_game) {
            ToastUtil.show("长按 游戏中心");
        } else if (id == R.id.rl_history) {
            ToastUtil.show("长按 观看记录");
        } else if (id == R.id.rl_download) {
            ToastUtil.show("长按 离线缓存");
        } else {
        }
        return true;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_tui_jian;
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
                .url(ApiConstants.TUI_JIAN_URL + "tab.json")
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
            urls[i] = ApiConstants.TUI_JIAN_URL + "content_" + (i + 1) + ".json";
        }

        mViewPager.setOffscreenPageLimit(6);//默认是1 默认最多缓存2的1次方+1=3
        mViewPager.setAdapter(new TuiJianPagerAdapter(getChildFragmentManager(), titles, urls));
        mTabLayout.setViewPager(mViewPager);
        mLoading.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
    }

    /**
     * 设置热搜词
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchHotwordEvent(SearchHotwordEvent event) {
        final String searchHotword = event.getSearchHotword();
        if (!TextUtils.isEmpty(searchHotword)) {
            mSearchHotword.setText(searchHotword);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
