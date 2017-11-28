package com.xingyunyicai.core.ui.location;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.util.toast.ToastUtil;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.location
 * 文件名：   LocationHelper
 * 创建者：   DoDo
 * 创建时间： 2017/10/19 6:46
 * 描述：     TODO
 */

public class LocationHelper {


    private LocationHelper() {
        initLocation();
        BaiduLocationHolder.CLIENT.registerLocationListener(BaiduLocationHolder.LISTENER);
    }

    private static final class LocationHolder {
        private static final LocationHelper INSTANCE = new LocationHelper();
    }

    public static LocationHelper getInstance() {
        return LocationHolder.INSTANCE;
    }

    private static final class BaiduLocationHolder {
        private static final LocationClient CLIENT = new LocationClient(DoDo.getAppContext());
        private static final BDAbstractLocationListener LISTENER = new MyLocationListener();
        private static final LocationClientOption OPTION = new LocationClientOption();


    }

    /**
     * 开始定位
     *
     * 注：定位结束会自动停止
     */
    public void start() {
        BaiduLocationHolder.CLIENT.start();
    }

    /**
     * 终止
     *
     * 注：在onDestroy()方法里调用
     */
    public void stop() {
        if (BaiduLocationHolder.CLIENT != null) {
            BaiduLocationHolder.CLIENT.unRegisterLocationListener(BaiduLocationHolder.LISTENER);
            if (BaiduLocationHolder.CLIENT.isStarted()) {
                BaiduLocationHolder.CLIENT.stop();
            }
        }

    }

    private static class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            if (location == null) {
                return;
            }

            //获取定位结果
            location.getTime();    //获取定位时间
            location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
            location.getLocType();    //获取定位类型
            location.getLatitude();    //获取纬度信息
            location.getLongitude();    //获取经度信息
            location.getRadius();    //获取定位精准度
            location.getAddrStr();    //获取地址信息
            location.getCountry();    //获取国家信息
            location.getCountryCode();    //获取国家码
            location.getCity();    //获取城市信息
            location.getCityCode();    //获取城市码
            location.getDistrict();    //获取区县信息
            location.getStreet();    //获取街道信息
            location.getStreetNumber();    //获取街道码
            location.getLocationDescribe();    //获取当前位置描述信息
            location.getPoiList();    //获取当前位置周边POI信息

            location.getBuildingID();    //室内精准定位下，获取楼宇ID
            location.getBuildingName();    //室内精准定位下，获取楼宇名称
            location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息

            if (location.getLocType() == BDLocation.TypeGpsLocation) {

                //当前为GPS定位结果，可获取以下信息
                location.getSpeed();    //获取当前速度，单位：公里每小时
                location.getSatelliteNumber();    //获取当前卫星数
                location.getAltitude();    //获取海拔高度信息，单位米
                location.getDirection();    //获取方向信息，单位度
//                location.

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

                //当前为网络定位结果，可获取以下信息
                location.getOperators();    //获取运营商信息

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                //当前为网络定位结果

            } else if (location.getLocType() == BDLocation.TypeServerError) {

                //当前网络定位失败
                //可将定位唯一ID、IMEI、定位失败时间反馈至loc-bugs@baidu.com

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                //当前网络不通

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                //当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限
                //可进一步参考onLocDiagnosticMessage中的错误返回码

            }

            //定位完成，自动停止
            if (BaiduLocationHolder.CLIENT.isStarted()) {
                BaiduLocationHolder.CLIENT.stop();
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
                ToastUtil.show("建议打开GPS");

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI) {

                //建议打开wifi，不必连接，这样有助于提高网络定位精度！
                ToastUtil.show("建议打开wifi，不必连接，这样有助于提高网络定位精度！");

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION) {

                //定位权限受限，建议提示用户授予APP定位权限！
                ToastUtil.show("定位权限受限，建议提示用户授予APP定位权限！");

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET) {

                //网络异常造成定位失败，建议用户确认网络状态是否异常！
                ToastUtil.show("网络异常造成定位失败，建议用户确认网络状态是否异常！");

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE) {

                //手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！
                ToastUtil.show("手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！");

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI) {

                //无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！
                ToastUtil.show("无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！");

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH) {

                //无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！
                ToastUtil.show("无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！");

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_SERVER_FAIL) {

                //百度定位服务端定位失败
                //建议反馈location.getLocationID()和大体定位时间到loc-bugs@baidu.com
                ToastUtil.show("百度定位服务端定位失败");

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN) {

                //无法获取有效定位依据，但无法确定具体原因
                //建议检查是否有安全软件屏蔽相关定位权限
                //或调用LocationClient.restart()重新启动后重试！
                ToastUtil.show("无法获取有效定位依据，但无法确定具体原因");

            }
        }

    }

    private void initLocation() {

//        LocationClientOption option = new LocationClientOption();
        BaiduLocationHolder.OPTION.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        BaiduLocationHolder.OPTION.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
        BaiduLocationHolder.OPTION.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        BaiduLocationHolder.OPTION.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        BaiduLocationHolder.OPTION.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        BaiduLocationHolder.OPTION.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        BaiduLocationHolder.OPTION.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        BaiduLocationHolder.OPTION.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        BaiduLocationHolder.OPTION.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        BaiduLocationHolder.OPTION.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        BaiduLocationHolder.OPTION.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

//        BaiduLocationHolder.OPTION.setWifiValidTime(5*60*1000);
        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位

        BaiduLocationHolder.CLIENT.setLocOption(BaiduLocationHolder.OPTION);
    }
}
