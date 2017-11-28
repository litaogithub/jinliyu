package com.xingyunyicai.qihoo.video.main.baodian.listener;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xingyunyicai.core.util.toast.ToastUtil;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian
 * 文件名：   BaoDianItemClickListener
 * 创建者：   DoDo
 * 创建时间： 2017/9/23 7:38
 * 描述：     TODO
 */

public class BaoDianItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

    public static BaoDianItemChildClickListener create() {
        return new BaoDianItemChildClickListener();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ToastUtil.show("item child点击：" + position);
    }
}
