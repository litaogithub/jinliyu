package com.xingyunyicai.qihoo.video.main.baodian;

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
import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.MulAdapter;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.core.util.toast.ToastUtil;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.R2;
import com.xingyunyicai.qihoo.video.main.VideoBottomDelegate;
import com.xingyunyicai.qihoo.video.main.baodian.adapter.BaoDianAdapter;
import com.xingyunyicai.qihoo.video.main.baodian.data.BaoDianDataConverter;
import com.xingyunyicai.qihoo.video.main.baodian.listener.BaoDianItemChildClickListener;
import com.xingyunyicai.qihoo.video.main.baodian.listener.BaoDianItemClickListener;
import com.xingyunyicai.qihoo.video.main.tuijian.loadmore.DoDoLoadMoreView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.baodian
 * 文件名：   BaoDianContentDelegate
 * 创建者：   DoDo
 * 创建时间： 2017/10/14 23:54
 * 描述：     TODO
 */

public class BaoDianContentDelegate extends DoDoDelegate implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private static final String ARGS_URL = "pager_url";
    private String mUrl = null;

    @BindView(R2.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    private MulAdapter mAdapter = null;
    private DataConverter mDataConverter = null;
    private GridLayoutManager mLayoutManager = null;

    public static BaoDianContentDelegate create(String url) {
        final Bundle args = new Bundle();
        args.putString(ARGS_URL, url);
        final BaoDianContentDelegate delegate = new BaoDianContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_bao_dian_content;
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

            initRefreshLayout();
            initRecyclerView();

            mRefreshLayout.setRefreshing(true);

            DoDo.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    requestData();
//                    ToastUtil.show(mUrl);
                }
            }, 800);

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
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //优化
        mRecyclerView.setHasFixedSize(true);
        //取消adapter.notifyItemChanged()方法自带的默认动画效果
        ((DefaultItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
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
        mDataConverter = new BaoDianDataConverter();
        final VideoBottomDelegate videoBottomDelegate = getParentDelegate().getParentDelegate();
        mAdapter = BaoDianAdapter.create(mDataConverter.setJsonData(json), videoBottomDelegate);
        //下拉加载
        mAdapter.setOnLoadMoreListener(BaoDianContentDelegate.this, mRecyclerView);
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
        mAdapter.setOnItemClickListener(BaoDianItemClickListener.create());
        /**
         * item子控件点击
         * 需要在adapter中设置需要响应事件的子控件
         * 子控件如果响应了ChildClickListener，则该子控件就不会再响应item的点击事件
         */
        mAdapter.setOnItemChildClickListener(BaoDianItemChildClickListener.create());

    }

    private boolean mNoNet = true;//无网络
    private boolean mNoData = true;//无数据

    /**
     * 下拉刷新
     */
    private void refreshData() {
        mAdapter.setEmptyView(R.layout.view_empty_loading, (ViewGroup) mRecyclerView.getParent());//这里不写父布局也可以。默认使用rv作为父布局
        mAdapter.setNewData(null);
        DoDo.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                //清空adapter之前的数据
                mDataConverter.clearData();
                mAdapter.setNewData(mDataConverter.convert());
                //重新开启下拉刷新监听
                mAdapter.setEnableLoadMore(true);
                loadCount = -1;
                ToastUtil.show("刷新完成");
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
                if (loadCount < 0) {
                    loadCount++;
//                    final MulEntity entity = MulEntity.builder()
//                            .setItemType(ItemType.AD_1)
//                            .build();
                    mDataConverter.clearData();
                    final ArrayList<MulEntity> entities = mDataConverter.convert();
                    mAdapter.addData(entities);
                    //上拉加载完成
                    mAdapter.loadMoreComplete();
                    ToastUtil.show("10条新数据");
                } else {
                    loadCount = -1;
                    //上拉加载结束
                    ToastUtil.show("没有更多内容了");
                    mAdapter.loadMoreEnd(true);//false=显示"我是有底线的"，true则隐藏
                }
            }
        }, 1000);
    }

    @Override
    public void onLoadMoreRequested() {
        loadMoreData();
    }
}
