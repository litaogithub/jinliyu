package com.xingyunyicai.ec.main.index_2.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;

import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.core.ui.image.GlideApp;
import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.ItemTypeBuilder;
import com.xingyunyicai.core.ui.recycler.MulAdapter;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.core.ui.recycler.MulFields;
import com.xingyunyicai.core.ui.recycler.MulHolder;
import com.xingyunyicai.ec.R;
import com.xingyunyicai.ec.main.index_2.bean.TePinBean;
import com.xingyunyicai.ec.main.index_2.data.TePinItemType;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by YuanJun on 2017/12/4 10:24
 */

public class TePinAdapter extends MulAdapter {

    private final DoDoDelegate DELEGATE;//用于页面跳转

    protected TePinAdapter(List<MulEntity> data, DoDoDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
    }

    public static TePinAdapter create(List<MulEntity> data, DoDoDelegate delegate) {
        return new TePinAdapter(data, delegate);
    }

    public static TePinAdapter create(DataConverter converter, DoDoDelegate delegate) {
        return new TePinAdapter(converter.convert(), delegate);
    }

    @Override
    protected void loadImage(MulHolder holder, String imageUrl, @IdRes int viewId) {
        GlideApp.with(mContext)
                .load(imageUrl)
                .apply(DEFAULT_OPTIONS)
                .placeholder(R.drawable.banner_background)
                .into((ImageView) holder.getView(viewId));

    }

    @Override
    public LinkedHashMap<Integer, Integer> addItemTypes(ItemTypeBuilder builder) {
        return builder.addItemType(TePinItemType.GOODS, R.layout.item_goods)
                .build();
    }

    @Override
    public void handle(MulHolder holder, MulEntity entity) {
        switch (holder.getItemViewType()) {
            case TePinItemType.GOODS:
                final TePinBean tePinBean = entity.getField(MulFields.BEAN);
                final String cover = tePinBean.getCover();
                final String title = tePinBean.getTitle();
                final boolean frank = tePinBean.getFrank();
                final String sold = tePinBean.getSold();
                final String newPrice = tePinBean.getNewPrice();
                final String oldPrice = tePinBean.getOldPrice();

                holder.setGone(R.id.frank, frank);

                holder.setText(R.id.title, title)
                        .setText(R.id.sold, "已售" + sold + "件")
                        .setText(R.id.newPrice, "￥" + newPrice)
                        .setText(R.id.oldPrice, "￥" + oldPrice);

                loadImage(holder, cover, R.id.cover);

                break;
            default:
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        final int fullSpanSize = gridLayoutManager.getSpanCount();
        final int itemSpanSize = super.getSpanSize(gridLayoutManager, position);

        final int itemType = getData().get(position).getField(MulFields.ITEM_TYPE);
        switch (itemType) {
            case TePinItemType.GOODS:
                return fullSpanSize;
            default:
                break;
        }

        return itemSpanSize;
    }
}
