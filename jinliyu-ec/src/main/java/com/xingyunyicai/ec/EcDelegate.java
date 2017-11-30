package com.xingyunyicai.ec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.ec.location.LocationDeledate;

import butterknife.BindView;

/**
 * Created by YuanJun on 2017/11/27 18:06
 */

public class EcDelegate extends DoDoDelegate {

    @BindView(R2.id.enter_tv)
    TextView mTextView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_ec;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(new LocationDeledate());
            }
        });

    }
}
