package com.example.dllo.food.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/10/24.
 */
public abstract class BaseFragment extends Fragment {
    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected <T extends View> T bindView(int id) {
        return (T)getView().findViewById(id);
    }
    // 指定在哪个view里findViewById
    protected <T extends View> T bindView(View view, int id) {
        return (T) view.findViewById(id);
    }

    protected abstract int getLayout();

    protected abstract void initView();

    protected void setClickListener(View.OnClickListener clickListener, View... views) {
        for (View view :
                views) {
            view.setOnClickListener(clickListener);
        }
    }

}
