package com.xingyunyicai.qihoo.video.main.tuijian.data;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.ItemType;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.BannerBean;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.ContentBean;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.FilterBean;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.FooterBean;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.HeaderBean;
import com.xingyunyicai.qihoo.video.main.tuijian.bean.TodayHotBean;
import com.xingyunyicai.qihoo.video.main.tuijian.event.SearchHotwordEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian
 * 文件名：   TuiJianDataConverter
 * 创建者：   DoDo
 * 创建时间： 2017/9/21 16:47
 * 描述：     TODO
 */

public class TuiJianDataConverter extends DataConverter {

    private JSONObject mData = null;

    @Override
    public ArrayList<MulEntity> convert() {
        mData = JSON.parseObject(getJsonData()).getJSONObject("data").getJSONObject("data");
        if (mData == null) {
            return ENTITIES;
        }

        setSearchHotWord();

        addBanner();
        addTodayHot();
        addFilter();



        //blocks
        final JSONArray blocks = mData.getJSONArray("blocks");
        if (blocks == null) {
            return ENTITIES;
        }

        final int blocksSize = blocks.size();
        if (blocksSize <= 0) {
            return ENTITIES;
        }
        for (int i = 0; i < blocksSize; i++) {
            final JSONObject blocksBean = blocks.getJSONObject(i);
            //header
            final String bgCover = blocksBean.getString("bgCover");
            final String blockName = blocksBean.getString("blockName");
            final String tipsWord = blocksBean.getString("tipsWord");

            final HeaderBean headerBean = new HeaderBean();
            headerBean.setBgCover(bgCover);
            headerBean.setTitle(blockName);
            headerBean.setTips(tipsWord);
            final MulEntity headerEntity = MulEntity.builder()
                    .setHeader(headerBean)
                    .build();
            //最后一起添加

            final String blockType = blocksBean.getString("blockType");

            //嵌套rv
            final ArrayList<ArrayList<MulEntity>> nestRvData = new ArrayList<>();

            final JSONArray blockItems = blocksBean.getJSONArray("blockItems");
            final int blockItemsSize = blockItems.size();
            if (blockItemsSize <= 0) {
                continue;
            }
            for (int j = 0; j < blockItemsSize; j++) {
                final ArrayList<MulEntity> rvEntitys = new ArrayList<>();

                final JSONObject blockItemsBean = blockItems.getJSONObject(j);

                final JSONArray bigItems = blockItemsBean.getJSONArray("bigItems");
                final JSONArray smallItems = blockItemsBean.getJSONArray("smallItems");

                JSONArray contentItems;
                int contentItemTpye = 0;

                if (bigItems != null) {
                    contentItems = bigItems;
                    contentItemTpye = ItemType.CONTENT_1;

                    final int contentItemsSize = contentItems.size();
                    if (contentItemsSize <= 0) {
                        continue;
                    }
                    for (int m = 0; m < contentItemsSize; m++) {
                        final JSONObject contentItemsBean = contentItems.getJSONObject(m);
                        final String cover = contentItemsBean.getString("cover");
                        final String dynamicImg = contentItemsBean.getString("dynamicImg");
                        final String title = contentItemsBean.getString("title");
                        final String word = contentItemsBean.getString("word");
                        final JSONObject label = contentItemsBean.getJSONObject("label");
                        if (label == null) {
                            continue;
                        }
                        final String content = label.getString("content");
                        final String type = label.getString("type");

                        final ContentBean contentBean = new ContentBean();
                        contentBean.setImage(cover);
                        contentBean.setGif(dynamicImg);
                        contentBean.setTitle(title);
                        contentBean.setTips(word);
                        contentBean.setLabelContent(content);
                        contentBean.setLabelType(type);

                        final MulEntity contentEntity = MulEntity.builder()
                                .setItemType(contentItemTpye)
                                .setBean(contentBean)
                                .build();
                        rvEntitys.add(contentEntity);
                    }

                }

                if (smallItems != null) {
                    contentItems = smallItems;
                    if ("H".equals(blockType)) {
                        contentItemTpye = ItemType.CONTENT_2;
                    } else if ("V".equals(blockType)) {
                        contentItemTpye = ItemType.CONTENT_3;
                    } else {
                        continue;
                    }

                    final int contentItemsSize = contentItems.size();
                    if (contentItemsSize <= 0) {
                        continue;
                    }
                    for (int m = 0; m < contentItemsSize; m++) {
                        final JSONObject contentItemsBean = contentItems.getJSONObject(m);
                        final String cover = contentItemsBean.getString("cover");
                        final String dynamicImg = contentItemsBean.getString("dynamicImg");
                        final String title = contentItemsBean.getString("title");
                        final String word = contentItemsBean.getString("word");
                        final JSONObject label = contentItemsBean.getJSONObject("label");
                        if (label == null) {
                            continue;
                        }
                        final String content = label.getString("content");
                        final String type = label.getString("type");

                        final ContentBean contentBean = new ContentBean();
                        contentBean.setImage(cover);
                        contentBean.setGif(dynamicImg);
                        contentBean.setTitle(title);
                        contentBean.setTips(word);
                        contentBean.setLabelContent(content);
                        contentBean.setLabelType(type);

                        final MulEntity contentEntity = MulEntity.builder()
                                .setItemType(contentItemTpye)
                                .setBean(contentBean)
                                .build();
                        rvEntitys.add(contentEntity);
                    }
                } else {
                    continue;
                }

                nestRvData.add(rvEntitys);
            }

            final MulEntity nestRvEntity = MulEntity.builder()
                    .setItemType(ItemType.NEST_RECYCLER_VIEW)
                    .setData(nestRvData)
                    .build();
            //最后一起添加

            //footer
            final String getMore = blocksBean.getString("getMore");
            final String showMoreTips = blocksBean.getString("showMoreTips");

            final FooterBean footerBean = new FooterBean();
            footerBean.setLeftTitle(getMore);
            footerBean.setRightTitle(showMoreTips);
            final MulEntity footerEntity = MulEntity.builder()
                    .setFooter(footerBean)
                    .build();
            //最后一起添加

            //这里一起添加
            ENTITIES.add(headerEntity);
            ENTITIES.add(nestRvEntity);
            ENTITIES.add(footerEntity);

            //space
            addSpace();

            //广告
            if (i % 3 == 0) {
                addAD();
                addSpace();
            }
        }

        return ENTITIES;
    }

