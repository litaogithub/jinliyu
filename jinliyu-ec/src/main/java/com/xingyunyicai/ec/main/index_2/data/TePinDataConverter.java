package com.xingyunyicai.ec.main.index_2.data;

import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.ec.main.index_2.bean.TePinBean;

import java.util.ArrayList;

/**
 * Created by YuanJun on 2017/12/4 10:22
 */

public class TePinDataConverter extends DataConverter {

    @Override
    public ArrayList<MulEntity> convert() {

        for (int i = 0; i < 3; i++) {
            final TePinBean tePinBean1 = new TePinBean();
            tePinBean1.setCover("https://img.alicdn.com/bao/uploaded/i3/2208958975/TB2m3QHatHO8KJjSZFtXXchfXXa_!!2208958975.jpg_b.jpg");
            tePinBean1.setTitle("休闲零食夹心饼干威化饼");
            tePinBean1.setPrice("800");
            tePinBean1.setEvaluateCount("1563");
            tePinBean1.setGoodCount("90");
            final MulEntity entity1 = MulEntity.builder()
                    .setItemType(TePinItemType.GOODS)
                    .setBean(tePinBean1)
                    .build();

            final TePinBean tePinBean2 = new TePinBean();
            tePinBean2.setCover("https://img.alicdn.com/bao/uploaded/i3/2757793292/TB2WYEkXQfb_uJkSnaVXXXFmVXa_!!2757793292.jpg_b.jpg");
            tePinBean2.setTitle("三只松鼠休闲零食");
            tePinBean2.setPrice("120");
            tePinBean2.setEvaluateCount("1030");
            tePinBean2.setGoodCount("99");
            final MulEntity entity2 = MulEntity.builder()
                    .setItemType(TePinItemType.GOODS)
                    .setBean(tePinBean2)
                    .build();

            final TePinBean tePinBean3 = new TePinBean();
            tePinBean3.setCover("https://img.alicdn.com/bao/uploaded/i4/3328032806/TB2Xka4XjuhSKJjSspdXXc11XXa_!!3328032806.png_b.jpg");
            tePinBean3.setTitle("乐事休闲零食真脆薯条");
            tePinBean3.setPrice("69");
            tePinBean3.setEvaluateCount("163");
            tePinBean3.setGoodCount("86");
            final MulEntity entity3 = MulEntity.builder()
                    .setItemType(TePinItemType.GOODS)
                    .setBean(tePinBean3)
                    .build();

            ENTITIES.add(entity1);
            ENTITIES.add(entity2);
            ENTITIES.add(entity3);
        }
        return ENTITIES;
    }
}
