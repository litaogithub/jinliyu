package com.xingyunyicai.core.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.banner
 * 文件名：   ImageHolder
 * 创建者：   DoDo
 * 创建时间： 2017/9/3 5:55
 * 描述：     TODO
 */

public class ImageHolder implements Holder<String>{

    private AppCompatImageView mImageView = null;

    private static final RequestOptions BANNER_OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context)
                .load(data)
                .apply(BANNER_OPTIONS)
                .into(mImageView);
    }
}
