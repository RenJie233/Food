package com.example.dllo.food.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.food.R;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

/**
 * Created by Ren on 16/10/25.
 */
public class EvaluationListAdapter extends BaseAdapter {

    Context context;
    EvaluationBean bean;

    public EvaluationListAdapter(Context context) {
        this.context = context;
    }

    public void setBean(EvaluationBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getFeeds().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getFeeds().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EvaluationViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_evaluation_list, parent, false);
            viewHolder = new EvaluationViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (EvaluationViewHolder) convertView.getTag();
        }
        VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getBackground(), viewHolder.ivEvaBg);
        viewHolder.tvEvaSource.setText(bean.getFeeds().get(position).getSource());
        viewHolder.tvEvaTitle.setText(bean.getFeeds().get(position).getTitle());
        viewHolder.tvEvaTail.setText(bean.getFeeds().get(position).getTail());
        return convertView;
    }

    private class EvaluationViewHolder {

        private final TextView tvEvaSource;
        private final TextView tvEvaTitle;
        private final TextView tvEvaTail;
        private final ImageView ivEvaBg;


        public EvaluationViewHolder(View convertView) {
            ivEvaBg = (ImageView) convertView.findViewById(R.id.ivEvaBg);
            tvEvaSource = (TextView) convertView.findViewById(R.id.tvEvaSource);
            tvEvaTitle = (TextView) convertView.findViewById(R.id.tvEvaTitle);
            tvEvaTail = (TextView) convertView.findViewById(R.id.tvEvaTail);
        }
    }
}
