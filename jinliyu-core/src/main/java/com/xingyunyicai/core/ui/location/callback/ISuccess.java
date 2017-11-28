package com.xingyunyicai.core.ui.location.callback;

import com.baidu.location.BDLocation;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.location.callback
 * 文件名：   ISuccess
 * 创建者：   DoDo
 * 创建时间： 2017/10/20 6:49
 * 描述：     TODO
 */

public interface ISuccess {

    /**
     * 定位成功
     *
     * @param addr 地址
     */
    void onSuccess(String type, String addr, BDLocation location);

}
