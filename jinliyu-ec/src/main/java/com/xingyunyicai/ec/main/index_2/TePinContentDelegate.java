package com.xingyunyicai.ec.main.index_2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.core.ui.image.GlideApp;
import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.ItemType;
import com.xingyunyicai.core.ui.recycler.MulAdapter;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.ec.R;
import com.xingyunyicai.ec.R2;
import com.xingyunyicai.ec.main.ECBottomDelegate;
import com.xingyunyicai.ec.main.index_2.adapter.TePinAdapter;
import com.xingyunyicai.ec.main.index_2.data.TePinDataConverter;
import com.xingyunyicai.ec.main.index_2.listener.TePinItemClickListener;
import com.xingyunyicai.ec.main.index_2.loadmore.TePinLoadMoreView;

import butterknife.BindView;

/**
 * Created by YuanJun on 2017/12/1 17:55
 */

public class TePinContentDelegate extends DoDoDelegate implements BaseQuickAdapter.RequestLoadMoreListener {

    private static final String ARGS_URL = "pager_url";
    private String mUrl = null;
    private MulAdapter mAdapter = null;
    private DataConverter mDataConverter = null;
    private GridLayoutManager mLayoutManager = null;

    @BindView(R2.id.recyclerview)
    RecyclerView mRecyclerView = null;

    public static TePinContentDelegate create(String url) {
        final Bundle args = new Bundle();
        args.putString(ARGS_URL, url);
        final TePinContentDelegate delegate = new TePinContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_te_pin_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final String url = getArguments().getString(ARGS_URL);
        if (!TextUtils.isEmpty(url)) {
            mUrl = url;
        }
    }

    /**
     * 懒加载在当前fragment的整个生命周期中只会执行一次(实例化且第一次可见时)
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //防止viewpager滚动多次导致重复刷新
        if (mAdapter == null) {

            initRecyclerView();

//            DoDo.getHandler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    requestData();
//                }
//            }, 800);

            requestData();

        }

    }

    private void initRecyclerView() {
        mLayoutManager = new GridLayoutManager(getContext(), 6);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //优化
        mRecyclerView.setHasFixedSize(true);
        //取消adapter.notifyItemChanged()方法自带的默认动画效果
        ((DefaultItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        //优化快速滑动时的图片加载
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private final int defaultY = 100;//快速滑动的默认临界值，超过改值则暂停图片加载，当小于改值时恢复图片加载
            private boolean toggle = false;//进入快速滑动标识1
            private boolean toggle2 = false;//暂停加载标识，是需要暂停一次即可
            private int tempY = -1;//就是dy的绝对值，定义这个主要是为了修正惯性滑动刚开始时的dy值异常（刚滑动可能就会突然大于100）

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                switch (recyclerView.getScrollState()) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        //按住并滑动
                        //这里会多次响应该状态，因为只需要在这里初始化一次标识符，所以移到下面onScrollStateChanged()里进行初始化
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        //松手后惯性滑动
                        if (tempY == -1) {
                            tempY = 0;//修正惯性滑动刚开始时的dy值异常
                        } else if (tempY >= 0) {
                            tempY = Math.abs(dy);
                        }

                        if (tempY > defaultY && !toggle) {
                            //进入快速滑动
                            toggle = true;
                        }
                        if (toggle) {
                            if (tempY > defaultY && !toggle2) {
                                toggle2 = true;
                                GlideApp.with(getProxyActivity()).pauseRequestsRecursive();
//                                Log.d("HAHAHA", "暂停加载" + tempY);
                            } else if (tempY < defaultY) {
                                if (GlideApp.with(getProxyActivity()).isPaused()) {
                                    GlideApp.with(getProxyActivity()).resumeRequestsRecursive();
//                                    Log.d("HAHAHA", "开启加载" + tempY);
                                }
                            }

                        }
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        //完全停止滑动
                        //这里是滑动监听，所以停止滑动的时候该状态不会在此响应
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        //按住并滑动
//                        Log.d("HAHAHA", "SCROLL_STATE_DRAGGING");
                        toggle = false;
                        toggle2 = false;
                        tempY = -1;
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        //松手后惯性滑动
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        //完全停止滑动
//                        Log.d("HAHAHA", "SCROLL_STATE_IDLE");
                        if (GlideApp.with(getProxyActivity()).isPaused()) {
                            GlideApp.with(getProxyActivity()).resumeRequestsRecursive();
//                            Log.d("HAHAHA", "停止滑动，并处于暂停状态，重新开启加载");
                        }
                        toggle = false;
                        toggle2 = false;
                        tempY = -1;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 请求数据
     */
    private void requestData() {

        //请求数据
//        RestClient.builder()
//                .url(mUrl)
////                .url("intercept")
////                .loader(getContext())
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        handleData(response);
//                    }
//                })
//                .build()
//                .get();

        handleData(null);

    }

