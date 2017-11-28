package com.xingyunyicai.qihoo.video.main.tuijian.loadmore;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.xingyunyicai.qihoo.video.R;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian.loadmore
 * 文件名：   DoDoLoadMoreView
 * 创建者：   DoDo
 * 创建时间： 2017/10/1 19:57
 * 描述：     上拉加载view只需要一个布局，所有状态子布局都放在里面
 */

public class DoDoLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.view_load_more;
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