    /**
     * 热搜词
     */
    private void setSearchHotWord() {
        final String searchHotWord = mData.getString("searchHotword");
        if (!TextUtils.isEmpty(searchHotWord)) {
            EventBus.getDefault().post(new SearchHotwordEvent(searchHotWord));
        }
    }

    /**
     * banner轮播图
     */
    private void addBanner() {
        final JSONArray focus = mData.getJSONArray("focus");
        final int focusSize = focus.size();
        if (focusSize <= 0) {
            return;
        }
        final BannerBean bannerBean = new BannerBean();
        final ArrayList<String> titles = new ArrayList<>();
        final ArrayList<String> images = new ArrayList<>();
        for (int i = 0; i < focusSize; i++) {
            final JSONObject focusBean = focus.getJSONObject(i);
            final String title = focusBean.getString("title");
            final String image = focusBean.getString("cover");
            titles.add(title);
            images.add(image);
        }
        bannerBean.setTitles(titles);
        bannerBean.setImages(images);

        final MulEntity bannerEntity = MulEntity.builder()
                .setBanner(bannerBean)
                .build();
        ENTITIES.add(bannerEntity);
    }

    /**
     * 今日热点
     */
    private void addTodayHot() {
        final JSONObject todayhotBlock = mData.getJSONObject("todayhotBlock");
        if (todayhotBlock == null) {
            return;
        }
        final TodayHotBean todayHotBean = new TodayHotBean();
        final String tagCover = todayhotBlock.getString("tagCover");
        todayHotBean.setTagCover(tagCover);

        final JSONArray items = todayhotBlock.getJSONArray("items");
        final int itemsSize = items.size();
        if (itemsSize <= 0) {
            return;
        }
        final ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < itemsSize; i++) {
            final JSONObject itemsBean = items.getJSONObject(i);
            final String title = itemsBean.getString("title");
            titles.add(title);
        }
        todayHotBean.setTitles(titles);

        final MulEntity todayHotEntity = MulEntity.builder()
                .setItemType(TuiJianItemType.TODAY_HOT)
                .setBean(todayHotBean)
                .build();
        ENTITIES.add(todayHotEntity);

        addSpace();
    }

    /**
     * 导航条
     */
    private void addFilter() {
        final JSONObject filterBlock = mData.getJSONObject("filterBlock");
        if (filterBlock == null) {
            return;
        }
        final JSONArray items = filterBlock.getJSONArray("items");
        final int itemsSize = items.size();
        if (itemsSize <= 0) {
            return;
        }

        final ArrayList<MulEntity> filterData = new ArrayList<>();
        for (int i = 0; i < itemsSize; i++) {
            final JSONObject itemsBean = items.getJSONObject(i);
            final String icon = itemsBean.getString("icon");
            final String title = itemsBean.getString("title");

            final FilterBean filterBean = new FilterBean();
            filterBean.setIcon(icon);
            filterBean.setTitle(title);

            final MulEntity contentEntity = MulEntity.builder()
                    .setItemType(ItemType.IMAGE_TEXT)
                    .setSpanSize(3)
                    .setBean(filterBean)
                    .build();
            filterData.add(contentEntity);
        }

        final MulEntity filterEntity = MulEntity.builder()
                .setItemType(TuiJianItemType.FILTER)
                .setData(filterData)
                .build();
        ENTITIES.add(filterEntity);

        addSpace();
    }

    /**
     * 间隔条
     */
    private void addSpace() {
        final MulEntity spaceEntity = MulEntity.builder()
                .setSpace()
                .build();
        ENTITIES.add(spaceEntity);
    }

    /**
     * 广告
     */
    private void addAD() {
        final MulEntity ADEntity = MulEntity.builder()
                .setItemType(ItemType.AD_1)
                .build();
        ENTITIES.add(ADEntity);
    }
}
