package com.xingyunyicai.qihoo.video.main;

import com.xingyunyicai.core.delegates.bottom.BaseBottomContainerDelegate;
import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.core.delegates.bottom.bean.BaseBottomTabBean;
import com.xingyunyicai.core.delegates.bottom.builder.BottomBarParamsBuilder;
import com.xingyunyicai.core.delegates.bottom.builder.BottomBarParamsType;
import com.xingyunyicai.core.delegates.bottom.builder.BottomItemBuilder;
import com.xingyunyicai.core.delegates.bottom.builder.BottomTabBeanBuilder;
import com.xingyunyicai.core.util.toast.ToastUtil;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.bottom.QihooVideoTabBean;
import com.xingyunyicai.qihoo.video.main.baodian.BaoDianDelegate;
import com.xingyunyicai.qihoo.video.main.pindao.PinDaoDelegate;
import com.xingyunyicai.qihoo.video.main.shike.ShiKeDelegate;
import com.xingyunyicai.qihoo.video.main.tuijian.TuiJianDelegate;
import com.xingyunyicai.qihoo.video.main.wode.WoDeDelegate;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main
 * 文件名：   VideoBottomDelegate
 * 创建者：   DoDo
 * 创建时间： 2017/9/14 21:25
 * 描述：     TODO
 */

public class VideoBottomDelegate extends BaseBottomContainerDelegate {

    @Override
    public LinkedHashMap<BaseBottomTabBean, BaseBottomItemDelegate> setTabItems(BottomItemBuilder builder) {
        final LinkedHashMap<BaseBottomTabBean, BaseBottomItemDelegate> items = new LinkedHashMap<>();
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new QihooVideoTabBean("推荐"))
                .setImageSelector(R.drawable.bottom_tab_tui_jian_bg)
                .setTextSelector(R.color.selector_main_tab_text)
                .setContainerSelector(R.drawable.bottom_tab_container_bg)
                .setTextSize(10)
                .build(), new TuiJianDelegate());
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new QihooVideoTabBean("频道"))
                .setImageSelector(R.drawable.bottom_tab_pin_dao_bg)
                .setTextSelector(R.color.selector_main_tab_text)
                .setContainerSelector(R.drawable.bottom_tab_container_bg)
                .setTextSize(10)
                .build(), new PinDaoDelegate());
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new QihooVideoTabBean("时刻"))
                .setImageSelector(R.drawable.bottom_tab_shi_ke_bg)
                .setTextSelector(R.color.selector_main_tab_text)
                .setContainerSelector(R.drawable.bottom_tab_container_bg)
                .setTextSize(10)
                .build(), new ShiKeDelegate());
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new QihooVideoTabBean("爆点"))
                .setImageSelector(R.drawable.bottom_tab_bao_dian_bg)
                .setTextSelector(R.color.selector_main_tab_text)
                .setContainerSelector(R.drawable.bottom_tab_container_bg)
                .setTextSize(10)
                .build(), new BaoDianDelegate());
        items.put(BottomTabBeanBuilder.builder(getContext())
                .setTabBean(new QihooVideoTabBean("我的"))
                .setImageSelector(R.drawable.bottom_tab_wo_de_bg)
                .setTextSelector(R.color.selector_main_tab_text)
                .setContainerSelector(R.drawable.bottom_tab_container_bg)
                .setTextSize(10)
                .build(), new WoDeDelegate());

        return builder.addItems(items).build();
    }

    @Override
    public WeakHashMap<BottomBarParamsType, Object> setBottomBar(BottomBarParamsBuilder builder) {
        return builder.setBottomBarHeight(50).build();
    }

    @Override
    public boolean onTabSelected(int position, boolean isRepeat) {
        if (isRepeat) {
            ToastUtil.show("当前tab被重复点击");
        }
        return super.onTabSelected(position, isRepeat);
    }

    @Override
    public int setFirstPageIndex() {
        return 0;
    }

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            pop();
            ToastUtil.cancle();
//            getProxyActivity().finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            ToastUtil.showLong("再按一次退出 360影视大全");
        }
        return true;//消费掉该事件，不再向后传递
    }

}
