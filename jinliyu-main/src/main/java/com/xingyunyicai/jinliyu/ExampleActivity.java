package com.xingyunyicai.jinliyu;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xingyunyicai.core.activitys.ProxyActivity;
import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.ec.EcDelegate;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;



public class ExampleActivity extends ProxyActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        super.onCreate(savedInstanceState);
        /*
        在toolbar上创建menu，需要去掉自带的ActionBar，方法有两种：
        1、使用不带ActionBar的主题，比如：Theme.AppCompat.Light.NoActionBar
        2、使用默认的主题（自带Actionbar），加入两个属性
            <item_location_sort name="windowActionBar">false</item_location_sort>
            <item_location_sort name="windowNoTitle">true</item_location_sort>
         */
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            //隐藏Actionbar
//            actionBar.hide();
//        }
        DoDo.getConfigurator().withActivity(this);
        //透明状态栏
//        StatusBarCompat.translucentStatusBar(this, true);
//        StatusBarUtil.setColor(this, Color.parseColor("#1db69a"), 0);
        setDefaultFragmentBackground(R.color.default_fragment_background);
    }

    @Override
    public DoDoDelegate setRootDelegate() {
        return new EcDelegate();
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
