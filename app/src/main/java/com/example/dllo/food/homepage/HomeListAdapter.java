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
public class HomeListAdapter extends BaseAdapter {

    Context context;
    HomePageBean bean;

    public HomeListAdapter(Context context) {
        this.context = context;
    }

    public void setBean(HomePageBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getFeeds() == null ? 0 : bean.getFeeds().size();
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
    public int getItemViewType(int position) {
        if (bean.getFeeds().get(position).getContent_type() == 1) {
            return 1;
        } else if (bean.getFeeds().get(position).getContent_type() == 2) {
            return 2;
        } else {
            return 4;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EvaluationViewHolder viewHolder = null;
        RightPicViewHolder rightPicViewHolder = null;
        ThreePicViewHolder threePicViewHolder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case 1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_right_pic, parent, false);
                    rightPicViewHolder = new RightPicViewHolder(convertView);
                    convertView.setTag(rightPicViewHolder);
                    break;
                case 2:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_three_pic, parent, false);
                    threePicViewHolder = new ThreePicViewHolder(convertView);
                    convertView.setTag(threePicViewHolder);
                    break;
                case 4:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_evaluation_list, parent, false);
                    viewHolder = new EvaluationViewHolder(convertView);
                    convertView.setTag(viewHolder);
                    break;
                default:
                    break;
            }

        } else {
            switch (type) {
                case 1:
                    rightPicViewHolder = (RightPicViewHolder) convertView.getTag();
                    break;
                case 2:
                    threePicViewHolder = (ThreePicViewHolder) convertView.getTag();
                    break;
                case 4:
                    viewHolder = (EvaluationViewHolder) convertView.getTag();
                    break;
            }

        }
        switch (type) {
            case 1:
                rightPicViewHolder.tvRightTitle.setText(bean.getFeeds().get(position).getTitle());
                rightPicViewHolder.tvRightSource.setText(bean.getFeeds().get(position).getSource());
                rightPicViewHolder.tvRightTail.setText(bean.getFeeds().get(position).getTail());
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getImages().get(0), rightPicViewHolder.ivRightPic);
                break;
            case 2:
                threePicViewHolder.tvThreeTitle.setText(bean.getFeeds().get(position).getTitle());
                threePicViewHolder.tvThreeSource.setText(bean.getFeeds().get(position).getSource());
                threePicViewHolder.tvThreeTail.setText(bean.getFeeds().get(position).getTail());
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getImages().get(0), threePicViewHolder.ivThreeOne);
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getImages().get(1), threePicViewHolder.ivThreeTwo);
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getImages().get(2), threePicViewHolder.ivThreeThr);
                break;
            case 4:
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getBackground(), viewHolder.ivEvaBg);
                viewHolder.tvEvaSource.setText(bean.getFeeds().get(position).getSource());
                viewHolder.tvEvaTitle.setText(bean.getFeeds().get(position).getTitle());
                viewHolder.tvEvaTail.setText(bean.getFeeds().get(position).getTail());
                break;
        }

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

    private class RightPicViewHolder {

        private final TextView tvRightTitle;
        private final TextView tvRightTail;
        private final TextView tvRightSource;
        private final ImageView ivRightPic;

        public RightPicViewHolder(View convertView) {
            tvRightTitle = (TextView) convertView.findViewById(R.id.tvRightTitle);
            tvRightTail = (TextView) convertView.findViewById(R.id.tvRightTail);
            tvRightSource = (TextView) convertView.findViewById(R.id.tvRightSource);
            ivRightPic = (ImageView) convertView.findViewById(R.id.ivRightPic);
        }
    }

    private class ThreePicViewHolder {

        private final TextView tvThreeTitle;
        private final TextView tvThreeSource;
        private final TextView tvThreeTail;
        private final ImageView ivThreeOne;
        private final ImageView ivThreeTwo;
        private final ImageView ivThreeThr;

        public ThreePicViewHolder(View convertView) {
            tvThreeTitle = (TextView) convertView.findViewById(R.id.tvThreeTitle);
            tvThreeSource = (TextView) convertView.findViewById(R.id.tvThreeSource);
            tvThreeTail = (TextView) convertView.findViewById(R.id.tvThreeTail);
            ivThreeOne = (ImageView) convertView.findViewById(R.id.ivThreeOne);
            ivThreeTwo = (ImageView) convertView.findViewById(R.id.ivThreeTwo);
            ivThreeThr = (ImageView) convertView.findViewById(R.id.ivThreeThr);

        }
    }
}
