package com.xingyunyicai.core.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.xingyunyicai.core.app.DoDo;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.util.storage
 * 文件名：   DoDoPreference
 * 创建者：   DoDo
 * 创建时间： 2017/9/3 3:51
 * 描述：     SharedPreference工具类
 */

public final class DoDoPreference {

    /**
     * 提示:
     * Activity.getPreferences(int mode)生成 Activity名.xml 用于Activity内部存储
     * PreferenceManager.getDefaultSharedPreferences(Context)生成 包名_preferences.xml
     * Context.getSharedPreferences(String name,int mode)生成name.xml
     */
    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(DoDo.getAppContext());
    private static final String APP_PREFERENCES_KEY = "profile";//TODO profile:配置文件
    public  static final String HISTORY_CITY = "historySearchCity";//历史访问城市记录

    private static SharedPreferences getAppPreference() {
        return PREFERENCES;
    }

    public static void setAppProfile(String val) {
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCES_KEY, val)
                .apply();
    }

    public static String getAppProfile() {
        return getAppPreference().getString(APP_PREFERENCES_KEY, null);
    }

//    public static JSONObject getAppProfileJson() {
//        final String profile = getAppProfile();
//        return JSON.parseObject(profile);
//    }

    public static void removeAppProfile() {
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void clearAppPreferences() {
        getAppPreference()
                .edit()
                .clear()
                .apply();
    }

    public static void setAppFlag(String key, boolean flag) {
        getAppPreference()
                .edit()
                .putBoolean(key, flag)
                .apply();
    }

    public static boolean getAppFlag(String key) {
        return getAppPreference()
                .getBoolean(key, false);
    }

    public static void addCustomAppProfile(String key, String val) {
        getAppPreference()
                .edit()
                .putString(key, val)
                .apply();
    }

    public static String getCustomAppProfile(String key) {
        return getAppPreference().getString(key, "");
    }

    public static void removeCustomAppProfile(@NonNull String key) {
        getAppPreference()
                .edit()
                .remove(key)
                .apply();
    }

}
