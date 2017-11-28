package com.xingyunyicai.qihoo.video.main.pindao.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.ItemType;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.qihoo.video.main.pindao.bean.ContentBean;

import java.util.ArrayList;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.pindao.data
 * 文件名：   PinDaoDataConverter
 * 创建者：   DoDo
 * 创建时间： 2017/10/14 17:29
 * 描述：     TODO
 */

public class PinDaoDataConverter extends DataConverter {

    private JSONObject mData = null;

    @Override
    public ArrayList<MulEntity> convert() {
        mData = JSON.parseObject(getJsonData()).getJSONObject("data").getJSONObject("data");
        if (mData == null) {
            return ENTITIES;
        }

        addMajor();
        addMinor();

        return ENTITIES;
    }

    /**
     * 主要的
     */
    private void addMajor() {
        final JSONArray major = mData.getJSONArray("major");
        if (major == null) {
            return;
        }

        final int majorSize = major.size();
        if (majorSize <= 0) {
            return;
        }
        //嵌套rv
        final ArrayList<MulEntity> majorData = new ArrayList<>();

        for (int i = 0; i < majorSize; i++) {
            final JSONObject majorBean = major.getJSONObject(i);
            final String icon = majorBean.getString("icon");
            final String title = majorBean.getString("title");

            final ContentBean contentBean = new ContentBean();
            contentBean.setIcon(icon);
            contentBean.setTitle(title);

            final MulEntity contentEntity = MulEntity.builder()
                    .setItemType(ItemType.IMAGE_TEXT)
                    .setSpanSize(1)
                    .setBean(contentBean)
                    .build();
            majorData.add(contentEntity);
        }

        final MulEntity majorEntity = MulEntity.builder()
                .setItemType(PinDaoItemType.MAJOR)
                .setData(majorData)
                .build();
        ENTITIES.add(majorEntity);

        addSpace();

    }

    /**
     * 次要的
     */
    private void addMinor() {
        final JSONArray minor = mData.getJSONArray("minor");
        if (minor == null) {
            return;
        }

        final int minorSize = minor.size();
        if (minorSize <= 0) {
            return;
        }
        //嵌套rv
        final ArrayList<MulEntity> minorData = new ArrayList<>();

        for (int i = 0; i < minorSize; i++) {
            final JSONObject minorBean = minor.getJSONObject(i);
            final String icon = minorBean.getString("icon");
            final String title = minorBean.getString("title");

            final ContentBean contentBean = new ContentBean();
            contentBean.setIcon(icon);
            contentBean.setTitle(title);

            final MulEntity contentEntity = MulEntity.builder()
                    .setItemType(ItemType.IMAGE_TEXT)
                    .setSpanSize(1)
                    .setBean(contentBean)
                    .build();
            minorData.add(contentEntity);
        }

        final MulEntity minorEntity = MulEntity.builder()
                .setItemType(PinDaoItemType.MINOR)
                .setData(minorData)
                .build();
        ENTITIES.add(minorEntity);
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
}
