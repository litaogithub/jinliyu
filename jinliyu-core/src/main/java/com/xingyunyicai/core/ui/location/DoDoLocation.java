package com.xingyunyicai.core.ui.location;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.core.ui.location.callback.IError;
import com.xingyunyicai.core.ui.location.callback.IFailure;
import com.xingyunyicai.core.ui.location.callback.IStart;
import com.xingyunyicai.core.ui.location.callback.IStop;
import com.xingyunyicai.core.ui.location.callback.ISuccess;
import com.xingyunyicai.core.ui.location.callback.LocationCallback;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.location
 * 文件名：   DoDoLocation
 * 创建者：   DoDo
 * 创建时间： 2017/10/19 9:36
 * 描述：     TODO
 */

public class DoDoLocation {

    private final LocationClient LOCATION_CLIENT;
    private final LocationClientOption LOCATION_OPTION;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IStart START;
    private final IStop STOP;
    private final DoDoDelegate DELEGATE;

    public DoDoLocation(LocationClient locationClient,
                        LocationClientOption locationOption,
                        ISuccess success,
                        IFailure failure,
                        IError error,
                        IStart start,
                        IStop stop,
                        DoDoDelegate delegate) {
        this.LOCATION_CLIENT = locationClient;
        this.LOCATION_OPTION = locationOption;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.START = start;
        this.STOP = stop;
        this.DELEGATE = delegate;
    }

    public static LocationBuilder builder(DoDoDelegate delegate) {
        return new LocationBuilder(delegate);
    }

    /**
     * 开始定位
     */
    public final void start() {

        DELEGATE.startLocationWithCheck(this);

    }

    public void onStart() {

//        //360手机永远显示已授权，小米正常
//        int result = ContextCompat.checkSelfPermission(DELEGATE.getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
//        if (result == 0) {
//            Toast.makeText(DELEGATE.getContext(), "已授权", Toast.LENGTH_LONG).show();
//        } else if (result == -1) {
//            Toast.makeText(DELEGATE.getContext(), "未授权", Toast.LENGTH_LONG).show();
//        }
//
//        return;

        if (START != null) {
            START.onStart();
        }

        LOCATION_CLIENT.setLocOption(LOCATION_OPTION);

        final BDAbstractLocationListener locationListener = getLocationCallback();
        LOCATION_CLIENT.registerLocationListener(locationListener);

        //开始定位
        if (LOCATION_CLIENT.isStarted()) {
            LOCATION_CLIENT.restart();//停止，延时1s开始定位
        } else {
            LOCATION_CLIENT.start();//直接开始定位
        }
    }

    private LocationCallback getLocationCallback() {
        return new LocationCallback(
                LOCATION_CLIENT,
                SUCCESS,
                FAILURE,
                ERROR,
                STOP);
    }

}
