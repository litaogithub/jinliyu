package com.xingyunyicai.core.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xingyunyicai.core.delegates.web.event.BaseEvent;
import com.xingyunyicai.core.delegates.web.event.EventManager;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.app
 * 文件名：   Configurator
 * 创建者：   DoDo
 * 创建时间： 2017/8/28 18:42
 * 描述：     配置文件的存储和获取
 */

public class Configurator {

    //初始化XX之前，先定义存储它的空间
    //static final 全局初始化时就会初始化好(存疑？？？) 引用（或者叫实例）不可以被修改，但指向的对象可以
    //代码规范：大写 下划线分割
    private static final HashMap<Object, Object> DODO_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        //配置开始
        DODO_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        DODO_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    /**
     * 线程安全的懒汉模式 1.静态内部类 2.getInstance
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<Object, Object> getDodoConfigs() {
        return DODO_CONFIGS;
    }

    /**
     * 静态内部类 单例模式的初始化
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    //TODO 用 public final 修饰的方法，虚拟机会优化
    public final void configure() {
        //初始化字体图标库
        initIcons();
        //初始化Logger
        Logger.addLogAdapter(new AndroidLogAdapter());
        //配置完成
        DODO_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    /**
     * 配置API_HOST
     *
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        DODO_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    /**
     * 初始化字体图标
     */
    private void initIcons() {
        final int size = ICONS.size();
        if (size > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < size; i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 配置字体图标
     *
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 配置拦截器（单个）
     *
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        DODO_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 配置拦截器（多个）
     *
     * @param interceptors
     * @return
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);//合并两个集合，key相同则覆盖value
        DODO_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 配置Activity
     *
     * @param activity
     * @return
     */
    public final Configurator withActivity(Activity activity) {
        DODO_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    /**
     * 配置加载延迟
     *
     * @param delayed
     * @return
     */
    public final Configurator withLoaderDelayed(long delayed) {
        DODO_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    /**
     * 配置JS调用名
     */
    public final Configurator withJavascriptInterface(@NonNull String name) {
        DODO_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public final Configurator withWebEvent(@NonNull String name, @NonNull BaseEvent event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    /**
     * 检查配置是否完成
     */
    private void checkConfiguration() {
        //TODO 不会再修改的类变量和局部变量用final修饰,可以最大程度的避免修改一些本不该修改的变量，虚拟机也会进行优化
        final boolean isReady = (boolean) DODO_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call Configure");
        }
    }

    /**
     * 获取配置项
     *
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = DODO_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) DODO_CONFIGS.get(key);
    }

}





















