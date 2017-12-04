package com.xingyunyicai.core.ui.location.core;

import android.location.Address;

import java.util.List;

/**
 * Created by litao on 2017/12/1.
 */

public interface ILocationCity {

    void success(List<Address> addresses);
    void error(String msg);
}
