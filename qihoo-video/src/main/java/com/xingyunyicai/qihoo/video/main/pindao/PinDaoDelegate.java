package com.xingyunyicai.qihoo.video.main.pindao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.core.net.RestClient;
import com.xingyunyicai.core.net.callback.ISuccess;
import com.xingyunyicai.core.util.toast.ToastUtil;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.R2;
import com.xingyunyicai.qihoo.video.constant.ApiConstants;
import com.xingyunyicai.qihoo.video.main.VideoBottomDelegate;
import com.xingyunyicai.qihoo.video.main.pindao.adapter.PinDaoAdapter;
import com.xingyunyicai.qihoo.video.main.pindao.data.PinDaoDataConverter;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main
 * 文件名：   PinDaoDelegate
 * 创建者：   DoDo
 * 创建时间： 2017/9/18 9:13
 * 描述：     TODO
 */

public class PinDaoDelegate extends BaseBottomItemDelegate {

    @BindView(R2.id.rl_search)
    RelativeLayout mSearch;
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_pin_dao;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        initRecyclerView();

        requestData();
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getProxyActivity(), 4);
        mRecyclerView.setLayoutManager(manager);
        //优化
        mRecyclerView.setHasFixedSize(true);
        //取消adapter.notifyItemChanged()方法自带的默认动画效果
        ((DefaultItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    private void requestData() {

        RestClient.builder()
                .url(ApiConstants.PIN_DAO_URL + "channel_labels.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        handleData(response);
                    }
                })
                .build()
                .get();
    }

    private void handleData(String json) {
        final PinDaoDataConverter dataConverter = new PinDaoDataConverter();
        final VideoBottomDelegate videoBottomDelegate = getParentDelegate();
        final PinDaoAdapter adapter = PinDaoAdapter.create(dataConverter.setJsonData(json), videoBottomDelegate);
        adapter.setEmptyView(R.layout.view_empty_loading, (ViewGroup) mRecyclerView.getParent());
        adapter.bindToRecyclerView(mRecyclerView);
    }

    @OnClick(R2.id.rl_search)
    public void onViewClicked() {
        ToastUtil.show("搜索");
    }

    @OnLongClick(R2.id.rl_search)
    public boolean onViewLongClicked() {
        ToastUtil.show("长按搜索");
        return true;
    }

}
