package com.xingyunyicai.qihoo.video.main.baodian.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.ItemType;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.qihoo.video.main.baodian.bean.ContentBean;

import java.util.ArrayList;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.baodian
 * 文件名：   BaoDianDataConverter
 * 创建者：   DoDo
 * 创建时间： 2017/10/15 0:02
 * 描述：     TODO
 */

public class BaoDianDataConverter extends DataConverter {

    private JSONObject mData = null;

    @Override
    public ArrayList<MulEntity> convert() {
        mData = JSON.parseObject(getJsonData()).getJSONObject("data").getJSONObject("data");
        if (mData == null) {
            return ENTITIES;
        }

        final JSONArray datas = mData.getJSONArray("datas");
        if (datas == null) {
            return ENTITIES;
        }

        final int dataSize = datas.size();
        if (dataSize <= 0) {
            return ENTITIES;
        }

        for (int i = 0; i < dataSize; i++) {
            final JSONObject dataBean = datas.getJSONObject(i);

            final ContentBean contentBean = new ContentBean();

            final String commentCount = dataBean.getString("commentCount");
            final String cover = dataBean.getString("cover");
            final String duration = dataBean.getString("duration");
            final String playCount = dataBean.getString("playCount");
            final String title = dataBean.getString("title");

            contentBean.setCommentCount(commentCount);
            contentBean.setCover(cover);
            contentBean.setDuration(duration);
            contentBean.setPlayCount(playCount);
            contentBean.setTitle(title);

            final JSONObject wemedia = dataBean.getJSONObject("wemedia");
            if (wemedia != null) {
                final String headImg = wemedia.getString("headImg");
                final String headName = wemedia.getString("title");

                contentBean.setHeadImg(headImg);
                contentBean.setHeadName(headName);
            }

            final MulEntity contentEntity = MulEntity.builder()
                    .setItemType(ItemType.CONTENT_1)
                    .setBean(contentBean)
                    .build();

            ENTITIES.add(contentEntity);

            addSpace();

//            if (i % 3 == 0 && i > 0) {
//                addAD();
//                addSpace();
//            }

        }

        return ENTITIES;
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
