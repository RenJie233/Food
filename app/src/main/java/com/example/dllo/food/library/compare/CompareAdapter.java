package com.example.dllo.food.library.compare;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseViewHolder;
import com.example.dllo.food.entity.CompareItemBean;

import java.util.ArrayList;

/**
 * Created by Ren on 16/11/11.
 */
public class CompareAdapter extends BaseAdapter {


    private int compare;
    private ArrayList<CompareItemBean> been;

    public void setBeen(ArrayList<CompareItemBean> been) {
        this.been = been;
        notifyDataSetChanged();
    }

    public void setCompare(int compare) {
        this.compare = compare;
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
        BaseViewHolder viewHolder = BaseViewHolder.getViewHolder(convertView, parent, R.layout.item_compare_list);
//        if (compare == 10010) {
//            if (bean.getNutrition().get(position).getUnit() == null) {
//                viewHolder.setText(R.id.compareLeft, bean.getNutrition().get(position).getValue());
//            } else {
//                viewHolder.setText(R.id.compareLeft, bean.getNutrition().get(position).getValue() + bean.getNutrition().get(position).getUnit());
//            }
//            viewHolder.setText(R.id.compareCenter, bean.getNutrition().get(position).getName());
//        } else {
//            if (bean.getNutrition().get(position).getUnit() == null) {
//                viewHolder.setText(R.id.compareRight, bean.getNutrition().get(position).getValue());
//            } else {
//                viewHolder.setText(R.id.compareRight, bean.getNutrition().get(position).getValue() + bean.getNutrition().get(position).getUnit());
//            }
//            viewHolder.setText(R.id.compareCenter, bean.getNutrition().get(position).getName());
//        }
        if (been.get(position).getLeft() == null) {
            viewHolder.setText(R.id.compareLeft, "--");
        } else {
            viewHolder.setText(R.id.compareLeft, been.get(position).getLeft());
        }

        viewHolder.setText(R.id.compareCenter, been.get(position).getCenter());

        if (been.get(position).getRight() == null) {
            viewHolder.setText(R.id.compareRight, "--");
        } else {
            viewHolder.setText(R.id.compareRight, been.get(position).getRight());
        }


//        viewHolder.setText(R.id.compareRight, bean.getNutrition().get(position).getValue() + bean.getNutrition().get(position).getUnit());



        return viewHolder.getItemView();
    }
}
