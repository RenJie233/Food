package com.example.dllo.food.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.food.R;
import com.example.dllo.food.entity.HomeBean;
import com.example.dllo.food.volleyandgson.VolleySingleTon;


/**
 * Created by Ren on 16/10/31.
 */
public class HomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_RIGHT_PIC = 1;
    private static final int TYPE_THREE_PIC = 2;
    private static final int TYPE_EVALUATION = 4;
    private static final int TYPE_FIRST_NORMAL = 5;
    private static final int TYPE_FIRST_ADDS = 6;

    private HomeClickListener clickListener;
    private Context context;
    private HomeBean bean;


    public void setClickListener(HomeClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public HomePageAdapter(Context context) {
        this.context = context;
    }

    public void setBean(HomeBean bean) {
        this.bean = bean;
    }

    public void addBean(HomeBean bean) {
        this.bean.addData(bean.getFeeds());
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        RecyclerView.ViewHolder holder = getViewHolderByViewType(viewType);
        switch (viewType) {
            case TYPE_RIGHT_PIC:
                return new RightPicViewHolder(LayoutInflater.from(context).inflate(R.layout.item_right_pic, parent, false));
            case TYPE_THREE_PIC:
                return new ThreePicViewHolder(LayoutInflater.from(context).inflate(R.layout.item_three_pic, parent, false));
            case TYPE_EVALUATION:
                return new EvaluationViewHolder(LayoutInflater.from(context).inflate(R.layout.item_evaluation_list, parent, false));
            case TYPE_FIRST_NORMAL:
                return new FirstViewHolder(LayoutInflater.from(context).inflate(R.layout.item_first_recycler, parent, false));
            case TYPE_FIRST_ADDS:
                return new AddsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_first_adds, parent, false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (bean.getFeeds().get(position).getContent_type() == 1) {
            return TYPE_RIGHT_PIC;
        } else if(bean.getFeeds().get(position).getContent_type() == 2) {
            return TYPE_THREE_PIC;
        } else if (bean.getFeeds().get(position).getContent_type() == 4) {
            return TYPE_EVALUATION;
        } else if (bean.getFeeds().get(position).getContent_type() == 5) {
            return TYPE_FIRST_NORMAL;
        } else {
            return TYPE_FIRST_ADDS;
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case TYPE_RIGHT_PIC:
                RightPicViewHolder rightPicViewHolder = (RightPicViewHolder) holder;
                rightPicViewHolder.rightTitleTv.setText(bean.getFeeds().get(position).getTitle());
                rightPicViewHolder.rightSourceTv.setText(bean.getFeeds().get(position).getSource());
                rightPicViewHolder.rightTailTv.setText(bean.getFeeds().get(position).getTail());
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getImages().get(0), rightPicViewHolder.rightPicIv);
                rightPicViewHolder.rightLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onClick(bean.getFeeds().get(position).getLink(), bean.getFeeds().get(position).getItem_id());
                    }
                });
                break;
            case TYPE_THREE_PIC:
                ThreePicViewHolder threePicViewHolder = (ThreePicViewHolder) holder;
                threePicViewHolder.threeTitleTv.setText(bean.getFeeds().get(position).getTitle());
                threePicViewHolder.threeSourceTv.setText(bean.getFeeds().get(position).getSource());
                threePicViewHolder.threeTailTv.setText(bean.getFeeds().get(position).getTail());
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getImages().get(0), threePicViewHolder.threeOneIv);
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getImages().get(1), threePicViewHolder.threeTwoIv);
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getImages().get(2), threePicViewHolder.threeThrIv);
                threePicViewHolder.threeLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onClick(bean.getFeeds().get(position).getLink(), bean.getFeeds().get(position).getItem_id());
                    }
                });
                break;
            case TYPE_EVALUATION:
                EvaluationViewHolder viewHolder = (EvaluationViewHolder) holder;
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getBackground(), viewHolder.evaBgIv);
                viewHolder.evaSourceTv.setText(bean.getFeeds().get(position).getSource());
                viewHolder.evaTitleTv.setText(bean.getFeeds().get(position).getTitle());
                viewHolder.evaTailTv.setText(bean.getFeeds().get(position).getTail());
                viewHolder.evaRL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onClick(bean.getFeeds().get(position).getLink(), bean.getFeeds().get(position).getItem_id());
                    }
                });
                break;
            case TYPE_FIRST_NORMAL:
                FirstViewHolder firstViewHolder = (FirstViewHolder) holder;
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getCard_image(), firstViewHolder.homePicIv);
                firstViewHolder.homeNameTv.setText(bean.getFeeds().get(position).getPublisher());
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getPublisher_avatar(), firstViewHolder.homeIconIv);
                firstViewHolder.homeTitleTv.setText(bean.getFeeds().get(position).getTitle());
                if (bean.getFeeds().get(position).getDescription() == null){
                    firstViewHolder.homeDesTv.setVisibility(View.GONE);
                } else if (bean.getFeeds().get(position).getDescription().length() > 0){
                    firstViewHolder.homeDesTv.setText(bean.getFeeds().get(position).getDescription());
                } else {
                    firstViewHolder.homeDesTv.setVisibility(View.GONE);
                }
                firstViewHolder.homeCountTv.setText(bean.getFeeds().get(position).getLike_ct() + "");
                firstViewHolder.firstRL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onClick(bean.getFeeds().get(position).getLink(), bean.getFeeds().get(position).getItem_id());
                    }
                });
                break;
            case TYPE_FIRST_ADDS:
                AddsViewHolder addsViewHolder = (AddsViewHolder) holder;
                VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getCard_image(), addsViewHolder.firstAddsIv);
                addsViewHolder.addsLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onClick(bean.getFeeds().get(position).getLink(), bean.getFeeds().get(position).getItem_id());
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return bean.getFeeds().size();
    }


    private class RightPicViewHolder extends RecyclerView.ViewHolder {
        private TextView rightTitleTv;
        private TextView rightTailTv;
        private TextView rightSourceTv;
        private ImageView rightPicIv;
        private LinearLayout rightLL;

        public RightPicViewHolder(View itemView) {
            super(itemView);
            rightTitleTv = (TextView) itemView.findViewById(R.id.rightTitleTv);
            rightTailTv = (TextView) itemView.findViewById(R.id.rightTailTv);
            rightSourceTv = (TextView) itemView.findViewById(R.id.rightSourceTv);
            rightPicIv = (ImageView) itemView.findViewById(R.id.rightPicIv);
            rightLL = (LinearLayout) itemView.findViewById(R.id.rightLL);
        }
    }

    private class ThreePicViewHolder extends RecyclerView.ViewHolder {
        private TextView threeTitleTv;
        private TextView threeSourceTv;
        private TextView threeTailTv;
        private ImageView threeOneIv;
        private ImageView threeTwoIv;
        private ImageView threeThrIv;
        private LinearLayout threeLL;

        public ThreePicViewHolder(View itemView) {
            super(itemView);
            threeTitleTv = (TextView) itemView.findViewById(R.id.threeTitleTv);
            threeSourceTv = (TextView) itemView.findViewById(R.id.threeSourceTv);
            threeTailTv = (TextView) itemView.findViewById(R.id.threeTailTv);
            threeOneIv = (ImageView) itemView.findViewById(R.id.threeOneIv);
            threeTwoIv = (ImageView) itemView.findViewById(R.id.threeTwoIv);
            threeThrIv = (ImageView) itemView.findViewById(R.id.threeThrIv);
            threeLL = (LinearLayout) itemView.findViewById(R.id.threeLL);
        }
    }

    private class EvaluationViewHolder extends RecyclerView.ViewHolder {
        private TextView evaSourceTv;
        private TextView evaTitleTv;
        private TextView evaTailTv;
        private ImageView evaBgIv;
        private RelativeLayout evaRL;

        public EvaluationViewHolder(View itemView) {
            super(itemView);
            evaBgIv = (ImageView) itemView.findViewById(R.id.evaBgIv);
            evaSourceTv = (TextView) itemView.findViewById(R.id.evaSourceTv);
            evaTitleTv = (TextView) itemView.findViewById(R.id.evaTitleTv);
            evaTailTv = (TextView) itemView.findViewById(R.id.evaTailTv);
            evaRL = (RelativeLayout) itemView.findViewById(R.id.evaRL);
        }
    }

    private class FirstViewHolder extends RecyclerView.ViewHolder {
        private ImageView homePicIv;
        private TextView homeTitleTv;
        private ImageView homeIconIv;
        private TextView homeNameTv;
        private TextView homeCountTv;
        private TextView homeDesTv;
        private RelativeLayout firstRL;
        public FirstViewHolder(View itemView) {
            super(itemView);
            homePicIv = (ImageView) itemView.findViewById(R.id.homePicIv);
            homeTitleTv = (TextView) itemView.findViewById(R.id.homeTitleTv);
            homeIconIv = (ImageView) itemView.findViewById(R.id.homeIconIv);
            homeNameTv = (TextView) itemView.findViewById(R.id.homeNameTv);
            homeCountTv = (TextView) itemView.findViewById(R.id.homeCountTv);
            homeDesTv = (TextView) itemView.findViewById(R.id.homeDesTv);
            firstRL = (RelativeLayout) itemView.findViewById(R.id.firstRL);
        }
    }

    private class AddsViewHolder extends RecyclerView.ViewHolder {
        private ImageView firstAddsIv;
        private LinearLayout addsLL;

        public AddsViewHolder(View itemView) {
            super(itemView);
            firstAddsIv = (ImageView) itemView.findViewById(R.id.firstAddIv);
            addsLL = (LinearLayout) itemView.findViewById(R.id.addsLL);
        }
    }
}
