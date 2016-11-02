package com.example.dllo.food.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.food.R;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

/**
 * Created by Ren on 16/11/2.
 */
public class LibDetailAdapter extends BaseAdapter {

    LibDetailBean bean;

    public void setBean(LibDetailBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getFoods() == null ? 0 : bean.getFoods().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getFoods().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LibDetailViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lib_detail, parent, false);
            viewHolder = new LibDetailViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LibDetailViewHolder) convertView.getTag();
        }
        VolleySingleTon.getInstance().getImage(bean.getFoods().get(position).getThumb_image_url(), viewHolder.thumbIv);
        viewHolder.libDetailName.setText(bean.getFoods().get(position).getName());
        viewHolder.count.setText((int) (4.184 * Integer.parseInt(bean.getFoods().get(position).getCalory())) + ".00");
        if (bean.getFoods().get(position).getHealth_light() == 1) {
            viewHolder.healthLight.setImageResource(R.mipmap.ic_recommend);
        } else if (bean.getFoods().get(position).getHealth_light() == 2) {
            viewHolder.healthLight.setImageResource(R.mipmap.ic_suit);
        } else {
            viewHolder.healthLight.setImageResource(R.mipmap.ic_less);
        }
        return convertView;
    }

    private class LibDetailViewHolder {

        private ImageView thumbIv, healthLight;
        private TextView libDetailName, count, unit;

        public LibDetailViewHolder(View convertView) {
            thumbIv = (ImageView) convertView.findViewById(R.id.thumbIv);
            healthLight = (ImageView) convertView.findViewById(R.id.healthLight);
            libDetailName = (TextView) convertView.findViewById(R.id.libDetailName);
            count = (TextView) convertView.findViewById(R.id.count);
            unit = (TextView) convertView.findViewById(R.id.unit);
        }
    }
}
