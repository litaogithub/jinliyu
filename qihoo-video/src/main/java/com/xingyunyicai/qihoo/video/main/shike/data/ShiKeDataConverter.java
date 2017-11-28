package com.xingyunyicai.qihoo.video.main.shike.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.ItemType;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.qihoo.video.main.shike.bean.ShiKeBean;

import java.util.ArrayList;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.shike.data
 * 文件名：   ShiKeDataConverter
 * 创建者：   DoDo
 * 创建时间： 2017/10/16 0:24
 * 描述：     TODO
 */

public class ShiKeDataConverter extends DataConverter {

    private JSONObject mData = null;

    @Override
    public ArrayList<MulEntity> convert() {
        mData = JSON.parseObject(getJsonData()).getJSONObject("data").getJSONObject("data");
        if (mData == null) {
            return ENTITIES;
        }

        final JSONArray list = mData.getJSONArray("list");
        if (list == null) {
            return ENTITIES;
        }

        final int listSize = list.size();
        if (listSize <= 0) {
            return ENTITIES;
        }

        for (int i = 0; i < listSize; i++) {
            final JSONObject listBean = list.getJSONObject(i);
            final JSONObject auther = listBean.getJSONObject("auther");
            if (auther == null) {
                continue;
            }
            final String headImg = auther.getString("img");
            final String headName = auther.getString("title");

            final JSONObject base = listBean.getJSONObject("base");
            if (base == null) {
                continue;
            }
            final String cover = base.getString("img");

            final ShiKeBean shiKeBean = new ShiKeBean();
            shiKeBean.setHeadImg(headImg);
            shiKeBean.setHeadName(headName);
            shiKeBean.setCover(cover);

            final MulEntity entity = MulEntity.builder()
                    .setItemType(ItemType.CONTENT_2)
                    .setBean(shiKeBean)
                    .build();
            ENTITIES.add(entity);

        }

        return ENTITIES;
    }

}
