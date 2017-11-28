package com.xingyunyicai.qihoo.video.main.tuijian.listener;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.xingyunyicai.core.ui.recycler.ItemType;
import com.xingyunyicai.core.util.toast.ToastUtil;
import com.xingyunyicai.qihoo.video.R;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian
 * 文件名：   BaoDianItemClickListener
 * 创建者：   DoDo
 * 创建时间： 2017/9/23 7:38
 * 描述：     TODO
 */

public class TuiJianItemClickListener implements BaseQuickAdapter.OnItemClickListener {

    public static TuiJianItemClickListener create() {
        return new TuiJianItemClickListener();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        int type = adapter.getItemViewType(position);
        if (type == ItemType.AD_1) {
            ToastUtil.show("广告位：" + position);
            final ImageView iv = (ImageView) adapter.getViewByPosition(position, R.id.iv);
            YoYo.with(Techniques.FadeOutRight)
                    .duration(600)
                    .playOn(iv);

            YoYo.with(Techniques.FadeInLeft)
                    .duration(500)
                    .delay(400)
                    .playOn(iv);

        } else if (type == ItemType.HEADER) {
            TextView tv= (TextView) adapter.getViewByPosition(position, R.id.block_name);
            ToastUtil.show(tv.getText().toString());
        }

    }


}
