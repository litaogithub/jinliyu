package com.xingyunyicai.ec.main.index_2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xingyunyicai.ec.main.index_2.TePinContentDelegate;

/**
 * Created by YuanJun on 2017/12/1 17:52
 */

public class TePinPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTitles = null;
    private String[] mUrls = null;

    public TePinPagerAdapter(FragmentManager fm, String[] titles, String[] urls) {
        super(fm);
        this.mTitles = titles;
        this.mUrls = urls;
    }

    @Override
    public Fragment getItem(int position) {
        return TePinContentDelegate.create(mUrls[position]);
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
