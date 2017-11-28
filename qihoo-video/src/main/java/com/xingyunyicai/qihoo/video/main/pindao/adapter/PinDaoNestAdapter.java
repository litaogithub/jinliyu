package com.xingyunyicai.qihoo.video.main.pindao.adapter;

import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xingyunyicai.core.ui.image.GlideApp;
import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.ItemType;
import com.xingyunyicai.core.ui.recycler.ItemTypeBuilder;
import com.xingyunyicai.core.ui.recycler.MulAdapter;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.core.ui.recycler.MulFields;
import com.xingyunyicai.core.ui.recycler.MulHolder;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.main.pindao.bean.ContentBean;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.pindao.adapter
 * 文件名：   PinDaoNestAdapter
 * 创建者：   DoDo
 * 创建时间： 2017/10/14 18:01
 * 描述：     TODO
 */

public class PinDaoNestAdapter extends MulAdapter {

    protected PinDaoNestAdapter(List<MulEntity> data) {
        super(data);
    }

    public static PinDaoNestAdapter create(List<MulEntity> data) {
        return new PinDaoNestAdapter(data);
    }

    public static PinDaoNestAdapter create(DataConverter converter) {
        return new PinDaoNestAdapter(converter.convert());
    }

    @Override
    public LinkedHashMap<Integer, Integer> addItemTypes(ItemTypeBuilder builder) {
        return builder
                .addItemType(ItemType.IMAGE_TEXT, R.layout.item_pin_dao_content)
                .build();
    }

    @Override
    public void handle(MulHolder holder, MulEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.IMAGE_TEXT:
                final ContentBean contentBean = entity.getField(MulFields.BEAN);
                holder.setText(R.id.tv, contentBean.getTitle());
                GlideApp.with(mContext)
                        .load(contentBean.getIcon())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((ImageView) holder.getView(R.id.iv));
                break;
            default:
                break;
        }
    }
}
