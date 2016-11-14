package com.example.dllo.food.my;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseViewHolder;
import com.example.dllo.food.liteorm.Collection;

import java.util.ArrayList;

/**
 * Created by Ren on 16/11/14.
 */
public class CollectionListAdapter extends BaseAdapter {

    ArrayList<Collection> collections;

    public void setCollections(ArrayList<Collection> collections) {
        this.collections = collections;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return collections == null ? 0 : collections.size();
    }

    @Override
    public Object getItem(int position) {
        return collections.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder viewHolder = BaseViewHolder.getViewHolder(convertView, parent, R.layout.item_article_collection);
        viewHolder.setText(R.id.articleTv, collections.get(position).getTitle());
        return viewHolder.getItemView();
    }
}
