package com.xingyunyicai.ec.location;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xingyunyicai.core.delegates.DoDoDelegate;
import com.xingyunyicai.core.ui.recycler.MulFields;
import com.xingyunyicai.core.util.storage.DoDoPreference;
import com.xingyunyicai.core.util.toast.ToastUtil;
import com.xingyunyicai.ec.ApiConstants;
import com.xingyunyicai.ec.R;
import com.xingyunyicai.ec.R2;
import com.xingyunyicai.ec.location.bean.PinyinComparator;
import com.xingyunyicai.ec.location.bean.SortModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

import static com.xingyunyicai.core.util.storage.DoDoPreference.getCustomAppProfile;

/**
 * Created by litao on 2017/11/29.
 * 城市定位主页面
 */

public class LocationDeledate extends DoDoDelegate {

    @BindView(R2.id.rv)
    RecyclerView sortListView;
    @BindView(R2.id.sidrbar)
    com.xingyunyicai.ec.location.SideBar sideBar;
    @BindView(R2.id.dialog)
    TextView dialog;
    @BindView(R2.id.filter_edit)
    com.xingyunyicai.ec.location.ClearEditText mClearEditText;
    @BindView(R2.id.location_back)
    ImageView mBack;
    private LocationAdapter mAdapter;
    private List<SortModel> sourceDateList;
    private LocationDataConvert locationDataConvert;
    private GridLayoutManager linearLayoutManager;

    @Override
    public Object setLayout() {
        return R.layout.location_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort("dianji");
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initViews();
        initAdapter();
    }


    private void initViews() {
        sideBar.setTextView(dialog);
        sideBar.setOnTouchingLetterChangedListener(new com.xingyunyicai.ec.location.SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int position = mAdapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    linearLayoutManager.scrollToPositionWithOffset(position,0);
                }
            }
        });

        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    //初始化adapter填充数据
    private void initAdapter(){
        sourceDateList = filledData(getResources().getStringArray(R.array.date));
        Collections.sort(sourceDateList, new PinyinComparator());
        locationDataConvert = new LocationDataConvert(sourceDateList,
                Arrays.asList(getResources().getStringArray(R.array.hotCityData)),showHistoryData());
        mAdapter = new LocationAdapter(locationDataConvert.convert());
        linearLayoutManager = new GridLayoutManager(getProxyActivity(),3);
        sortListView.setLayoutManager(linearLayoutManager);
        sortListView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int itemType = mAdapter.getData().get(position).getItemType();
                OnItemClickListener(itemType,position);
            }
        });
    }

    /**
     * 从sp里取出搜索城市缓存，默认北京
     * @return
     */
    private List<String> showHistoryData(){
        List<String> cityList = new ArrayList<>();
        cityList.add(getString(R.string.default_location_city));
        String dataStr = DoDoPreference.getCustomAppProfile(DoDoPreference.HISTORY_CITY);
        if(TextUtils.isEmpty(dataStr))
            return cityList;
        String[] strArr = dataStr.split(",");
        //默认保存3个历史搜索城市
        int count = ApiConstants.HISTORY_CITY_COUNT;
        if(strArr.length > count){
            //如果sp里存的数量大于3个就清空缓存
            DoDoPreference.removeCustomAppProfile(DoDoPreference.HISTORY_CITY);
        }
        for(int i = 0; i < strArr.length; i++){
            cityList.add(strArr[i]);
        }
        return cityList;
    }

    /**
     * 根据type判断点击item事件
     * @param type
     * @param position
     */
    private void OnItemClickListener(int type,int position){
        switch (type){
            case LocationItemType.ITEM_SORT:
                String sortName = ((SortModel)mAdapter.getData().get(position).getField(MulFields.BEAN)).getName();
                ToastUtils.showShort(sortName);
                savaHistorySearch(sortName);
                break;
            case LocationItemType.HOT_CITY:
                String hotCityName = mAdapter.getData().get(position).getField(MulFields.BEAN);
                ToastUtils.showShort(hotCityName);
                savaHistorySearch(hotCityName);
                break;
            case LocationItemType.LOCATION_RECENT:
                String locationName = mAdapter.getData().get(position).getField(MulFields.BEAN);
                ToastUtils.showShort(locationName);
                savaHistorySearch(locationName);
                break;
            default:
                break;
        }
    }

    //保存历史搜索城市名存起来
    private void savaHistorySearch(String cityName){
        String searchCity = getCustomAppProfile(DoDoPreference.HISTORY_CITY);
        StringBuilder stringBuilder = new StringBuilder();
        if(TextUtils.isEmpty(searchCity)){
            stringBuilder.append(cityName+",");
            DoDoPreference.addCustomAppProfile(DoDoPreference.HISTORY_CITY,stringBuilder.toString());
        }else{
            String saveCityName = DoDoPreference.getCustomAppProfile(DoDoPreference.HISTORY_CITY);
            stringBuilder.append(saveCityName+cityName+",");
            DoDoPreference.addCustomAppProfile(DoDoPreference.HISTORY_CITY,stringBuilder.toString());
        }
    }

    //将城市名过滤并附加前缀字母
    private List<SortModel> filledData(String[] date){
        List<SortModel> mSortList = new ArrayList<>();

        for(int i=0; i<date.length; i++){
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            String pinyin = com.xingyunyicai.ec.location.CharacterParser.getInstance().getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入字母过滤城市加入新集合
     *  将新数据排序展示
     */
    private void filterData(String filterStr){
        List<SortModel> filterDateList = new ArrayList<>();
        boolean flag;
        if(TextUtils.isEmpty(filterStr)){
            filterDateList = sourceDateList;
            flag = true;
        }else{
            filterDateList.clear();
            flag = false;
            for(SortModel sortModel : sourceDateList){
                String name = sortModel.getName();
                if(name.indexOf(filterStr.toString()) != -1 || com.xingyunyicai.ec.location.CharacterParser.getInstance().getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }

        Collections.sort(filterDateList, new PinyinComparator());
        mAdapter.setNewData(locationDataConvert.setDataList(filterDateList,flag).convert());
    }
}
