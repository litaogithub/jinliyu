package com.xingyunyicai.qihoo.video.main.wode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.xingyunyicai.core.delegates.bottom.BaseBottomItemDelegate;
import com.xingyunyicai.core.util.toast.ToastUtil;
import com.xingyunyicai.qihoo.video.R;
import com.xingyunyicai.qihoo.video.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main
 * 文件名：   PinDaoDelegate
 * 创建者：   DoDo
 * 创建时间： 2017/9/18 9:13
 * 描述：     TODO
 */

public class WoDeDelegate extends BaseBottomItemDelegate {

    @BindView(R2.id.login)
    LinearLayout login;
    @BindView(R2.id.li_xian)
    LinearLayout liXian;
    @BindView(R2.id.bo_fang)
    LinearLayout boFang;
    @BindView(R2.id.ben_di)
    LinearLayout benDi;
    @BindView(R2.id.shou_cang)
    LinearLayout shouCang;
    @BindView(R2.id.mo_fa)
    LinearLayout moFa;
    @BindView(R2.id.dai_kuan)
    LinearLayout daiKuan;
    @BindView(R2.id.you_xi)
    LinearLayout youXi;
    @BindView(R2.id.wifi)
    LinearLayout wifi;
    @BindView(R2.id.she_zhi)
    LinearLayout sheZhi;

    @Override
    public Object setLayout() {
        return R.layout.delegate_wo_de;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @OnClick(R2.id.login)
    public void onLoginClicked() {
        ToastUtil.show("登陆");
    }

    @OnClick(R2.id.li_xian)
    public void onLiXianClicked() {
        ToastUtil.show("离线缓存");
    }

    @OnClick(R2.id.bo_fang)
    public void onBoFangClicked() {
        ToastUtil.show("播放记录");
    }

    @OnClick(R2.id.ben_di)
    public void onBenDiClicked() {
        ToastUtil.show("本地视频");
    }

    @OnClick(R2.id.shou_cang)
    public void onShouCangClicked() {
        ToastUtil.show("我的收藏");
    }

    @OnClick(R2.id.mo_fa)
    public void onMoFaClicked() {
        ToastUtil.show("魔法口袋");
    }

    @OnClick(R2.id.dai_kuan)
    public void onDaiKuanClicked() {
        ToastUtil.show("贷款");
    }

    @OnClick(R2.id.you_xi)
    public void onYouXiClicked() {
        ToastUtil.show("游戏中心");
    }

    @OnClick(R2.id.wifi)
    public void onWifiClicked() {
        ToastUtil.show("免费WIFI");
    }

    @OnClick(R2.id.she_zhi)
    public void onSheZhiClicked() {
        ToastUtil.show("设置");
    }
}
