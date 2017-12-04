package com.xingyunyicai.ec.main.index_2.listener;

import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.core.ui.recycler.MulFields;
import com.xingyunyicai.ec.main.index_2.bean.TePinBean;
import com.xingyunyicai.ec.main.index_2.data.TePinItemType;

/**
 * Created by YuanJun on 2017/12/4 11:13
 */

public class TePinItemClickListener implements BaseQuickAdapter.OnItemClickListener {

    public static TePinItemClickListener create() {
        return new TePinItemClickListener();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (adapter.getItemViewType(position)) {
            case TePinItemType.GOODS:
                final MulEntity entity = (MulEntity) adapter.getData().get(position);
                final TePinBean tePinBean = entity.getField(MulFields.BEAN);
                ToastUtils.showShort(tePinBean.getTitle());
                break;
            default:
                break;
        }
    }
}
