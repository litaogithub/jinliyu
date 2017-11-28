package com.xingyunyicai.core.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.banner
 * 文件名：   HolderCreator
 * 创建者：   DoDo
 * 创建时间： 2017/9/3 5:55
 * 描述：     TODO
 */

public class HolderCreator implements CBViewHolderCreator<ImageHolder> {
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
