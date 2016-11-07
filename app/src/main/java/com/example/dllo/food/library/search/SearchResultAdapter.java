package com.example.dllo.food.library.search;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseViewHolder;
import com.example.dllo.food.entity.SearchResultBean;

/**
 * Created by Ren on 16/11/7.
 */
public class SearchResultAdapter extends BaseAdapter {

    private SearchResultBean bean;
    private String code;

    public void setBean(SearchResultBean bean) {
        this.bean = bean;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int getCount() {
        return bean.getItems() == null ? 0 : bean.getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder viewHolder = BaseViewHolder.getViewHolder(convertView, parent, R.layout.item_lib_detail);

        viewHolder.setImage(R.id.thumbIv, bean.getItems().get(position).getThumb_image_url());
        viewHolder.setText(R.id.libDetailName, bean.getItems().get(position).getName());

        if (bean.getItems().get(position).getHealth_light() == 1) {
            viewHolder.setImage(R.id.healthLight, R.mipmap.point_recommend);
        } else if (bean.getItems().get(position).getHealth_light() == 2) {
            viewHolder.setImage(R.id.healthLight, R.mipmap.point_suit);
        } else {
            viewHolder.setImage(R.id.healthLight, R.mipmap.point_less);
        }
        return viewHolder.getItemView();
    }
}
