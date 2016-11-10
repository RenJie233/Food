package com.example.dllo.food.library.search;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by Ren on 16/11/10.
 */
public class SearchListAdapter extends BaseAdapter {

    ArrayList<String> arrayList;

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder viewHolder = BaseViewHolder.getViewHolder(convertView, parent, R.layout.item_search_history);
        viewHolder.setText(R.id.itemHistoryTv, arrayList.get(position));
        return viewHolder.getItemView();
    }
}
