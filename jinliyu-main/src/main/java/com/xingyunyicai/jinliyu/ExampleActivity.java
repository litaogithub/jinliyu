package com.xingyunyicai.jinliyu;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xingyunyicai.core.activitys.ProxyActivity;
import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.core.util.CommonUtil;
import com.xingyunyicai.ec.main.ECBottomDelegate;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        在toolbar上创建menu，需要去掉自带的ActionBar，方法有两种：
//        1、使用不带ActionBar的主题，比如：Theme.AppCompat.Light.NoActionBar
//        2、使用默认的主题（自带Actionbar），加入两个属性
//            <item name="windowActionBar">false</item>
//            <item name="windowNoTitle">true</item>

//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            //隐藏Actionbar
//            actionBar.hide();
//        }
        //全局配置
        DoDo.getConfigurator().withActivity(this);
        //状态栏适配(白底黑字)
        CommonUtil.setStatusBarForNative(this);
        CommonUtil.setStatusBarForMIUI(this);
        CommonUtil.setStatusBarForFlyme(this);
        //透明状态栏(注：必须放在状态栏适配代码之后，否则不生效)
        StatusBarCompat.translucentStatusBar(this, true);

//        StatusBarCompat.setStatusBarColor(this, Color.BLACK);
//        StatusBarUtil.setColor(this, Color.parseColor("#1db69a"), 0);
        //设置delegate默认背景色
        setDefaultFragmentBackground(R.color.default_fragment_background);
    }

    @Override
    public DoDoDelegate setRootDelegate() {
        return new ECBottomDelegate();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        //设置全局fragment动画  注：对RootDelegate（也就是第一个delegate）无效
        return new DefaultHorizontalAnimator();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //极光推送 生命周期
//        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //极光推送 生命周期
//        JPushInterface.onResume(this);
    }

}
