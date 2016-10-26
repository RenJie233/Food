package com.example.dllo.food.homepage;

import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.UrlValues;
import com.example.dllo.food.base.BaseFragment;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

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
        lvHomeFood = bindView(R.id.lvHomeFood);

    }

    @Override
    protected void initData() {
        GsonRequest<HomePageBean> gsonRequest = new GsonRequest<HomePageBean>(HomePageBean.class, UrlValues.HOME_FOOD, new Response.Listener<HomePageBean>() {
            @Override
            public void onResponse(HomePageBean response) {
                HomeListAdapter adapter = new HomeListAdapter(getActivity());
                adapter.setBean(response);
                lvHomeFood.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
    }
}
