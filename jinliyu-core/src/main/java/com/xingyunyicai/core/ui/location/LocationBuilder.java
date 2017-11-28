package com.xingyunyicai.core.ui.location;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.core.ui.location.callback.IError;
import com.xingyunyicai.core.ui.location.callback.IFailure;
import com.xingyunyicai.core.ui.location.callback.IStart;
import com.xingyunyicai.core.ui.location.callback.IStop;
import com.xingyunyicai.core.ui.location.callback.ISuccess;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.location
 * 文件名：   LocationBuilder
 * 创建者：   DoDo
 * 创建时间： 2017/10/20 4:20
 * 描述：     TODO
 */

public class LocationBuilder {

    private LocationClient mLocationClient = null;
    private LocationClientOption mLocationOption = null;
    private ISuccess mSuccess = null;
    private IFailure mFailure = null;
    private IError mError = null;
    private IStart mStart = null;
    private IStop mStop = null;
    private final DoDoDelegate DELEGATE;

    LocationBuilder(DoDoDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public final LocationBuilder client(LocationClient client) {
        this.mLocationClient = client;
        return this;
    }

    public final LocationBuilder option(LocationClientOption option) {
        this.mLocationOption = option;
        return this;
    }

    public final LocationBuilder success(ISuccess success) {
        this.mSuccess = success;
        return this;
    }

    public final LocationBuilder failure(IFailure failure) {
        this.mFailure = failure;
        return this;
    }

    public final LocationBuilder error(IError error) {
        this.mError = error;
        return this;
    }

    public final LocationBuilder start(IStart start) {
        this.mStart = start;
        return this;
    }

    public final LocationBuilder stop(IStop stop) {
        this.mStop = stop;
        return this;
    }

    public final DoDoLocation build() {
        if (mLocationClient == null) {
            mLocationClient = LocationCreator.getLocationClient();
        }
        if (mLocationOption == null) {
            mLocationOption = LocationCreator.getDefaultLocationOption();
        }
        return new DoDoLocation(
                mLocationClient,
                mLocationOption,
                mSuccess,
                mFailure,
                mError,
                mStart,
                mStop,
                DELEGATE);
    }

}
