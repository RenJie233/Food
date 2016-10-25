package com.example.dllo.food.homepage;

import android.widget.ListView;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseFragment;

/**
 * Created by Ren on 16/10/24.
 */
public class FoodFragment extends BaseFragment {
    private ListView lvHomeFood;
    @Override
    protected int getLayout() {
        return R.layout.fragment_food;
    }

    @Override
    protected void initView() {
        lvHomeFood = bindView(R.id.lvHomeEvaluation);

    }

    @Override
    protected void initData() {

    }
}
