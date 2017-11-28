package com.xingyunyicai.core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xingyunyicai.core.activitys.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates
 * 文件名：   BaseDelegate
 * 创建者：   DoDo
 * 创建时间： 2017/8/30 15:51
 * 描述：     delegate基类
 */

public abstract class BaseDelegate extends SupportFragment {

    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;

    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    public final ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DELEGATE.setFragmentAnimator(new DefaultHorizontalAnimator());
        //在fragment(delegate)中显示menu，且设置SupportActionBar的话，必须添加此命令
        //如果使用mToolBar.inflateMenu(R.menu...)来填充菜单，则不需要添加此命令
//        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
//            container.addView(rootView);
        } else {
            throw new ClassCastException("type of getLayoutId() must be int or View!");
        }
        //绑定视图
        mUnbinder = ButterKnife.bind(this, rootView);
        //添加事件
        onBindView(savedInstanceState, rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            //解除视图绑定
            mUnbinder.unbind();
        }
    }
}
