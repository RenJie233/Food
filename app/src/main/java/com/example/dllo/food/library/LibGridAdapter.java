package com.example.dllo.food.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.food.R;
import com.example.dllo.food.entity.LibraryBean;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

import java.util.ArrayList;

/**
 * Created by Ren on 16/10/27.
 */
public class LibGridAdapter extends BaseAdapter {
    Context context;
//    LibraryBean bean;
    ArrayList<LibraryBean.GroupBean.CategoriesBean> categoriesBeen;

    public void setCategoriesBeen(ArrayList<LibraryBean.GroupBean.CategoriesBean> categoriesBeen) {
        this.categoriesBeen = categoriesBeen;
    }

    public LibGridAdapter(Context context) {
        this.context = context;
    }

//    public void setBean(LibraryBean bean) {
//        this.bean = bean;
//    }

    @Override
    public int getCount() {
//        return bean.getGroup().get(0).getCategories() == null ? 0 : bean.getGroup().get(0).getCategories().size();
        return categoriesBeen.size();
    }

    @Override
    public Object getItem(int position) {
//        return bean.getGroup().get(0).getCategories().get(position);
        return categoriesBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_view, parent, false);
            viewHolder = new GridViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GridViewHolder) convertView.getTag();
        }
//        viewHolder.tvItemGrid.setText(bean.getGroup().get(0).getCategories().get(position).getName());
//        VolleySingleTon.getInstance().getImage(bean.getGroup().get(0).getCategories().get(position).getImage_url(), viewHolder.ivItemGrid);
        viewHolder.tvItemGrid.setText(categoriesBeen.get(position).getName());
        VolleySingleTon.getInstance().getImage(categoriesBeen.get(position).getImage_url(), viewHolder.ivItemGrid);
        return convertView;
    }

    private class GridViewHolder {

        private final ImageView ivItemGrid;
        private final TextView tvItemGrid;

        public GridViewHolder(View convertView) {
            ivItemGrid = (ImageView) convertView.findViewById(R.id.ivItemGrid);
            tvItemGrid = (TextView) convertView.findViewById(R.id.tvItemGrid);
        }
    }
}
