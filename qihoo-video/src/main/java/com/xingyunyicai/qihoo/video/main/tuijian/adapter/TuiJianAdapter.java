package com.xingyunyicai.qihoo.video.main.tuijian.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.fondesa.recyclerviewdivider.factories.VisibilityFactory;
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
import com.xingyunyicai.core.util.toast.ToastUtil;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.main.tuijian.TestPageDelegate;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.BannerBean;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.FooterBean;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.HeaderBean;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.TodayHotBean;
import com.xingyunyicai.qihoo.video.main.tuijian.data.TuiJianItemType;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian
 * 文件名：   TuiJianAdapter
 * 创建者：   DoDo
 * 创建时间： 2017/9/21 16:49
 * 描述：     TODO
 */

public class TuiJianAdapter extends MulAdapter implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    private final DoDoDelegate DELEGATE;//用于页面跳转

    protected TuiJianAdapter(List<MulEntity> data, DoDoDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
    }

    public static TuiJianAdapter create(List<MulEntity> data, DoDoDelegate delegate) {
        return new TuiJianAdapter(data, delegate);
    }

    public static TuiJianAdapter create(DataConverter converter, DoDoDelegate delegate) {
        return new TuiJianAdapter(converter.convert(), delegate);
    }

    @Override
    public LinkedHashMap<Integer, Integer> addItemTypes(ItemTypeBuilder builder) {
        return builder.addItemType(ItemType.HEADER, R.layout.item_header)
                .addItemType(ItemType.FOOTER, R.layout.item_footer)
                .addItemType(ItemType.SPACE, R.layout.item_space)
                .addItemType(ItemType.AD_1, R.layout.item_ad)
                .addItemType(ItemType.NEST_RECYCLER_VIEW, R.layout.item_nest_rv)
                .addItemType(ItemType.BANNER, R.layout.item_banner)
                .addItemType(TuiJianItemType.TODAY_HOT, R.layout.item_today_hot)
                .addItemType(TuiJianItemType.FILTER, R.layout.item_nest_rv)//和上面一样，用的嵌套rv布局
                .build();
    }

    @Override
    public void handle(MulHolder holder, MulEntity entity) {

        switch (holder.getItemViewType()) {
            case ItemType.HEADER:
                HeaderBean headerBean = entity.getField(MulFields.BEAN);
                holder.setText(R.id.block_name, headerBean.getTitle());
                final String imageUrl = headerBean.getBgCover();
                if (TextUtils.isEmpty(imageUrl)) {
                    holder.setGone(R.id.tips_word, true);
                    holder.setGone(R.id.iv, false);
                    holder.setText(R.id.tips_word, headerBean.getTips());
                } else {
                    holder.setGone(R.id.tips_word, false);
                    holder.setGone(R.id.iv, true);
                    GlideApp.with(mContext)
                            .load(imageUrl)
//                            .apply(DEFAULT_OPTIONS)
//                            .fitCenter()
                            .into((ImageView) holder.getView(R.id.iv));
                }


                break;
            case ItemType.FOOTER:
                FooterBean footerBean = entity.getField(MulFields.BEAN);
                final String rightTitle = footerBean.getRightTitle();
                if (!TextUtils.isEmpty(rightTitle)) {
                    holder.setText(R.id.show_more_tips, rightTitle);
                    holder.setGone(R.id.container_2, true);
                    holder.setGone(R.id.line, true);
                } else {
                    holder.setGone(R.id.container_2, false);
                    holder.setGone(R.id.line, false);
                }

                holder.setText(R.id.get_more, footerBean.getLeftTitle());

                //给子控件单独添加点击事件
                holder.addOnClickListener(R.id.container_1)
                        .addOnClickListener(R.id.container_2);

                break;
            case ItemType.NEST_RECYCLER_VIEW:

                final ArrayList<ArrayList<MulEntity>> data = entity.getField(MulFields.DATA);
                final int index = entity.getField(MulFields.CURRENT_INDEX);

                final RecyclerView recyclerView = holder.getView(R.id.recycler_view);

                TuiJianNestAdapter adapter = (TuiJianNestAdapter) recyclerView.getAdapter();
                //TODO 根据recyclerView.getAdapter()判断adapter是否为null最保险，bug最少，数据不错乱，分割线不变化，完美
                if (adapter == null) {
                    final GridLayoutManager manager = new GridLayoutManager(holder.itemView.getContext(), 12);
                    recyclerView.setLayoutManager(manager);
                    //解决当外层rv滑动到顶端时，滑动banner后再按住嵌套rv向下滑，导致swipeRefreshLayout进度条显示异常问题
                    recyclerView.setNestedScrollingEnabled(false);
                    //优化
                    recyclerView.setHasFixedSize(true);
                    //分割线
                    RecyclerViewDivider.with(holder.itemView.getContext())
                            .size(DimenUtil.dp2px(8))
                            .asSpace()//不绘制，只调整item间距
                            .visibilityFactory(new VisibilityFactory() {
                                @Override
                                public int displayDividerForItem(int groupCount, int groupIndex) {
                                    //只绘制垂直分割线
                                    return SHOW_ITEMS_ONLY;
                                }
                            })
                            .build()
                            .addTo(recyclerView);

                    adapter = new TuiJianNestAdapter(data.get(index));
                    adapter.bindToRecyclerView(recyclerView);

                    adapter.setOnItemClickListener(this);
                    adapter.setOnItemChildClickListener(this);

                } else {
                    adapter.setNewData(data.get(index));
                }

                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    BannerBean bannerBean = entity.getField(MulFields.BEAN);

                    final List<String> bannerImages = bannerBean.getImages();
                    final List<String> bannerTitles = bannerBean.getTitles();

                    final Banner banner = holder.getView(R.id.banner);
                    banner.setImages(bannerImages)
                            .setBannerTitles(bannerTitles)
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
                            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                            .setIndicatorGravity(BannerConfig.RIGHT)
                            .isAutoPlay(true)
                            .setDelayTime(4000)
                            .setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    ToastUtil.show("banner点击：" + position);
                                }
                            })
                            .start();

                    mIsInitBanner = true;
                }
                break;
            case TuiJianItemType.TODAY_HOT:
                TodayHotBean todayHotBean = entity.getField(MulFields.BEAN);
                final String TagCover = todayHotBean.getTagCover();
