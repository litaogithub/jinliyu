package com.xingyunyicai.ec.location;

import com.xingyunyicai.core.ui.recycler.DataConverter;
import com.xingyunyicai.core.ui.recycler.ItemType;
import com.xingyunyicai.core.ui.recycler.MulEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by litao on 2017/11/29.
 */

public class LocationDataConvert extends DataConverter {

    private List<SortModel> sourceDateList;
    private List<String> hotCityData;
    private List<String> locationCityData;
    private boolean isFirst = false;
    private boolean clearFlag = false;

    public LocationDataConvert(List<SortModel> sourceDateList,List<String> hotCityData,List<String> locationCityData) {
        this.sourceDateList = sourceDateList;
        this.hotCityData = hotCityData;
        this.locationCityData = locationCityData;
        isFirst = true;
        clearFlag = true;
    }

    @Override
    public ArrayList<MulEntity> convert() {
        clearData();
        //根据flag标签判断首次进入加载全部数据，搜索城市时过滤非城市列表数据
        if(clearFlag && isFirst){
            addLocationHeader();
            addLocationCity();
            addHotCityHeader();
            addHotCity();
        }
        addSortCity();
        return ENTITIES;
    }
    //添加定位城市header
    private void addLocationHeader(){
        MulEntity locationHeaderEntity = MulEntity.builder()
                .setHeader("定位/最近访问")
                .setItemType(LocationItemType.LOCATION_RECENT_HEADER)
                .build();
        ENTITIES.add(locationHeaderEntity);
    }
    //添加定位和最近访问的城市
    private void addLocationCity() {
        if (null == locationCityData)
            return;
        int size = locationCityData.size() + 1;//加列表header个数
        for (String str : locationCityData) {
            MulEntity locationCity = MulEntity.builder()
                    .setBean(str)
                    .setSpanSize(1)
                    .setId(size)
                    .setItemType(LocationItemType.LOCATION_RECENT)
                    .build();
            ENTITIES.add(locationCity);
        }
    }
    //添加城市排序数据
    private void addSortCity(){
        if(null == sourceDateList)
            return;
        int size = sourceDateList.size();
        for(SortModel sortModel : sourceDateList){
            MulEntity mulEntity = MulEntity.builder()
                    .setBean(sortModel)
                    .setId(size)
                    .setTag(clearFlag)
                    .setItemType(LocationItemType.ITEM_SORT)
                    .build();
            ENTITIES.add(mulEntity);
        }
    }
    //添加热门城市header
    private void addHotCityHeader(){
        MulEntity hotcityHeaderEntity = MulEntity.builder()
                .setHeader("热门城市")
                .setItemType(ItemType.HEADER)
                .build();
        ENTITIES.add(hotcityHeaderEntity);
    }
    //添加热门城市数据
    private void addHotCity(){
        if(null == hotCityData)
            return;
        int size = hotCityData.size() + 1;
        for(String str : hotCityData){
            MulEntity hotcityHeaderEntity = MulEntity.builder()
                    .setBean(str)
                    .setId(size)
                    .setSpanSize(1)
                    .setItemType(LocationItemType.HOT_CITY)
                    .build();
            ENTITIES.add(hotcityHeaderEntity);
        }
    }


    public LocationDataConvert setDataList(List<SortModel> list,boolean claarFlag){
        this.sourceDateList = list;
        this.clearFlag = claarFlag;
        return this;
    }
}
