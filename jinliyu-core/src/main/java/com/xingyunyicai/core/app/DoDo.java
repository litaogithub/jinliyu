package com.xingyunyicai.core.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.app
 * 文件名：   DoDo
 * 创建者：   DoDo
 * 创建时间： 2017/8/28 18:36
 * 描述：     对外的工具类 全部静态方法直接调用
 */

public final class DoDo {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getDodoConfigs();
    }

    public static Context getAppContext(){
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }


}
