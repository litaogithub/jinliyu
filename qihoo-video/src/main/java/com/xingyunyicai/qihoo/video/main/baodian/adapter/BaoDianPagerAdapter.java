package com.xingyunyicai.qihoo.video.main.baodian.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xingyunyicai.qihoo.video.main.baodian.BaoDianContentDelegate;

import java.util.Random;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.baodian.adapter
 * 文件名：   BaoDianPagerAdapter
 * 创建者：   DoDo
 * 创建时间： 2017/10/14 23:52
 * 描述：     TODO
 */

public class BaoDianPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = null;
    private String[] mUrls = null;

    public BaoDianPagerAdapter(FragmentManager fm,String[] titles,String[] urls) {
        super(fm);
        this.mTitles = titles;
        this.mUrls = urls;
    }

    @Override
    public Fragment getItem(int position) {
        if (position <= 2) {
            return BaoDianContentDelegate.create(mUrls[position]);
        } else {
            final Random random = new Random();
            final int index = random.nextInt(3);
            return BaoDianContentDelegate.create(mUrls[index]);
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
