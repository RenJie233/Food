package com.example.dllo.food.library.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dllo.food.R;
import com.example.dllo.food.entity.KeyWordsBean;

/**
 * Created by Ren on 16/11/7.
 */
public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.MyViewHolder> {

    private KeyWordsBean bean;

    public void setBean(KeyWordsBean bean) {
        this.bean = bean;
    }

    @Override
    public SearchRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_key_words, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchRecyclerAdapter.MyViewHolder holder, int position) {
        holder.itemKeyWordsTv.setText(bean.getKeywords().get(position));
    }

    @Override
    public int getItemCount() {
        return bean.getKeyword_count() == 0 ? 0 : bean.getKeyword_count();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView itemKeyWordsTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemKeyWordsTv = (TextView) itemView.findViewById(R.id.itemKeyWordsTv);
        }
    }
}
