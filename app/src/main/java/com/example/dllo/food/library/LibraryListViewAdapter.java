package com.example.dllo.food.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.food.R;

import java.util.ArrayList;

/**
 * Created by Ren on 16/10/25.
 */
public class LibraryListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<LibraryBean> been;

    public LibraryListViewAdapter(Context context) {
        this.context = context;
    }

    public void setBeen(ArrayList<LibraryBean> been) {
        this.been = been;
    }

    @Override
    public int getCount() {
        return been == null ? 0 : been.size();
    }

    @Override
    public Object getItem(int position) {
        return been.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LibraryListViewViewHolder viewViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_library_listview, parent, false);
            viewViewHolder = new LibraryListViewViewHolder(convertView);
            convertView.setTag(viewViewHolder);
        } else {
            viewViewHolder = (LibraryListViewViewHolder) convertView.getTag();
        }
        if (been.get(position).getGroup().get(position).getKind() == "group") {
            viewViewHolder.tvLibList.setText("食物分类");
        } else if (been.get(position).getGroup().get(position).getKind() == "brand") {
            viewViewHolder.tvLibList.setText("热门品牌");
        } else {
            viewViewHolder.tvLibList.setText("连锁餐饮");
        }
        

        return convertView;
    }

    private class LibraryListViewViewHolder {


        private final TextView tvLibList;
        private final RecyclerView rvLibList;

        public LibraryListViewViewHolder(View convertView) {
            tvLibList = (TextView) convertView.findViewById(R.id.tvLibList);
            rvLibList = (RecyclerView) convertView.findViewById(R.id.rvLibList);
        }
    }
}
