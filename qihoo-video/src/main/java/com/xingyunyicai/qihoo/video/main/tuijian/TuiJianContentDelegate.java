package com.xingyunyicai.qihoo.video.main.tuijian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xingyunyicai.core.app.DoDo;
import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.core.net.RestClient;
import com.xingyunyicai.core.net.callback.ISuccess;
import com.xingyunyicai.core.ui.image.GlideApp;
import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.ItemType;
import com.xingyunyicai.core.ui.recycler.MulAdapter;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.core.util.toast.ToastUtil;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.R2;
import com.xingyunyicai.qihoo.video.main.VideoBottomDelegate;
import com.xingyunyicai.qihoo.video.main.tuijian.adapter.TuiJianAdapter;
import com.xingyunyicai.qihoo.video.main.tuijian.data.TuiJianDataConverter;
import com.xingyunyicai.qihoo.video.main.tuijian.listener.TuiJianItemChildClickListener;
import com.xingyunyicai.qihoo.video.main.tuijian.listener.TuiJianItemClickListener;
import com.xingyunyicai.qihoo.video.main.tuijian.loadmore.DoDoLoadMoreView;
import com.youth.banner.Banner;

import butterknife.BindView;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian
 * 文件名：   TuiJianContentDelegate
 * 创建者：   DoDo
 * 创建时间： 2017/10/11 23:12
 * 描述：     TODO
 */

public class TuiJianContentDelegate extends DoDoDelegate implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private static final String ARGS_URL = "pager_url";
    private String mUrl = null;

    @BindView(R2.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    private MulAdapter mAdapter = null;
    private DataConverter mDataConverter = null;
    private GridLayoutManager mLayoutManager = null;

    public static TuiJianContentDelegate create(String url) {
        final Bundle args = new Bundle();
        args.putString(ARGS_URL, url);
        final TuiJianContentDelegate delegate = new TuiJianContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_tui_jian_content;
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
     *
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //防止viewpager滚动多次导致重复刷新
        if (mAdapter == null) {

            initRefreshLayout();
            initRecyclerView();

            mRefreshLayout.setRefreshing(true);

            DoDo.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    requestData();
                }
            },800);

        }

    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                R.color.qihoo_color
        );
        mRefreshLayout.setOnRefreshListener(this);
        //设置灵敏度
        mRefreshLayout.setDistanceToTriggerSync(400);
    }

    private void initRecyclerView() {
        mLayoutManager = new GridLayoutManager(getContext(), 12);
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

            private boolean isBannerShow = false;//banner是否正在显示
            private Banner banner = null;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //控制banner的轮播状态，不可见时停止，可见时开启
                final int firstItemPosition = mLayoutManager.findFirstVisibleItemPosition();
                final int itemType = mAdapter.getItemViewType(firstItemPosition);
                switch (itemType) {
                    case ItemType.BANNER:
                        if (!isBannerShow) {
                            isBannerShow = true;
                            if (banner == null) {
                                banner = (Banner) mAdapter.getViewByPosition(firstItemPosition, R.id.banner);
                            }
                            if (banner != null) {
//                                Log.d("HAHAHA", "banner显示");
                                banner.startAutoPlay();
                            }
                        }
                        break;
                    default:
                        if (isBannerShow) {
                            isBannerShow = false;
                            if (banner != null) {
//                                Log.d("HAHAHA", "banner隐藏");
                                banner.stopAutoPlay();
                            }
                        }
                        break;
                }

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

        RestClient.builder()
                .url(mUrl)
//                .url("intercept")
//                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        handleData(response);
                        mRefreshLayout.setRefreshing(false);
                    }
                })
                .build()
                .get();
    }

    private View noNetView = null;
    private View noDataView = null;

    /**
     * 处理数据
     *
     * @param json
     */
    private void handleData(String json) {
        mDataConverter = new TuiJianDataConverter();
        final VideoBottomDelegate videoBottomDelegate = getParentDelegate().getParentDelegate();
        mAdapter = TuiJianAdapter.create(mDataConverter.setJsonData(json), videoBottomDelegate);
        //下拉加载
        mAdapter.setOnLoadMoreListener(TuiJianContentDelegate.this, mRecyclerView);
        mAdapter.setLoadMoreView(new DoDoLoadMoreView());
        mAdapter.disableLoadMoreIfNotFullPage(mRecyclerView);
        //空布局
        //如果加载失败，需要点击空布局进行刷新，所以设置空布局方法就放在刷新操作里
        //这里只做初始化空布局view的工作
        noNetView = LayoutInflater.from(getProxyActivity()).inflate(R.layout.view_empty_no_net, (ViewGroup) mRecyclerView.getParent(), false);//这里不写父布局也可以
        noDataView = LayoutInflater.from(getProxyActivity()).inflate(R.layout.view_empty_no_data, (ViewGroup) mRecyclerView.getParent(), false);//这里不写父布局也可以
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
        mAdapter.setOnItemClickListener(TuiJianItemClickListener.create());
        /**
         * item子控件点击
         * 需要在adapter中设置需要响应事件的子控件
         * 子控件如果响应了ChildClickListener，则该子控件就不会再响应item的点击事件
         */
        mAdapter.setOnItemChildClickListener(TuiJianItemChildClickListener.create());

    }

    private boolean mNoNet = true;//无网络
    private boolean mNoData = true;//无数据

    /**
     * 下拉刷新
     */
    private void refreshData() {
        mAdapter.setEmptyView(R.layout.view_empty_loading, (ViewGroup) mRecyclerView.getParent());//这里不写父布局也可以。默认使用rv作为父布局
        DoDo.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mNoNet) {
                    mNoNet = false;
                    mAdapter.setEmptyView(noNetView);
                    mAdapter.setNewData(null);
                    ToastUtil.show("刷新失败，无网络");
                } else if (mNoData) {
                    mNoData = false;
                    mAdapter.setEmptyView(noDataView);
                    mAdapter.setNewData(null);
                    ToastUtil.show("刷新失败，无数据");
                } else {
                    mNoNet = true;
                    mNoData = true;
                    //清空adapter之前的数据
                    mDataConverter.clearData();
                    mAdapter.reLoadBanner();//重新加载banner
                    mAdapter.setNewData(mDataConverter.convert());
                    //重新开启下拉刷新监听
                    mAdapter.setEnableLoadMore(true);
                    ToastUtil.show("刷新完成");
                }
                if (mRefreshLayout.isRefreshing()) {
                    mRefreshLayout.setRefreshing(false);
                }
            }
        }, 800);
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.setRefreshing(true);
        refreshData();
    }

    private int loadCount = -1;

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

    @Override
    public void onLoadMoreRequested() {
        loadMoreData();
    }
}
