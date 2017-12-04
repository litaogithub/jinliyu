package com.xingyunyicai.core.ui.location.callback;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.location.callback
 * 文件名：   LocationCallback
 * 创建者：   DoDo
 * 创建时间： 2017/10/19 7:55
 * 描述：     TODO
 */

public final class LocationCallback extends BDAbstractLocationListener {

    private final LocationClient LOCATION_CLIENT;//用于自动停止定位
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IStop STOP;

    public LocationCallback(LocationClient locationClient,
                            ISuccess success,
                            IFailure failure,
                            IError error,
                            IStop stop) {
        this.LOCATION_CLIENT = locationClient;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.STOP = stop;
    }

    @Override
    public void onReceiveLocation(BDLocation location) {

        if (location == null) {
            return;
        }

        //获取定位结果
//        location.getTime();    //获取定位时间
//        location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
//        location.getLocType();    //获取定位类型
//        location.getLatitude();    //获取纬度信息
//        location.getLongitude();    //获取经度信息
//        location.getRadius();    //获取定位精准度
//        location.getAddrStr();    //获取地址信息
//        location.getCountry();    //获取国家信息
//        location.getCountryCode();    //获取国家码
//        location.getCity();    //获取城市信息
//        location.getCityCode();    //获取城市码
//        location.getDistrict();    //获取区县信息
//        location.getStreet();    //获取街道信息
//        location.getStreetNumber();    //获取街道码
//        location.getLocationDescribe();    //获取当前位置描述信息
//        location.getPoiList();    //获取当前位置周边POI信息
//
//        location.getBuildingID();    //室内精准定位下，获取楼宇ID
//        location.getBuildingName();    //室内精准定位下，获取楼宇名称
//        location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息

        if (location.getLocType() == BDLocation.TypeGpsLocation) {

            //当前为GPS定位结果，可获取以下信息
//            location.getSpeed();    //获取当前速度，单位：公里每小时
//            location.getSatelliteNumber();    //获取当前卫星数
//            location.getAltitude();    //获取海拔高度信息，单位米
//            location.getDirection();    //获取方向信息，单位度
////                location.
            if (SUCCESS != null) {
                SUCCESS.onSuccess("GPS", location.getAddrStr(), location);
            }

        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

            //当前为网络定位结果，可获取以下信息
//            location.getOperators();    //获取运营商信息
            if (SUCCESS != null) {
                SUCCESS.onSuccess("网络", location.getAddrStr(), location);
            }

        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

            //当前为网络定位结（离线）
            if (SUCCESS != null) {
                SUCCESS.onSuccess("离线", location.getAddrStr(), location);
            }

        } else if (location.getLocType() == BDLocation.TypeServerError) {

            //当前网络定位失败
            //可将定位唯一ID、IMEI、定位失败时间反馈至loc-bugs@baidu.com
            if (FAILURE != null) {
                FAILURE.onFailure("当前网络定位失败");
            }

        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

            //当前网络不通
            if (FAILURE != null) {
                FAILURE.onFailure("当前网络不通");
            }

        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

            //当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限
            //可进一步参考onLocDiagnosticMessage中的错误返回码

            //方法执行顺序：onLocDiagnosticMessage() → onReceiveLocation()
            if (FAILURE != null) {
                FAILURE.onFailure("当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限");
            }

        }

        //定位完成，自动停止
        if (LOCATION_CLIENT.isStarted()) {
            LOCATION_CLIENT.stop();
            LOCATION_CLIENT.unRegisterLocationListener(this);
        }

        if (STOP != null) {
            STOP.onStop();
        }
    }

    /**
     * 回调定位诊断信息，开发者可以根据相关信息解决定位遇到的一些问题
     * 自动回调，相同的diagnosticType只会回调一次
     *
     * @param locType           当前定位类型
     * @param diagnosticType    诊断类型（1~9）
     * @param diagnosticMessage 具体的诊断信息释义
     */
    @Override
    public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {

        if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS) {

            //建议打开GPS
            if (ERROR != null) {
                ERROR.onError("建议打开GPS");
            }

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI) {

            //建议打开wifi，不必连接，这样有助于提高网络定位精度！
            if (ERROR != null) {
                ERROR.onError("建议打开wifi，不必连接，这样有助于提高网络定位精度！");
            }

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION) {

            //定位权限受限，建议提示用户授予APP定位权限！
            if (ERROR != null) {
                ERROR.onError("定位权限受限，建议提示用户授予APP定位权限！");
            }

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET) {

            //网络异常造成定位失败，建议用户确认网络状态是否异常！
            if (ERROR != null) {
                ERROR.onError("网络异常造成定位失败，建议用户确认网络状态是否异常！");
            }

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE) {

            //手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！
            if (ERROR != null) {
                ERROR.onError("手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！");
            }

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI) {

            //无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！
            if (ERROR != null) {
                ERROR.onError("无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！");
            }

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH) {

            //无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！
            if (ERROR != null) {
                ERROR.onError("无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！");
            }

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_SERVER_FAIL) {

            //百度定位服务端定位失败
            //建议反馈location.getLocationID()和大体定位时间到loc-bugs@baidu.com
            if (ERROR != null) {
                ERROR.onError("百度定位服务端定位失败");
            }

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN) {

            //无法获取有效定位依据，但无法确定具体原因
            //建议检查是否有安全软件屏蔽相关定位权限
            //或调用LocationClient.restart()重新启动后重试！
            if (ERROR != null) {
                ERROR.onError("无法获取有效定位依据，但无法确定具体原因");
            }

        }
    }
}