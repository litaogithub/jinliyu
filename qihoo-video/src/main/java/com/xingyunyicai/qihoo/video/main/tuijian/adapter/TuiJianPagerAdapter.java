package com.xingyunyicai.qihoo.video.main.tuijian.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.xingyunyicai.qihoo.video.main.tuijian.TuiJianContentDelegate;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian.adapter
 * 文件名：   TuiJianPagerAdapter
 * 创建者：   DoDo
 * 创建时间： 2017/10/12 0:40
 * 描述：     TODO
 */

public class TuiJianPagerAdapter extends FragmentPagerAdapter {
    /**
     * FragmentPagerAdapter:销毁视图
     * FragmentStatePagerAdapter:销毁视图+实例
     * <p>
     * setsetOffscreenPageLimit():预加载 同时保存的实例(视图)数 默认是1 也就是最多保存3个实例(在中间位置) 超过3个会根据选择的Adapter判断是销毁视图还是实例
     */

    private String[] mTitles = null;
    private String[] mUrls = null;

    public TuiJianPagerAdapter(FragmentManager fm,String[] titles,String[] urls) {
        super(fm);
        this.mTitles = titles;
        this.mUrls = urls;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("HAHAHAHA", "新建:" + mTitles[position]);
        return TuiJianContentDelegate.create(mUrls[position]);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
