package com.xingyunyicai.core.util.toast;

import android.widget.Toast;

import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.util.CommonUtil;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.util.toast
 * 文件名：   ToastUtil
 * 创建者：   DoDo
 * 创建时间： 2017/9/18 2:36
 * 描述：     TODO
 */

public final class ToastUtil {

    private static Toast mToast = null;//全局唯一的Toast

    public static void show(Object message) {
        showShort(message);
    }

    public static void show(Object message, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(DoDo.getAppContext(), CommonUtil.toString(message), duration);
        } else {
            mToast.setText(CommonUtil.toString(message));
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    public static void showShort(Object message) {
        if (mToast == null) {
            mToast = Toast.makeText(DoDo.getAppContext(), CommonUtil.toString(message), Toast.LENGTH_SHORT);
        } else {
            mToast.setText(CommonUtil.toString(message));
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showLong(Object message) {
        if (mToast == null) {
            mToast = Toast.makeText(DoDo.getAppContext(), CommonUtil.toString(message), Toast.LENGTH_LONG);
        } else {
            mToast.setText(CommonUtil.toString(message));
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    public static void cancle() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public static void destroy() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }
}
