package com.xingyunyicai.core.ui.recycler;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.recycler
 * 文件名：   DataConverter
 * 创建者：   DoDo
 * 创建时间： 2017/9/9 12:07
 * 描述：     数据转换 约束
 */

public abstract class DataConverter {

    protected final ArrayList<MulEntity> ENTITIES = new ArrayList<>();

    private String mJsonData = null;

    public abstract ArrayList<MulEntity> convert();

    protected String getJsonData() {
        if (TextUtils.isEmpty(mJsonData)) {
            throw new NullPointerException("JSON DATA IS NULL OR EMPTY!");
        }
        return mJsonData;
    }

    public DataConverter setJsonData(String jsonData) {
        this.mJsonData = jsonData;
        return this;
    }

    public void clearData() {
        ENTITIES.clear();
    }
}
