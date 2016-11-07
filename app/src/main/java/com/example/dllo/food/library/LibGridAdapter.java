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
    private Context context;
    private ArrayList<LibraryBean.GroupBean.CategoriesBean> categoriesBeen;


    public void setCategoriesBeen(ArrayList<LibraryBean.GroupBean.CategoriesBean> categoriesBeen) {
        this.categoriesBeen = categoriesBeen;
        notifyDataSetChanged();
    }

    public LibGridAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return categoriesBeen.size();
    }

    @Override
    public Object getItem(int position) {
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
        viewHolder.itemGridTV.setText(categoriesBeen.get(position).getName());
        VolleySingleTon.getInstance().getImage(categoriesBeen.get(position).getImage_url(), viewHolder.itemGridIv);
        return convertView;
    }

    private class GridViewHolder {

        private ImageView itemGridIv;
        private TextView itemGridTV;

        public GridViewHolder(View convertView) {
            itemGridIv = (ImageView) convertView.findViewById(R.id.itemGridIv);
            itemGridTV = (TextView) convertView.findViewById(R.id.itemGridTv);
        }
    }
}
