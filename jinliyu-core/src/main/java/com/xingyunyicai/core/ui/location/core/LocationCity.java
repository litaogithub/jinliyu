package com.xingyunyicai.core.ui.location.core;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.core.util.log.DoDoLogger;

import java.util.List;
import java.util.Locale;

/**
 * Created by litao on 2017/12/1.
 */

public class LocationCity {

    private ILocationCity LOCATION;
    private Context context;
    private LocationManager myLocationManager = null;
    private DoDoDelegate delegate;

    public LocationCity(ILocationCity LOCATION,Context context,DoDoDelegate delegate) {
        this.LOCATION = LOCATION;
        this.context = context;
        this.delegate = delegate;
    }

    public static LocationCityBuilder builder(DoDoDelegate delegate){
        return new LocationCityBuilder(delegate);
    }

    /**
     * 开启权限检查
     */
    public void startCheckPermission(){
        delegate.startLocationCityWithCheck(this);
    }

    public void start(){
        new MyAsyncTask().execute(getLocation());
    }

    private Location getLocation() {
        //获取位置管理服务
        myLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //查找服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); //定位精度: 最高
        criteria.setAltitudeRequired(false); //海拔信息：不需要
        criteria.setBearingRequired(false); //方位信息: 不需要
        criteria.setCostAllowed(true);  //是否允许付费
        criteria.setPowerRequirement(Criteria.POWER_LOW); //耗电量: 低功耗
        Location gpsLocation = null;
        Location netLocation = null;

        try {
            if (netWorkIsOpen()) {
                //2000代表每2000毫秒更新一次，5代表每5秒更新一次
//                myLocationManager.requestLocationUpdates("network", 2000, 5, locationListener);
                netLocation = myLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (gpsIsOpen()) {
//                myLocationManager.requestLocationUpdates("gps", 2000, 5, locationListener);
                gpsLocation = myLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }catch (SecurityException e){
                if(LOCATION != null)
                    LOCATION.error(e.getMessage());
        }

        if (gpsLocation == null && netLocation == null) {
            return null;
        }
        if (gpsLocation != null && netLocation != null) {
            if (gpsLocation.getTime() < netLocation.getTime()) {
                gpsLocation = null;
                return netLocation;
            } else {
                netLocation = null;
                return gpsLocation;
            }
        }
        if (gpsLocation == null) {
            return netLocation;
        } else {
            return gpsLocation;
        }
    }

    private boolean gpsIsOpen() {
        boolean isOpen = true;
        if (!myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {//没有开启GPS
            isOpen = false;
        }
        return isOpen;
    }

    private boolean netWorkIsOpen() {
        boolean netIsOpen = true;
        if (!myLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {//没有开启网络定位
            netIsOpen = false;
        }
        return netIsOpen;
    }

    /**
     * 根据获得经纬度请求城市信息并返回给主线程
     */
    private class MyAsyncTask extends AsyncTask<Location,Void,List<Address>>{

        @Override
        protected List<Address> doInBackground(Location... params) {
            return getAddress(params[0]);
        }

        @Override
        protected void onPostExecute(List<Address> addressList) {
            super.onPostExecute(addressList);
            if(LOCATION != null) {
                if (addressList != null && addressList.size() > 0) {
                    LOCATION.success(addressList);
                } else {
                    LOCATION.error("返回定位数据为空");
                }
            }
        }
    }
    //官方API根据经纬度得到城市详情
    private List<Address> getAddress(Location location) {
        List<Address> result = null;
        try {
            if (location != null) {
                Geocoder gc = new Geocoder(context, Locale.getDefault());
                result = gc.getFromLocation(location.getLatitude(),//耗时操作
                        location.getLongitude(), 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            new MyAsyncTask().execute(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            DoDoLogger.d("aaa","onStatusChanged:"+provider+"="+status+"=");
        }

        @Override
        public void onProviderEnabled(String provider) {
            DoDoLogger.d("aaa","onStatusChanged:"+provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            DoDoLogger.d("aaa","onStatusChanged:"+provider);
        }
    };
}
