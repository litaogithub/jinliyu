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

    private static final String TAG = "Location";
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
//                LocationCity.builder(EcDelegate.this)
//                        .call(getProxyActivity(),new ILocationCity() {
//                            @Override
//                            public void success(List<Address> addresses) {
//                                Address address = addresses.get(0);
//                                mTextView.setText("城市:"+address.getAdminArea()+
//                                        "\n经度："+address.getLatitude()+
//                                        "\n纬度："+address.getLongitude()+"\n"+address.getAddressLine(0));
//                            }
//
//                            @Override
//                            public void error(String msg) {
//                                ToastUtils.showShort(msg);
//                            }
//                        })
//                        .build().startCheckPermission();
            }
        });
    }
}
