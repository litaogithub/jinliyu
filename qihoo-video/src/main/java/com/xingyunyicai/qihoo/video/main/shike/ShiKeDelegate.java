package com.xingyunyicai.qihoo.video.main.shike;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.core.util.toast.ToastUtil;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.R2;
import com.xingyunyicai.qihoo.video.main.shike.adapter.ShiKePagerAdapter;

import butterknife.BindView;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main
 * 文件名：   PinDaoDelegate
 * 创建者：   DoDo
 * 创建时间： 2017/9/18 9:13
 * 描述：     TODO
 */

public class ShiKeDelegate extends BaseBottomItemDelegate {

    private static final String TAG = "ShiKeDelegate";

    @BindView(R2.id.shike_toobar)
    Toolbar mToolBar;
    @BindView(R2.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shi_ke;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        getProxyActivity().setSupportActionBar(mToolBar);

        mToolBar.inflateMenu(R.menu.menu_shi_ke);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                final int id = item.getItemId();
                //这里返回的id是R1里的，R2里虽然包含一样的名字，但值是不同的
                //又因为library中的R里面不是常量，而switch语句里的case需要的是常量，所以用if
                if (id == R.id.action_game) {
                    ToastUtil.show(item.getTitle());

                } else if (id == R.id.action_history) {
                    ToastUtil.show(item.getTitle());

                } else if (id == R.id.action_download) {
                    ToastUtil.show(item.getTitle());

                } else {
                }
                return false;
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        DoDo.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //延时加载，防止动画卡顿
                handleData();

            }
        }, 500);

    }

    private void handleData() {

        mViewPager.setAdapter(new ShiKePagerAdapter(getChildFragmentManager()));
        mTabLayout.setViewPager(mViewPager);
    }

    /**
     * 调用getProxyActivity().setSupportActionBar(mToolBar);方法后会执行，不设置则不会执行
     *
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.clear();
//        inflater.inflate(R.menu.menu_shi_ke, menu);
//        DoDoLogger.d(TAG, "onCreateOptionsMenu");
    }

    /**
     * 同上
     *
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

}
