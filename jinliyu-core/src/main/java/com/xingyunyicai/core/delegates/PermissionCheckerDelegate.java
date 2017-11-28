package com.xingyunyicai.core.delegates;

import android.Manifest;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.xingyunyicai.core.ui.location.DoDoLocation;
import com.xingyunyicai.core.util.toast.ToastUtil;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.delegates
 * 文件名：   PermissionCheckerDelegate
 * 创建者：   DoDo
 * 创建时间： 2017/8/30 16:16
 * 描述：     基于Android6.0的权限检查delegate
 */

@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    void startLocation(DoDoLocation location) {
//        ToastUtil.show("用户允许 定位权限！可开始定位");
        //360手机永远只走这个方法，小米正常
        location.onStart();

    }

    public void startLocationWithCheck(DoDoLocation location) {
        PermissionCheckerDelegatePermissionsDispatcher.startLocationWithPermissionCheck(this, location);
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    void onLocationDenied() {
        ToastUtil.show("用户拒绝 定位权限！");
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    void onLocationNever() {
        ToastUtil.show("用户永久拒绝 定位权限！");
    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    void showRationaleForLocation(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setTitle("用户授权")
                .setMessage("权限：ACCESS_FINE_LOCATION\n说明：定位")
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
