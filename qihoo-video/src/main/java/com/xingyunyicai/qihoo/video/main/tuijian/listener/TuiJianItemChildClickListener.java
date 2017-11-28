package com.xingyunyicai.qihoo.video.main.tuijian.listener;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.core.ui.recycler.MulFields;
import com.xingyunyicai.core.util.toast.ToastUtil;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.main.tuijian.adapter.TuiJianAdapter;
import com.xingyunyicai.qihoo.video.main.tuijian.anim.RotationAnimator;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.FooterBean;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.TodayHotBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian
 * 文件名：   BaoDianItemClickListener
 * 创建者：   DoDo
 * 创建时间： 2017/9/23 7:38
 * 描述：     TODO
 */

public class TuiJianItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

    public static TuiJianItemChildClickListener create() {
        return new TuiJianItemChildClickListener();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//        ToastUtil.show("child点击" + position);
        final TuiJianAdapter adapt = (TuiJianAdapter) adapter;

        final int id = view.getId();
        if (id == R.id.container_1) {
            FooterBean footerBean = ((TuiJianAdapter) adapter).getData().get(position).getField(MulFields.BEAN);
            ToastUtil.show(footerBean.getLeftTitle());

            final TextView tv = (TextView) adapt.getViewByPosition(position, R.id.get_more);
            YoYo.with(Techniques.Landing)
                    .duration(600)
                    .playOn(tv);

        } else if (id == R.id.container_2) {
            FooterBean footerBean = adapt.getData().get(position).getField(MulFields.BEAN);
            ToastUtil.show(footerBean.getRightTitle());
            final int rvPosition = position - 1;
            final int currentIndex = adapt.getData().get(rvPosition).getField(MulFields.CURRENT_INDEX);
            final ArrayList<ArrayList<MulEntity>> data = adapt.getData().get(rvPosition).getField(MulFields.DATA);
            final int size = data.size();
            if (currentIndex >= data.size() - 1) {
                adapt.getData().get(rvPosition).setField(MulFields.CURRENT_INDEX, 0);
            } else {
                adapt.getData().get(rvPosition).setField(MulFields.CURRENT_INDEX, currentIndex + 1);
            }

            adapt.notifyItemChanged(rvPosition);
//            adapt.notifyItemChanged(position);
//            adapt.notifyDataSetChanged();


            final ImageView iv = (ImageView) adapt.getViewByPosition(position, R.id.iv);
            YoYo.with(new RotationAnimator())
                    .delay(300)
                    .duration(400)
                    .playOn(iv);
            final TextView tv = (TextView) adapt.getViewByPosition(position, R.id.show_more_tips);
            YoYo.with(Techniques.Wobble)
                    .duration(1000)
                    .playOn(tv);

        } else if (id == R.id.tv) {

            final int lastIndex = adapt.getData().get(position).getField(MulFields.TAG);
            final TodayHotBean todayHotBean = adapt.getData().get(position).getField(MulFields.BEAN);
            final List<String> data = todayHotBean.getTitles();
            final int currentIndex;
            final int size = data.size();
            if (lastIndex >= data.size() - 1) {
                currentIndex = 0;
            } else {
                currentIndex = lastIndex + 1;
            }
            adapt.getData().get(position).setField(MulFields.TAG, currentIndex);

            final TextView tv = (TextView) adapt.getViewByPosition(position, R.id.tv);
            tv.setText(data.get(currentIndex));
            YoYo.with(Techniques.FadeInDown)
                    .duration(400)
                    .playOn(tv);
            ToastUtil.show(data.get(currentIndex));
        } else if (id == R.id.iv) {
            ToastUtil.show("今日热点");
        }
    }
}
