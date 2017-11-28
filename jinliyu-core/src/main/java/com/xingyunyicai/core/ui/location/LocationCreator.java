package com.xingyunyicai.core.ui.location;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.xingyunyicai.core.app.DoDo;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.location
 * 文件名：   LocationCreator
 * 创建者：   DoDo
 * 创建时间： 2017/10/20 4:20
 * 描述：     TODO
 */

public class LocationCreator {

    public static LocationClient getLocationClient() {
        return BaiduLocationHolder.LOCATION_CLIENT;
    }

    private static final class BaiduLocationHolder {
        private static final LocationClient LOCATION_CLIENT = new LocationClient(DoDo.getAppContext());
    }

    public static LocationClientOption getDefaultLocationOption() {
        return BaiduLocationOption.LOCATION_OPTION;
    }

    private static final class BaiduLocationOption {
        private static final LocationClientOption LOCATION_OPTION = initLocationOption();

        private static LocationClientOption initLocationOption() {
            final LocationClientOption option = new LocationClientOption();
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

            option.setCoorType("bd09ll");
            //可选，默认gcj02，设置返回的定位结果坐标系

            int span = 1000;
            option.setScanSpan(span);
            //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

            option.setIsNeedAddress(true);
            //可选，设置是否需要地址信息，默认不需要

            option.setOpenGps(true);
            //可选，默认false,设置是否使用gps

            option.setLocationNotify(true);
            //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

            option.setIsNeedLocationDescribe(true);
            //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

            option.setIsNeedLocationPoiList(true);
            //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

            option.setIgnoreKillProcess(false);
            //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

            option.SetIgnoreCacheException(false);
            //可选，默认false，设置是否收集CRASH信息，默认收集

            option.setEnableSimulateGps(false);
            //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

//        BaiduLocationHolder.OPTION.setWifiValidTime(5*60*1000);
            //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位

            return option;
        }
    }

}
