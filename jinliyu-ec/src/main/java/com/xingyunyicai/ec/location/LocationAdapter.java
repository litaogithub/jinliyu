package com.xingyunyicai.ec.location;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.xingyunyicai.core.ui.recycler.ItemType;
import com.xingyunyicai.core.ui.recycler.ItemTypeBuilder;
import com.xingyunyicai.core.ui.recycler.MulAdapter;
import com.xingyunyicai.core.ui.recycler.MulEntity;
import com.xingyunyicai.core.ui.recycler.MulFields;
import com.xingyunyicai.core.ui.recycler.MulHolder;
import com.xingyunyicai.ec.R;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by litao on 2017/11/29.
 */

public class LocationAdapter extends MulAdapter {

    private int totalSize;
    private int hotCitySize;
    private int locationCitySize;

    protected LocationAdapter(List<MulEntity> data) {
        super(data);
    }

    @Override
    public LinkedHashMap<Integer, Integer> addItemTypes(ItemTypeBuilder builder) {
        return builder.addItemType(LocationItemType.ITEM_SORT, R.layout.item_location_sort)
                .addItemType(LocationItemType.HOT_CITY,R.layout.item_location_hotcity)
                .addItemType(ItemType.HEADER,R.layout.item_location_header)
                .addItemType(LocationItemType.LOCATION_RECENT,R.layout.item_location_hotcity)
                .addItemType(LocationItemType.LOCATION_RECENT_HEADER,R.layout.item_location_header)
                .build();
    }

    @Override
    public void handle(MulHolder holder, MulEntity entity) {
        switch (holder.getItemViewType()){
            case LocationItemType.ITEM_SORT:
                SortModel sortModel = entity.getField(MulFields.BEAN);
                boolean flag = entity.getField(MulFields.TAG);
                if(flag){
                    int section = getSectionForPosition(holder.getAdapterPosition());
                    if(holder.getAdapterPosition() == getPositionForSection(section)){
                        holder.setVisible(R.id.catalog,true);
                        holder.setText(R.id.catalog,sortModel.getSortLetters());
                    }else{
                        holder.setGone(R.id.catalog,false);
                    }
                }else{
                    holder.setGone(R.id.catalog,false);
                }
                holder.setText(R.id.title,sortModel.getName());
                break;
            case LocationItemType.HOT_CITY:
                String hotCity = entity.getField(MulFields.BEAN);
                hotCitySize = entity.getField(MulFields.ID);
                holder.setText(R.id.btn_hotcity,hotCity);
                break;
            case ItemType.HEADER:
                String header = entity.getField(MulFields.BEAN);
                holder.setText(R.id.hotCity_header,header);
                break;
            case LocationItemType.LOCATION_RECENT_HEADER:
                String locationHeader = entity.getField(MulFields.BEAN);
                holder.setText(R.id.hotCity_header,locationHeader);
                break;
            case LocationItemType.LOCATION_RECENT:
                String locationRecent = entity.getField(MulFields.BEAN);
                locationCitySize = entity.getField(MulFields.ID);
                TextView textView = holder.getView(R.id.btn_hotcity);
                if(holder.getAdapterPosition() == 1){
                    Drawable drawable = mContext.getResources().getDrawable(android.R.drawable.ic_lock_idle_alarm);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    textView.setCompoundDrawables(drawable,null,null,null);
                }else{
                    textView.setCompoundDrawables(null,null,null,null);
                }
                textView.setText(locationRecent);
                break;
            default:
                break;
        }
    }

    public int getSectionForPosition(int position) {
        return ((SortModel)getData().get(position).getField(MulFields.BEAN)).getSortLetters().charAt(0);
    }

    public int getPositionForSection(int section) {
        int size = getData().size();
        totalSize = hotCitySize + locationCitySize;
        for (int i = totalSize; i < size; i++) {
            String sortStr = ((SortModel)getData().get(i).getField(MulFields.BEAN)).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }
}
