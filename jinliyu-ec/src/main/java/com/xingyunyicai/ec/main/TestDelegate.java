package com.xingyunyicai.ec.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.ec.R;
import com.xingyunyicai.ec.R2;

import butterknife.BindView;

/**
 * Created by YuanJun on 2017/11/29 13:12
 */

public class TestDelegate extends BaseBottomItemDelegate {

    private static final String ARGS_TITLE = "args_title";
    private String mTitle = null;

    @BindView(R2.id.title)
    TextView mTv = null;

    public static TestDelegate create(String title) {
        final Bundle args = new Bundle();
        args.putString(ARGS_TITLE, title);
        final TestDelegate testDelegate = new TestDelegate();
        testDelegate.setArguments(args);
        return testDelegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mTitle = args.getString(ARGS_TITLE);

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_test;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mTv.setText(mTitle);
    }
}