//                    loadImage(holder, TagCover, R.id.iv);
                GlideApp.with(mContext)
                        .load(TagCover)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((ImageView) holder.getView(R.id.iv));

                final List<String> titleList = todayHotBean.getTitles();
                final int tag = entity.getField(MulFields.TAG);
                holder.setText(R.id.tv, titleList.get(tag));

                holder.addOnClickListener(R.id.tv)
                        .addOnClickListener(R.id.iv);
                break;
            case TuiJianItemType.FILTER:
                final ArrayList<MulEntity> filterData = entity.getField(MulFields.DATA);

                final RecyclerView rv = holder.getView(R.id.recycler_view);

                TuiJianNestAdapter adapt = (TuiJianNestAdapter) rv.getAdapter();
                if (adapt == null) {
                    final GridLayoutManager manager = new GridLayoutManager(holder.itemView.getContext(), 12);
                    rv.setLayoutManager(manager);
                    //解决当外层rv滑动到顶端时，滑动banner后再按住嵌套rv向下滑，导致swipeRefreshLayout进度条显示异常问题
                    rv.setNestedScrollingEnabled(false);
                    //优化
                    rv.setHasFixedSize(true);
                    //分割线
                    RecyclerViewDivider.with(holder.itemView.getContext())
                            .size(DimenUtil.dp2px(0.5f))
                            .marginSize(DimenUtil.dp2px(12))
                            .color(Color.parseColor("#11111111"))
                            .visibilityFactory(new VisibilityFactory() {
                                @Override
                                public int displayDividerForItem(int groupCount, int groupIndex) {
                                    //只绘制垂直分割线
                                    return SHOW_ITEMS_ONLY;
                                }
                            })
                            .build()
                            .addTo(rv);

                    adapt = new TuiJianNestAdapter(filterData);
                    adapt.bindToRecyclerView(rv);

                    adapt.setOnItemClickListener(this);

                } else {
                    adapt.setNewData(filterData);
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        ToastUtil.show("嵌套rv item点击:" + position);
        DELEGATE.start(TestPageDelegate.create(String.valueOf(position)));

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ToastUtil.show("嵌套rv item子控件点击:" + position);
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        final int fullSpanSize = gridLayoutManager.getSpanCount();
        final int itemSpanSize = super.getSpanSize(gridLayoutManager, position);

        final int itemType = getData().get(position).getField(MulFields.ITEM_TYPE);
        switch (itemType) {
            case TuiJianItemType.TODAY_HOT:
            case TuiJianItemType.FILTER:
                return fullSpanSize;
            default:
                break;
        }

        return itemSpanSize;
    }
}
