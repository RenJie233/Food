package com.example.dllo.food.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.food.R;
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
        VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getCard_image(), holder.ivHomePic);
        holder.tvHomeName.setText(bean.getFeeds().get(position).getPublisher());
        VolleySingleTon.getInstance().getImage(bean.getFeeds().get(position).getPublisher_avatar(), holder.ivHomeIcon);
        holder.tvHomeTitle.setText(bean.getFeeds().get(position).getTitle());

        if (bean.getFeeds().get(position).getDescription().length() > 0){
            holder.tvHomeDes.setText(bean.getFeeds().get(position).getDescription());
        } else {
            holder.tvHomeDes.setVisibility(View.GONE);
        }

        holder.tvHomeCount.setText(bean.getFeeds().get(position).getLike_ct() + "");
    }

    @Override
    public int getItemCount() {
        return bean.getFeeds() == null ? 0 : bean.getFeeds().size();
    }

    class FirstViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivHomePic;
        private final TextView tvHomeTitle;
        private final ImageView ivHomeIcon;
        private final TextView tvHomeName;
        private final TextView tvHomeCount;
        private final TextView tvHomeDes;

        public FirstViewHolder(View itemView) {
            super(itemView);
            ivHomePic = (ImageView) itemView.findViewById(R.id.ivHomePic);
            tvHomeTitle = (TextView) itemView.findViewById(R.id.tvHomeTitle);
            ivHomeIcon = (ImageView) itemView.findViewById(R.id.ivHomeIcon);
            tvHomeName = (TextView) itemView.findViewById(R.id.tvHomeName);
            tvHomeCount = (TextView) itemView.findViewById(R.id.tvHomeCount);
            tvHomeDes = (TextView) itemView.findViewById(R.id.tvHomeDes);
        }
    }
}
