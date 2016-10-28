package com.example.dllo.food.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.food.R;
import com.example.dllo.food.entity.HomePageBean;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

/**
 * Created by Ren on 16/10/25.
 */
public class HomeListAdapter extends BaseAdapter {

    Context context;
    HomePageBean bean;
    private static final int TYPE_RIGHT_PIC = 0;
    private static final int TYPE_THREE_PIC = 1;
    private static final int TYPE_EVALUATION = 2;

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

    // 判断并返回item布局类型 int
    @Override
    public int getItemViewType(int position) {
        if (bean.getFeeds().get(position).getContent_type() == 1) {
            return TYPE_RIGHT_PIC;
        } else if (bean.getFeeds().get(position).getContent_type() == 2) {
            return TYPE_THREE_PIC;
        } else {
            return TYPE_EVALUATION;
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
                case TYPE_RIGHT_PIC:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_right_pic, parent, false);
                    rightPicViewHolder = new RightPicViewHolder(convertView);
                    convertView.setTag(rightPicViewHolder);
                    break;
                case TYPE_THREE_PIC:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_three_pic, parent, false);
                    threePicViewHolder = new ThreePicViewHolder(convertView);
                    convertView.setTag(threePicViewHolder);
                    break;
                case TYPE_EVALUATION:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_evaluation_list, parent, false);
                    viewHolder = new EvaluationViewHolder(convertView);
                    convertView.setTag(viewHolder);
                    break;
                default:
                    break;
            }

        } else {
            switch (type) {
                case TYPE_RIGHT_PIC:
                    rightPicViewHolder = (RightPicViewHolder) convertView.getTag();
                    break;
                case TYPE_THREE_PIC:
                    threePicViewHolder = (ThreePicViewHolder) convertView.getTag();
                    break;
                case TYPE_EVALUATION:
                    viewHolder = (EvaluationViewHolder) convertView.getTag();
                    break;
            }

        }
        switch (type) {
            case TYPE_RIGHT_PIC:
                rightPicViewHolder.rightTitleTv.setText(bean.getFeeds().get(position).getTitle());
                rightPicViewHolder.rightSourceTv.setText(bean.getFeeds().get(position).getSource());
                rightPicViewHolder.rightTailTv.setText(bean.getFeeds().get(position).getTail());
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getImages().get(0), rightPicViewHolder.rightPicIv);
                break;
            case TYPE_THREE_PIC:
                threePicViewHolder.threeTitleTv.setText(bean.getFeeds().get(position).getTitle());
                threePicViewHolder.threeSourceTv.setText(bean.getFeeds().get(position).getSource());
                threePicViewHolder.threeTailTv.setText(bean.getFeeds().get(position).getTail());
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getImages().get(0), threePicViewHolder.threeOneIv);
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getImages().get(1), threePicViewHolder.threeTwoIv);
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getImages().get(2), threePicViewHolder.threeThrIv);
                break;
            case TYPE_EVALUATION:
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getBackground(), viewHolder.evaBgIv);
                viewHolder.evaSourceTv.setText(bean.getFeeds().get(position).getSource());
                viewHolder.evaTitleTv.setText(bean.getFeeds().get(position).getTitle());
                viewHolder.evaTailTv.setText(bean.getFeeds().get(position).getTail());
                break;
        }

        return convertView;
    }

    private class EvaluationViewHolder {

        private TextView evaSourceTv;
        private TextView evaTitleTv;
        private TextView evaTailTv;
        private ImageView evaBgIv;


        public EvaluationViewHolder(View convertView) {
            evaBgIv = (ImageView) convertView.findViewById(R.id.evaBgIv);
            evaSourceTv = (TextView) convertView.findViewById(R.id.evaSourceTv);
            evaTitleTv = (TextView) convertView.findViewById(R.id.evaTitleTv);
            evaTailTv = (TextView) convertView.findViewById(R.id.evaTailTv);
        }
    }

    private class RightPicViewHolder {

        private TextView rightTitleTv;
        private TextView rightTailTv;
        private TextView rightSourceTv;
        private ImageView rightPicIv;

        public RightPicViewHolder(View convertView) {
            rightTitleTv = (TextView) convertView.findViewById(R.id.rightTitleTv);
            rightTailTv = (TextView) convertView.findViewById(R.id.rightTailTv);
            rightSourceTv = (TextView) convertView.findViewById(R.id.rightSourceTv);
            rightPicIv = (ImageView) convertView.findViewById(R.id.rightPicIv);
        }
    }

    private class ThreePicViewHolder {

        private TextView threeTitleTv;
        private TextView threeSourceTv;
        private TextView threeTailTv;
        private ImageView threeOneIv;
        private ImageView threeTwoIv;
        private ImageView threeThrIv;

        public ThreePicViewHolder(View convertView) {
            threeTitleTv = (TextView) convertView.findViewById(R.id.threeTitleTv);
            threeSourceTv = (TextView) convertView.findViewById(R.id.threeSourceTv);
            threeTailTv = (TextView) convertView.findViewById(R.id.threeTailTv);
            threeOneIv = (ImageView) convertView.findViewById(R.id.threeOneIv);
            threeTwoIv = (ImageView) convertView.findViewById(R.id.threeTwoIv);
            threeThrIv = (ImageView) convertView.findViewById(R.id.threeThrIv);

        }
    }
}
