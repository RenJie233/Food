package com.example.dllo.food.homepage;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseFragment;
import com.example.dllo.food.entity.FirstPageBean;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

/**
 * Created by Ren on 16/10/24.
 */
public class FirstPageFragment extends BaseFragment {

    private RecyclerView homeFirstRv;
    private FirstRecyclerAdapter adapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView() {
        homeFirstRv = bindView(R.id.homeFirstRv);
        adapter = new FirstRecyclerAdapter(getActivity());
    }

    @Override
    protected void initData() {
        GsonRequest<FirstPageBean> gsonRequest = new GsonRequest<FirstPageBean>(FirstPageBean.class, UrlValues.HOME_FIRST_PAGE, new Response.Listener<FirstPageBean>() {
            @Override
            public void onResponse(FirstPageBean response) {
                adapter.setBean(response);
                homeFirstRv.setAdapter(adapter);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                homeFirstRv.setLayoutManager(manager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
    }


}
