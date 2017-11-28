package com.xingyunyicai.core.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.xingyunyicai.core.R;
import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.core.util.toast.ToastUtil;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.activitys
 * 文件名：   ProxyActivity
 * 创建者：   DoDo
 * 创建时间： 2017/8/30 15:48
 * 描述：     容器Activity
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract DoDoDelegate setRootDelegate();

    //注意这里有一个重载的onCreate方法，Activity启动时走一个参数的方法
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //防止app进入后台重新打开发生重启的现象
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        //装载根Activity视图
        setContentView(container);

        //第一次加载
        if (savedInstanceState == null) {
            //加载根delegate
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ToastUtil.destroy();

        //释放资源
        System.gc();//垃圾回收（回收时间不确定）
        System.runFinalization();//对已经失去引用的对象强制调用该对象的finalize方法
    }

}
