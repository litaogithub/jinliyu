package com.xingyunyicai.core.ui.recycler;

import android.support.annotation.IdRes;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xingyunyicai.core.ui.image.GlideApp;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.recycler
 * 文件名：   MulAdapter
 * 创建者：   DoDo
 * 创建时间： 2017/9/10 3:38
 * 描述：     TODO
 */

public abstract class MulAdapter extends BaseMultiItemQuickAdapter<MulEntity, MulHolder>
        implements BaseQuickAdapter.SpanSizeLookup {

    //确保初始化一次Banner，防止重复Item加载
    protected boolean mIsInitBanner = false;
    //设置图片加载策略
    protected static final RequestOptions DEFAULT_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
//                    .transform(new RoundedCorners(6))//圆角 设置后会导致GIF丢帧
//                    .dontAnimate();//设置后会导致GIF无法播放

    private final LinkedHashMap<Integer, Integer> ITEM_TYPES = new LinkedHashMap<>();

    /**
     * 设置itemType和layout之间的映射关系
     *
     * @param builder
     * @return
     */
    public abstract LinkedHashMap<Integer, Integer> addItemTypes(ItemTypeBuilder builder);

    /**
     * 处理数据
     *
     * @param holder
     * @param entity
     */
    public abstract void handle(MulHolder holder, MulEntity entity);

    /**
     * 加载图片
     *
     * @param holder
     * @param imageUrl
     * @param viewId
     */
    protected void loadImage(MulHolder holder, String imageUrl, @IdRes int viewId) {
        GlideApp.with(mContext)
                .load(imageUrl)
                .apply(DEFAULT_OPTIONS)
                .into((ImageView) holder.getView(viewId));

    }

    protected void loadGif(MulHolder holder, String imageUrl, @IdRes int viewId) {
        Glide.with(mContext)
//                .asGif()
                .load(imageUrl)
                .apply(DEFAULT_OPTIONS)
                .into((ImageView) holder.getView(viewId));

    }

    protected MulAdapter(List<MulEntity> data) {
        super(data);
        init();
    }

    @Override
    protected MulHolder createBaseViewHolder(View view) {
        return MulHolder.create(view);
    }

    private void init() {
        final ItemTypeBuilder builder = ItemTypeBuilder.builder();
        final LinkedHashMap<Integer, Integer> itemTypes = addItemTypes(builder);
        ITEM_TYPES.putAll(itemTypes);
        for (Map.Entry<Integer, Integer> item : ITEM_TYPES.entrySet()) {
            final int type = item.getKey();
            final int layoutId = item.getValue();
            //设置多item布局
            //TODO 这里添加的ItenType必须在DataConverter中添加过的，也就是说实体类MulItemEntity中必须存在相应的ItemType，否则会报Resources$NotFoundException: Resource ID #0xfffffe6c异常，因为找不到实体类中ItemType对应的布局文件
            addItemType(type, layoutId);
        }

        //设置宽度监听
        setSpanSizeLookup(this);
//        setNotDoAnimationCount(10);
        //开启载入动画
//        openLoadAnimation();
        //多次执行动画
//        isFirstOnly(false);
    }

    @Override
    protected void convert(MulHolder helper, MulEntity item) {
        handle(helper, item);
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        final int fullSpanSize = gridLayoutManager.getSpanCount();
        final int itemSpanSize = getData().get(position).getField(MulFields.SPAN_SIZE);
        //spanSize非法，默认占满
        if ((itemSpanSize < 0) || (itemSpanSize > fullSpanSize)) {
            return fullSpanSize;
        }

        //用户手动设置了
        if (itemSpanSize != 0) {
            return itemSpanSize;
        }

        //用户没有设置，itemSpanSize=0
        final int itemType = getData().get(position).getField(MulFields.ITEM_TYPE);
        switch (itemType) {
            case ItemType.HEADER:
            case ItemType.FOOTER:
            case ItemType.BANNER:
            case ItemType.SPACE:
            case ItemType.AD_1:
            case ItemType.CONTENT_1:
            case ItemType.NEST_RECYCLER_VIEW:
                return fullSpanSize;
            case ItemType.CONTENT_2:
            case ItemType.AD_2:
                return fullSpanSize / 2;
            case ItemType.CONTENT_3:
                return fullSpanSize / 3;
            default:
                break;
        }

        return fullSpanSize;

    }

    /**
     * 重新加载banner
     *
     */
    public void reLoadBanner() {
        mIsInitBanner = false;
    }

}
