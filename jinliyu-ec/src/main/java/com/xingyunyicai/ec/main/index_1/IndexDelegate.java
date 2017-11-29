package com.xingyunyicai.ec.main.index_1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.core.ui.image.GlideApp;
import com.xingyunyicai.ec.R;
import com.xingyunyicai.ec.R2;
import com.xingyunyicai.ec.main.index_1.bean.BannerBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by YuanJun on 2017/11/29 10:28
 */

public class IndexDelegate extends BaseBottomItemDelegate {

    @BindView(R2.id.banner)
    Banner mBanner = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    private void initBanner() {
        final BannerBean bannerBean = initBannerBean();
        mBanner.setImages(bannerBean.getImages())
                .setBannerTitles(bannerBean.getTitles())
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        GlideApp.with(context)
                                .load(path)
                                .placeholder(R.drawable.banner_background)
                                .apply(DEFAULT_OPTIONS)
                                .into(imageView);
                    }
                })
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setIndicatorGravity(BannerConfig.CENTER)
                .isAutoPlay(true)
                .setDelayTime(4000)
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        ToastUtils.showShort("banner点击：" + position);
                    }
                })
                .start();
    }

    private BannerBean initBannerBean() {
        final BannerBean bannerBean = new BannerBean();
        final ArrayList<String> titles = new ArrayList<>();
        final ArrayList<String> images = new ArrayList<>();
        titles.add("图片1");
        images.add("http://img.zcool.cn/community/01f5045663ca6c32f8754573b70db1.jpg@900w_1l_2o_100sh.jpg");
        titles.add("图片2");
        images.add("http://img.zcool.cn/community/010a3757a35e200000012e7ef37e76.jpg");
        titles.add("图片3");
        images.add("http://img.zcool.cn/community/014a2157ccd3ca0000018c1b7b53d1.jpg@900w_1l_2o_100sh.jpg");
        titles.add("图片1");
        images.add("http://img.zcool.cn/community/01f5045663ca6c32f8754573b70db1.jpg@900w_1l_2o_100sh.jpg");
        titles.add("图片2");
        images.add("http://img.zcool.cn/community/010a3757a35e200000012e7ef37e76.jpg");
        titles.add("图片3");
        images.add("http://img.zcool.cn/community/014a2157ccd3ca0000018c1b7b53d1.jpg@900w_1l_2o_100sh.jpg");
        bannerBean.setTitles(titles);
        bannerBean.setImages(images);
        return bannerBean;
    }

    //设置图片加载策略
    protected static final RequestOptions DEFAULT_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
//                    .transform(new RoundedCorners(6))//圆角 设置后会导致GIF丢帧
//                    .dontAnimate();//设置后会导致GIF无法播放

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }
}
