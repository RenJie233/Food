package com.example.dllo.food.homepage;

import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.base.BaseFragment;
import com.example.dllo.food.entity.HomePageBean;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

/**
 * Created by Ren on 16/10/24.
 */
public class EvaluationFragment extends BaseFragment {
    private ListView homeEvaluationLv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_evaluation;
    }

    @Override
    protected void initView() {
        homeEvaluationLv = bindView(R.id.homeEvaluationLv);
    }

    @Override
    protected void initData() {
        GsonRequest<HomePageBean> gsonRequest = new GsonRequest<HomePageBean>(HomePageBean.class, UrlValues.HOME_EVALUATION, new Response.Listener<HomePageBean>() {
            @Override
            public void onResponse(HomePageBean response) {
                HomeListAdapter adapter = new HomeListAdapter(getActivity());
                adapter.setBean(response);
                homeEvaluationLv.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
    }
}
