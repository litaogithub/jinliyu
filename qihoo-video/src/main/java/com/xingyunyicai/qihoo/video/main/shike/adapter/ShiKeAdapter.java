package com.xingyunyicai.qihoo.video.main.shike.adapter;

import android.support.annotation.IdRes;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
import com.xingyunyicai.core.util.dimen.DimenUtil;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.main.shike.bean.ShiKeBean;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.shike.adapter
 * 文件名：   ShiKeAdapter
 * 创建者：   DoDo
 * 创建时间： 2017/10/16 0:26
 * 描述：     TODO
 */

public class ShiKeAdapter extends MulAdapter {

    private final DoDoDelegate DELEGATE;
//    private final int screenWidth = DimenUtil.getScreenWidth();
//    //一列的宽度 = 屏幕宽度 - 图片之间的间隙 / 2 (两列)
//    private final int rowWidth = (screenWidth - DimenUtil.dp2px(6) * 3) / 2;


    protected ShiKeAdapter(List<MulEntity> data, DoDoDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
    }

    public static ShiKeAdapter create(List<MulEntity> data, DoDoDelegate delegate) {
        return new ShiKeAdapter(data, delegate);
    }

    public static ShiKeAdapter create(DataConverter converter, DoDoDelegate delegate) {
        return new ShiKeAdapter(converter.convert(), delegate);
    }

    @Override
    public LinkedHashMap<Integer, Integer> addItemTypes(ItemTypeBuilder builder) {
        return builder
                .addItemType(ItemType.CONTENT_2, R.layout.item_pager_shi_ke_content)
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
            case ItemType.CONTENT_2:
                final ShiKeBean shiKeBean = entity.getField(MulFields.BEAN);
                final String cover = shiKeBean.getCover();
                final String headImg = shiKeBean.getHeadImg();
                final String headName = shiKeBean.getHeadName();

                holder.setText(R.id.headName, headName);
                final ImageView coverImg = holder.getView(R.id.cover);
                final FrameLayout press = holder.getView(R.id.press);
                final int imageHeight;
                if (holder.getAdapterPosition() == 0) {
                    imageHeight = DimenUtil.dp2px(170);
                } else {
                    imageHeight = DimenUtil.dp2px(270);
                }

                coverImg.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, imageHeight));
                press.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, imageHeight));

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
