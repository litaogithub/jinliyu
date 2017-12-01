package com.xingyunyicai.ec.main.index_2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.ec.R;
import com.xingyunyicai.ec.R2;

import butterknife.BindView;

/**
 * Created by YuanJun on 2017/12/1 17:55
 */

public class TePinContentDelegate extends DoDoDelegate {

    private static final String ARGS_URL = "pager_url";
    private String mUrl = null;

    @BindView(R2.id.title)
    TextView mTestTv = null;

    public static TePinContentDelegate create(String url) {
        final Bundle args = new Bundle();
        args.putString(ARGS_URL, url);
        final TePinContentDelegate delegate = new TePinContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_test;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final String url = getArguments().getString(ARGS_URL);
        if (!TextUtils.isEmpty(url)) {
            mUrl = url;
        }
        mTestTv.setText(url);
    }
}
