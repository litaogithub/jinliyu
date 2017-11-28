package com.xingyunyicai.jinliyu;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.net.interceptors.DebugInterceptor;
import com.xingyunyicai.jinliyu.event.ShareEvent;
import com.xingyunyicai.jinliyu.interceptor.QihooInterceptor;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodocomic.example
 * 文件名：   ExampleApp
 * 创建者：   DoDo
 * 创建时间： 2017/8/28 18:37
 * 描述：     TODO
 */

public class ExampleApp extends Application {

    /**TODO
     * 插件：adb idea
     * 功能：清除缓存数据 重启adb
     * 快捷键：Ctrl+Alt+Shift+A
     */

    @Override
    public void onCreate() {
        super.onCreate();
        DoDo.init(this)
                .withIcon(new FontAwesomeModule())//自带的图标库，需添加相应依赖
//                .withIcon(new IoniconsModule())//自带的图标库，需添加相应依赖
//                .withIcon(new FontComicModule())//自定义图标库
                .withLoaderDelayed(1000)
                .withApiHost("http://192.168.75.101:5555/DoDoComic/")//以"/"结尾
                .withInterceptor(new DebugInterceptor("intercept", R.raw.test))
                .withInterceptor(new QihooInterceptor())
//                .withInterceptor(new JiGuangInterceptor())
//                .withInterceptor(new IMInterceptor())
                .withJavascriptInterface("dodo")//JS
//                .withWebEvent("Web调用了原生",new Web2AndroidEvent())
                .withWebEvent("share",new ShareEvent())
                .configure();

        Stetho.initializeWithDefaults(this);
        Utils.init(this);
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出  默认NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                // 开发环境：true时，遇到异常："Can not perform this action after onSaveInstanceState!"时，抛出，并Crash;
                // 生产环境：false时，不抛出，不会Crash，会捕获，可以在handleException()里监听到
                .debug(BuildConfig.DEBUG) // 实际场景建议.debug(BuildConfig.DEBUG)
                // 生产环境时，捕获上述异常（避免crash），会捕获
                // 建议在回调处上传下面异常到崩溃监控服务器
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();


//        DatabaseManager.getInstance().init(this);
        //百度地图
//        SDKInitializer.initialize(this);

        //开启极光推送
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);

        //融云IM
//        RongIMClient.init(this);
//        RongIM.init();

    }
}
