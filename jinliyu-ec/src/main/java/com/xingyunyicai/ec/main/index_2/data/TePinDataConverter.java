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
            tePinBean1.setTitle("盼盼 零食薯片 膨化食品 麦香鸡味块60g*3");
            tePinBean1.setFrank(true);
            tePinBean1.setSold("163");
            tePinBean1.setNewPrice("10.05");
            tePinBean1.setOldPrice("28.50");
            final MulEntity entity1 = MulEntity.builder()
                    .setItemType(TePinItemType.GOODS)
                    .setBean(tePinBean1)
                    .build();

            final TePinBean tePinBean2 = new TePinBean();
            tePinBean2.setCover("https://img.alicdn.com/bao/uploaded/i3/2757793292/TB2WYEkXQfb_uJkSnaVXXXFmVXa_!!2757793292.jpg_b.jpg");
            tePinBean2.setTitle("三只松鼠 休闲零食 干脆面串烧味 小贱拉面丸子85/袋");
            tePinBean2.setFrank(false);
            tePinBean2.setSold("373");
            tePinBean2.setNewPrice("11.92");
            tePinBean2.setOldPrice("35.50");
            final MulEntity entity2 = MulEntity.builder()
                    .setItemType(TePinItemType.GOODS)
                    .setBean(tePinBean2)
                    .build();

            final TePinBean tePinBean3 = new TePinBean();
            tePinBean3.setCover("https://img.alicdn.com/bao/uploaded/i4/3328032806/TB2Xka4XjuhSKJjSspdXXc11XXa_!!3328032806.png_b.jpg");
            tePinBean3.setTitle("宇仔 素大刀肉辣条 休闲零食品 麻辣小吃礼包258");
            tePinBean3.setFrank(true);
            tePinBean3.setSold("260");
            tePinBean3.setNewPrice("7.90");
            tePinBean3.setOldPrice("13.30");
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
