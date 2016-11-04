package com.example.dllo.food.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.food.R;
import com.example.dllo.food.entity.SubTypeBean;

import java.util.ArrayList;

/**
 * Created by Ren on 16/11/4.
 */
public class SubTypeAdapter extends BaseAdapter {

    ArrayList<SubTypeBean> been;

    public void setBeen(ArrayList<SubTypeBean> been) {
        this.been = been;
        notifyDataSetChanged();
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
        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_type, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.itemSubTv.setText(been.get(position).getName());
        return convertView;
    }


    private class MyViewHolder {
        private TextView itemSubTv;
        public MyViewHolder(View convertView) {
            itemSubTv = (TextView) convertView.findViewById(R.id.itemSubTv);
        }
    }
}
