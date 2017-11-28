package com.xingyunyicai.qihoo.video.main.baodian.adapter;

import android.support.annotation.IdRes;
import android.widget.ImageView;

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
import com.xingyunyicai.qihoo.video.main.baodian.bean.ContentBean;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.baodian.adapter
 * 文件名：   BaoDianAdapter
 * 创建者：   DoDo
 * 创建时间： 2017/10/15 0:03
 * 描述：     TODO
 */

public class BaoDianAdapter extends MulAdapter {

    private final DoDoDelegate DELEGATE;//用于页面跳转

    protected BaoDianAdapter(List<MulEntity> data, DoDoDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
    }

    public static BaoDianAdapter create(List<MulEntity> data, DoDoDelegate delegate) {
        return new BaoDianAdapter(data, delegate);
    }

    public static BaoDianAdapter create(DataConverter converter, DoDoDelegate delegate) {
        return new BaoDianAdapter(converter.convert(), delegate);
    }

    @Override
    public LinkedHashMap<Integer, Integer> addItemTypes(ItemTypeBuilder builder) {
        return builder
                .addItemType(ItemType.AD_1, R.layout.item_ad)
                .addItemType(ItemType.SPACE, R.layout.item_space)
                .addItemType(ItemType.CONTENT_1, R.layout.item_bao_dian_content)
                .build();
    }

    @Override
    protected void loadImage(MulHolder holder, String imageUrl, @IdRes int viewId) {
        GlideApp.with(mContext)
                .load(imageUrl)
                .placeholder((R.drawable.banner_background))
                .apply(DEFAULT_OPTIONS)
                .into((ImageView) holder.getView(viewId));
    }

    @Override
    public void handle(MulHolder holder, MulEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.CONTENT_1:
                final ContentBean contentBean = entity.getField(MulFields.BEAN);
                final String title = contentBean.getTitle();
                final String duration = contentBean.getDuration();
                final String cover = contentBean.getCover();//背景图
                final String headImg = contentBean.getHeadImg();
                final String headName = contentBean.getHeadName();
                final String playCount = contentBean.getPlayCount();//播放次数
                final String commentCount = contentBean.getCommentCount();//评论

                holder.setText(R.id.title, title)
                        .setText(R.id.duration, duration)
                        .setText(R.id.headName, headName)
                        .setText(R.id.playCount, playCount + "播放")
                        .setText(R.id.commentCount, commentCount);

                loadImage(holder, cover, R.id.cover);
//                GlideApp.with(mContext)
//                        .load(headImg)
//                        .apply(DEFAULT_OPTIONS)
//                        .transform(new CircleCrop())
//                        .into((ImageView) holder.getView(R.id.headImg));

                break;
            default:
                break;
        }
    }
}
