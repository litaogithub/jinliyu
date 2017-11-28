package com.xingyunyicai.qihoo.video.main.tuijian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.qihoo.video.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian
 * 文件名：   TestPageDelegate
 * 创建者：   DoDo
 * 创建时间： 2017/10/3 2:42
 * 描述：     TODO
 */

public class TestPageDelegate extends DoDoDelegate {

    public static TestPageDelegate create(String title) {
        final Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        final TestPageDelegate delegate = new TestPageDelegate();
        delegate.setArguments(bundle);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_test_page;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        getSupportDelegate().setFragmentAnimator(new DefaultHorizontalAnimator());
//        final Bundle args = getArguments();
//        final String title = args.getString("TITLE");
//        final TextView tv = (TextView) rootView.findViewById(R.id.tv);
//        tv.setText(title);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
