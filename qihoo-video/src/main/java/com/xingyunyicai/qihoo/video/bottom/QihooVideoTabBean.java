package com.xingyunyicai.qihoo.video.bottom;

import com.xingyunyicai.core.delegates.bottom.bean.BottomTabImageTextBean;
import com.xingyunyicai.qihoo.video.R;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.bottom
 * 文件名：   QihooVideoTabBean
 * 创建者：   DoDo
 * 创建时间： 2017/9/18 10:00
 * 描述：     TODO
 */

public class QihooVideoTabBean extends BottomTabImageTextBean {

    public QihooVideoTabBean(CharSequence text) {
        super(text);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tab_qihoo_video;
    }
}
