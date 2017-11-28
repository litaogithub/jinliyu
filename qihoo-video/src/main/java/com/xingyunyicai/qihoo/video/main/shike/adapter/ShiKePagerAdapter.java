package com.xingyunyicai.qihoo.video.main.shike.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xingyunyicai.qihoo.video.main.shike.FuJinPagerDelegate;
import com.xingyunyicai.qihoo.video.main.shike.ShiKePagerDelegate;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.shike
 * 文件名：   ShiKePagerAdapter
 * 创建者：   DoDo
 * 创建时间： 2017/10/11 16:29
 * 描述：     TODO
 */

public class ShiKePagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTitles = new String[]{"时刻", "附近"};

    public ShiKePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ShiKePagerDelegate();
        } else {
            return new FuJinPagerDelegate();
        }
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
