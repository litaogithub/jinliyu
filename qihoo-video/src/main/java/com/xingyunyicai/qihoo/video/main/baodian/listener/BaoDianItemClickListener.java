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

public class BaoDianItemClickListener implements BaseQuickAdapter.OnItemClickListener {

    public static BaoDianItemClickListener create() {
        return new BaoDianItemClickListener();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        ToastUtil.show("item点击：" + position);

    }


}
