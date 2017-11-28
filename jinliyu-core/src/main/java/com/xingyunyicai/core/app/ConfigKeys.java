package com.xingyunyicai.core.app;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.app
 * 文件名：   ConfigKeys
 * 创建者：   DoDo
 * 创建时间： 2017/8/28 18:48
 * 描述：     枚举类在整个应用程序中是唯一的单例，并且只会初始化一次。适合在多线程操作中进行安全的惰性单例的初始化（线程安全的懒汉模式）
 */

@SuppressWarnings("unused")
public enum ConfigKeys {

    //网络请求的域名
    API_HOST,
    //全局的上下文
    APPLICATION_CONTEXT,
    //初始化/配置完成的标记
    CONFIG_READY,
    //字体
    ICON,
    //拦截器
    INTERCEPTOR,
    //Handler 测试时方便做一些延时操作
    HANDLER,
    //Activity
    ACTIVITY,
    //加载延迟
    LOADER_DELAYED,
    //JS
    JAVASCRIPT_INTERFACE
}
