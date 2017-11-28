package com.xingyunyicai.qihoo.video.main.shike.adapter;

import android.support.annotation.IdRes;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.core.ui.image.GlideApp;
import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.ItemType;
import com.xingyunyicai.core.ui.recycler.ItemTypeBuilder;
import com.xingyunyicai.core.ui.recycler.MulAdapter;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.core.ui.recycler.MulFields;
import com.xingyunyicai.core.ui.recycler.MulHolder;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.main.shike.bean.FuJinBean;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.shike.adapter
 * 文件名：   FuJinAdapter
 * 创建者：   DoDo
 * 创建时间： 2017/10/16 3:38
 * 描述：     TODO
 */

public class FuJinAdapter extends MulAdapter {

    private final DoDoDelegate DELEGATE;

    protected FuJinAdapter(List<MulEntity> data, DoDoDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
    }

    public static FuJinAdapter create(List<MulEntity> data, DoDoDelegate delegate) {
        return new FuJinAdapter(data, delegate);
    }

    public static FuJinAdapter create(DataConverter converter, DoDoDelegate delegate) {
        return new FuJinAdapter(converter.convert(), delegate);
    }

    @Override
    public LinkedHashMap<Integer, Integer> addItemTypes(ItemTypeBuilder builder) {
        return builder
                .addItemType(ItemType.CONTENT_3, R.layout.item_pager_fu_jin_content)
                .build();
    }

    @Override
    protected void loadImage(MulHolder holder, String imageUrl, @IdRes int viewId) {
        GlideApp.with(mContext)
                .load(imageUrl)
                .placeholder((R.drawable.home_video_default_bg))
                .apply(DEFAULT_OPTIONS)
                .into((ImageView) holder.getView(viewId));
    }

    @Override
    public void handle(MulHolder holder, MulEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.CONTENT_3:
                final FuJinBean fuJinBean = entity.getField(MulFields.BEAN);
                final String cover = fuJinBean.getCover();
                final String headImg = fuJinBean.getHeadImg();
                final String headName = fuJinBean.getHeadName();
                final String region = fuJinBean.getRegion();

                holder.setText(R.id.headName, headName);
                holder.setText(R.id.region, region);

                loadImage(holder, cover, R.id.cover);
                GlideApp.with(mContext)
                        .load(headImg)
                        .apply(DEFAULT_OPTIONS)
                        .transform(new CircleCrop())
                        .into((ImageView) holder.getView(R.id.headImg));

                break;
            default:
                break;
        }
    }
}
