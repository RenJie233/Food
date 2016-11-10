package com.example.dllo.food.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.food.volleyandgson.VolleySingleTon;

/**
 * Created by Ren on 16/11/7.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private View itemView;


    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<>();
    }

    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }

    // listView
    public static BaseViewHolder getViewHolder(View itemView, ViewGroup parent, int itemId) {
        BaseViewHolder baseViewHolder;
        if ((itemView == null)) {
            Context context = parent.getContext();
            itemView = LayoutInflater.from(context).inflate(itemId, parent, false);
            baseViewHolder = new BaseViewHolder(itemView);
            itemView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) itemView.getTag();
        }
        return baseViewHolder;
    }

    public View getItemView() {
        return itemView;
    }

//    public String findButtonId(int id, String string) {
//        string = getView(id);
//    }


    // 设置数据
    // 设置文字
    public BaseViewHolder setText(int id, String text) {
        TextView textView = getView(id);
        textView.setText(text);
        return this;
    }
    // 设置图片
    public BaseViewHolder setImage(int id, int imgId) {
        ImageView imageView = getView(id);
        imageView.setImageResource(imgId);
        return this;
    }
    // 网络图片
    public BaseViewHolder setImage(int id, String url) {
        ImageView imageView = getView(id);
        VolleySingleTon.getInstance().getImage(url, imageView);
        return this;
    }
    // item点击事件
    public BaseViewHolder setItemClick(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
        return this;
    }
    // 点击事件
    public BaseViewHolder setViewClick(int id, View.OnClickListener listener) {
        getView(id).setOnClickListener(listener);
        return this;
    }
}
