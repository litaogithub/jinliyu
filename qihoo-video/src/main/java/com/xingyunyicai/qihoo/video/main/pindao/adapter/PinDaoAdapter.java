package com.xingyunyicai.qihoo.video.main.pindao.adapter;

import android.animation.Animator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.fondesa.recyclerviewdivider.factories.VisibilityFactory;
import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.ItemType;
import com.xingyunyicai.core.ui.recycler.ItemTypeBuilder;
import com.xingyunyicai.core.ui.recycler.MulAdapter;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.core.ui.recycler.MulFields;
import com.xingyunyicai.core.ui.recycler.MulHolder;
import com.xingyunyicai.core.util.dimen.DimenUtil;
import com.xingyunyicai.core.util.toast.ToastUtil;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.main.pindao.data.PinDaoItemType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.pindao.adapter
 * 文件名：   PinDaoAdapter
 * 创建者：   DoDo
 * 创建时间： 2017/10/14 17:31
 * 描述：     TODO
 */

public class PinDaoAdapter extends MulAdapter implements BaseQuickAdapter.OnItemClickListener {

    private final DoDoDelegate DELEGATE;//用于页面跳转

    protected PinDaoAdapter(List<MulEntity> data, DoDoDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
    }

    public static PinDaoAdapter create(List<MulEntity> data, DoDoDelegate delegate) {
        return new PinDaoAdapter(data, delegate);
    }

    public static PinDaoAdapter create(DataConverter converter, DoDoDelegate delegate) {
        return new PinDaoAdapter(converter.convert(), delegate);
    }

    @Override
    public LinkedHashMap<Integer, Integer> addItemTypes(ItemTypeBuilder builder) {
        return builder
                .addItemType(ItemType.SPACE, R.layout.item_space)
                .addItemType(PinDaoItemType.MAJOR, R.layout.item_major_rv)
                .addItemType(PinDaoItemType.MINOR, R.layout.item_minor_rv)
                .build();
    }

    @Override
    public void handle(MulHolder holder, MulEntity entity) {
        switch (holder.getItemViewType()) {
            case PinDaoItemType.MAJOR:
            case PinDaoItemType.MINOR:
                final ArrayList<MulEntity> majorData = entity.getField(MulFields.DATA);

                final RecyclerView rv = holder.getView(R.id.recycler_view);

                PinDaoNestAdapter adapt = (PinDaoNestAdapter) rv.getAdapter();
                if (adapt == null) {
                    final GridLayoutManager manager = new GridLayoutManager(holder.itemView.getContext(), 4);
                    rv.setLayoutManager(manager);
                    //解决当外层rv滑动到顶端时，滑动banner后再按住嵌套rv向下滑，导致swipeRefreshLayout进度条显示异常问题
                    rv.setNestedScrollingEnabled(false);
                    //优化
                    rv.setHasFixedSize(true);
                    //分割线
                    RecyclerViewDivider.with(holder.itemView.getContext())
                            .size(DimenUtil.dp2px(18))
                            .asSpace()//不绘制，只调整item间距
                            .visibilityFactory(new VisibilityFactory() {
                                @Override
                                public int displayDividerForItem(int groupCount, int groupIndex) {
                                    //只绘制水平分割线
                                    return SHOW_GROUP_ONLY;
                                }
                            })
                            .build()
                            .addTo(rv);

                    adapt = new PinDaoNestAdapter(majorData);
                    adapt.bindToRecyclerView(rv);

                    adapt.setOnItemClickListener(this);

                } else {
                    adapt.setNewData(majorData);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
        RelativeLayout rl = (RelativeLayout) adapter.getViewByPosition(position, R.id.rl);
        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
//                        DELEGATE.start(TestPageDelegate.create(String.valueOf(position)));
                    }
                })
                .playOn(rl);
        TextView tv = (TextView) adapter.getViewByPosition(position, R.id.tv);
        ToastUtil.show(tv.getText().toString());
    }
}