    private View noNetView = null;
    private View noDataView = null;

    /**
     * 处理数据
     *
     * @param json
     */
    private void handleData(String json) {
        mDataConverter = new TePinDataConverter();
        final ECBottomDelegate ecBottomDelegate = getParentDelegate().getParentDelegate();
        mAdapter = TePinAdapter.create(mDataConverter.setJsonData(json), ecBottomDelegate);
        //下拉加载
        mAdapter.setOnLoadMoreListener(TePinContentDelegate.this, mRecyclerView);
        mAdapter.setLoadMoreView(new TePinLoadMoreView());
        mAdapter.disableLoadMoreIfNotFullPage(mRecyclerView);
        //空布局
        //如果加载失败，需要点击空布局进行刷新，所以设置空布局方法就放在刷新操作里
        //这里只做初始化空布局view的工作
        noNetView = LayoutInflater.from(getProxyActivity()).inflate(R.layout.layout_empty_view_no_net, (ViewGroup) mRecyclerView.getParent(), false);//这里不写父布局也可以
        noDataView = LayoutInflater.from(getProxyActivity()).inflate(R.layout.layout_empty_view_no_data, (ViewGroup) mRecyclerView.getParent(), false);//这里不写父布局也可以
        noDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });
        noNetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });

//        //内部会调用rv.setAdapter()并保存rv的实例，方便获取到item内部的子控件 getViewByPosition()
//        mAdapter.bindToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);//设置上拉加载会自动保存rv实例，所以不能用bindToRecyclerView()

        /**
         * item点击
         */
        mAdapter.setOnItemClickListener(TePinItemClickListener.create());
        /**
         * item子控件点击
         * 需要在adapter中设置需要响应事件的子控件
         * 子控件如果响应了ChildClickListener，则该子控件就不会再响应item的点击事件
         */
//        mAdapter.setOnItemChildClickListener(TuiJianItemChildClickListener.create());

    }

    private boolean mNoNet = true;//无网络
    private boolean mNoData = true;//无数据

    /**
     * 下拉刷新
     */
    private void refreshData() {
        mAdapter.setEmptyView(R.layout.layout_loading_view, (ViewGroup) mRecyclerView.getParent());//这里不写父布局也可以。默认使用rv作为父布局
        DoDo.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mNoNet) {
                    mNoNet = false;
                    mAdapter.setEmptyView(noNetView);
                    mAdapter.setNewData(null);
                    ToastUtils.showShort("刷新失败，无网络");
                } else if (mNoData) {
                    mNoData = false;
                    mAdapter.setEmptyView(noDataView);
                    mAdapter.setNewData(null);
                    ToastUtils.showShort("刷新失败，无数据");
                } else {
                    mNoNet = true;
                    mNoData = true;
                    //清空adapter之前的数据
                    mDataConverter.clearData();
                    mAdapter.reLoadBanner();//重新加载banner
                    mAdapter.setNewData(mDataConverter.convert());
                    //重新开启下拉刷新监听
                    mAdapter.setEnableLoadMore(true);
                    ToastUtils.showShort("刷新完成");
                }
            }
        }, 800);
    }

    @Override
    public void onLoadMoreRequested() {
        loadMoreData();
    }

    private int loadCount = 1;

    /**
     * 上拉加载
     */
    private void loadMoreData() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadCount == -1) {
                    loadCount = 0;
                    //上拉加载失败
                    mAdapter.loadMoreFail();
                } else if (loadCount < 1) {
                    loadCount++;
                    final MulEntity entity = MulEntity.builder()
                            .setItemType(ItemType.AD_1)
                            .build();
                    mAdapter.addData(entity);
                    //上拉加载完成
                    mAdapter.loadMoreComplete();
                } else {
                    loadCount = -1;
                    //上拉加载结束
                    mAdapter.loadMoreEnd(false);//false=显示"我是有底线的"，true则隐藏
                }
            }
        }, 1000);
    }

}
