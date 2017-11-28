package com.xingyunyicai.ec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xingyunyicai.core.delegates.DoDoDelegate;

/**
 * Created by YuanJun on 2017/11/27 18:06
 */

public class EcDelegate extends DoDoDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_ec;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        //test 123
    }
}
