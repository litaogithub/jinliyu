package com.xingyunyicai.qihoo.video.main.tuijian.adapter;

import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.xingyunyicai.core.ui.image.GlideApp;
import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.ItemType;
import com.xingyunyicai.core.ui.recycler.ItemTypeBuilder;
import com.xingyunyicai.core.ui.recycler.MulAdapter;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.core.ui.recycler.MulFields;
import com.xingyunyicai.core.ui.recycler.MulHolder;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.ContentBean;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.FilterBean;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian
 * 文件名：   TuiJianNestAdapter
 * 创建者：   DoDo
 * 创建时间： 2017/9/24 6:06
 * 描述：     TODO
 */

public class TuiJianNestAdapter extends MulAdapter {

    protected TuiJianNestAdapter(List<MulEntity> data) {
        super(data);
    }

    public static TuiJianNestAdapter create(List<MulEntity> data) {
        return new TuiJianNestAdapter(data);
    }

    public static TuiJianNestAdapter create(DataConverter converter) {
        return new TuiJianNestAdapter(converter.convert());
    }

    @Override
    public LinkedHashMap<Integer, Integer> addItemTypes(ItemTypeBuilder builder) {
        return builder.addItemType(ItemType.CONTENT_1, R.layout.item_content_1)
                .addItemType(ItemType.CONTENT_2, R.layout.item_content_2)
                .addItemType(ItemType.CONTENT_3, R.layout.item_content_3)
                .addItemType(ItemType.IMAGE_TEXT, R.layout.item_image_text)
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
//        holder.setIsRecyclable(false);
//        Log.d("HAHAHA", "0");
        final String imageUrl;
        final String word;
        final String type;
        final String content;
        switch (holder.getItemViewType()) {
            case ItemType.CONTENT_1:
            case ItemType.CONTENT_2:
            case ItemType.CONTENT_3:
                ContentBean contentBean = entity.getField(MulFields.BEAN);
                imageUrl = contentBean.getGif();
                if (TextUtils.isEmpty(imageUrl)) {
                    loadImage(holder, contentBean.getImage(), R.id.cover);
                } else {
                    loadImage(holder, imageUrl, R.id.cover);
                }

                holder.setText(R.id.title, contentBean.getTitle());
                word = contentBean.getTips();
                if (TextUtils.isEmpty(word)) {
                    holder.setGone(R.id.word, false);
                } else {
                    holder.setGone(R.id.word, true);
                    holder.setText(R.id.word, word);
                }

                content = contentBean.getLabelContent();
                if (TextUtils.isEmpty(content)) {
                    holder.setGone(R.id.content_type_1, false)
                            .setGone(R.id.content_type_2, false);
                } else {
                    type = contentBean.getLabelType();
                    if ("1".equals(type)) {
                        holder.setGone(R.id.content_type_1, true)
                                .setGone(R.id.content_type_2, false)
                                .setText(R.id.content_type_1, content);
                    } else {
                        holder.setGone(R.id.content_type_1, false)
                                .setGone(R.id.content_type_2, true)
                                .setText(R.id.content_type_2, content);
                    }
                }

                break;
            case ItemType.IMAGE_TEXT:
                final FilterBean filterBean = entity.getField(MulFields.BEAN);
                holder.setText(R.id.tv, filterBean.getTitle());
                GlideApp.with(mContext)
                        .load(filterBean.getIcon())
                        .apply(DEFAULT_OPTIONS)
                        .transform(new CircleCrop())
                        .into((ImageView) holder.getView(R.id.iv));
                break;
            default:
                break;
        }
    }
}
