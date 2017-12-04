package com.xingyunyicai.ec.main.index_2.loadmore;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.xingyunyicai.ec.R;

/**
 * Created by YuanJun on 2017/12/4 11:03
 */

public class TePinLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.layout_load_more_view;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.loading;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_fail;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_end;
    }
}
