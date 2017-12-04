package com.xingyunyicai.ec.main.index_2.adapter;

import android.support.v7.widget.GridLayoutManager;

import com.xingyunyicai.core.delegates.DoDoDelegate;
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
    public LinkedHashMap<Integer, Integer> addItemTypes(ItemTypeBuilder builder) {
        return builder.addItemType(TePinItemType.GOODS, R.layout.item_goods)
                .build();
    }

    @Override
    public void handle(MulHolder holder, MulEntity entity) {
        switch (holder.getItemViewType()) {
            case TePinItemType.GOODS:
                final TePinBean tePinBean = entity.getField(MulFields.BEAN);
                String cover = tePinBean.getCover();
                String title = tePinBean.getTitle();
                String price = tePinBean.getPrice();
                String evaluateCount = tePinBean.getEvaluateCount();
                String goodCount = tePinBean.getGoodCount();

                holder.setText(R.id.title, title)
                        .setText(R.id.price, "￥" + price)
                        .setText(R.id.evaluateCount, evaluateCount + "条评价")
                        .setText(R.id.goodCount, goodCount + "%好评");

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
