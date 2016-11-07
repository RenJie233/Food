package com.example.dllo.food.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.food.R;
import com.example.dllo.food.entity.SortTypesBean;

/**
 * Created by Ren on 16/11/3.
 */
public class DetailGridAdapter extends BaseAdapter {

    private SortTypesBean bean;

    public void setBean(SortTypesBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getTypes().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getTypes().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetailViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_grid, parent, false);
            viewHolder = new DetailViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DetailViewHolder) convertView.getTag();
        }
        viewHolder.itemDetailTv.setText(bean.getTypes().get(position).getName());
        return convertView;
    }

    private class DetailViewHolder {
        private TextView itemDetailTv;
        public DetailViewHolder(View convertView) {
            itemDetailTv = (TextView) convertView.findViewById(R.id.itemDetailTv);
        }
    }
}
