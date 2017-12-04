package com.xingyunyicai.core.ui.location.core;

import android.content.Context;

import com.xingyunyicai.core.delegates.DoDoDelegate;

/**
 * Created by litao on 2017/12/1.
 */

public class LocationCityBuilder {

    private ILocationCity LOCATION;
    private Context context;
    private DoDoDelegate delegate;

    LocationCityBuilder(DoDoDelegate delegate) {
        this.delegate = delegate;
    }

    public final LocationCityBuilder call(Context context,ILocationCity iLocationCity){
        this.context = context;
        this.LOCATION = iLocationCity;
        return this;
    }

    public final LocationCity build(){
        return new LocationCity(LOCATION,context,delegate);
    }

}
