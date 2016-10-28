package com.example.dllo.food.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.food.R;
import com.example.dllo.food.entity.FirstPageBean;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

/**
 * Created by Ren on 16/10/25.
 */
public class FirstRecyclerAdapter extends RecyclerView.Adapter<FirstRecyclerAdapter.FirstViewHolder> {
    Context context;
    FirstPageBean bean;

    public FirstRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setBean(FirstPageBean bean) {
        this.bean = bean;
    }

    @Override
    public FirstRecyclerAdapter.FirstViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_first_recycler, parent, false);
        FirstViewHolder viewHolder = new FirstViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FirstRecyclerAdapter.FirstViewHolder holder, int position) {
        VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getCard_image(), holder.homePicIv);
        holder.homeNameTv.setText(bean.getFeeds().get(position).getPublisher());
        VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getPublisher_avatar(), holder.homeIconIv);
        holder.homeTitleTv.setText(bean.getFeeds().get(position).getTitle());

        if (bean.getFeeds().get(position).getDescription().length() > 0){
            holder.homeDesTv.setText(bean.getFeeds().get(position).getDescription());
        } else {
            holder.homeDesTv.setVisibility(View.GONE);
        }

        holder.homeCountTv.setText(bean.getFeeds().get(position).getLike_ct() + "");
    }

    @Override
    public int getItemCount() {
        return bean.getFeeds() == null ? 0 : bean.getFeeds().size();
    }

    class FirstViewHolder extends RecyclerView.ViewHolder {

        private ImageView homePicIv;
        private TextView homeTitleTv;
        private ImageView homeIconIv;
        private TextView homeNameTv;
        private TextView homeCountTv;
        private TextView homeDesTv;

        public FirstViewHolder(View itemView) {
            super(itemView);
            homePicIv = (ImageView) itemView.findViewById(R.id.homePicIv);
            homeTitleTv = (TextView) itemView.findViewById(R.id.homeTitleTv);
            homeIconIv = (ImageView) itemView.findViewById(R.id.homeIconIv);
            homeNameTv = (TextView) itemView.findViewById(R.id.homeNameTv);
            homeCountTv = (TextView) itemView.findViewById(R.id.homeCountTv);
            homeDesTv = (TextView) itemView.findViewById(R.id.homeDesTv);
        }
    }
}
