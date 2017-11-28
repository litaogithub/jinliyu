package com.xingyunyicai.core.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.recycler
 * 文件名：   RgbValue
 * 创建者：   DoDo
 * 创建时间： 2017/9/11 12:39
 * 描述：     TODO
 */
@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }

}
