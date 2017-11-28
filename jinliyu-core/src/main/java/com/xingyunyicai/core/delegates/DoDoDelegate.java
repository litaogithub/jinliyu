package com.xingyunyicai.core.delegates;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates
 * 文件名：   DoDoDelegate
 * 创建者：   DoDo
 * 创建时间： 2017/8/30 16:18
 * 描述：     需要被其他delegate继承
 */

public abstract class DoDoDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends DoDoDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
