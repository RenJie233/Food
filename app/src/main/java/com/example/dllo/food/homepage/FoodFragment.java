package com.example.dllo.food.homepage;

import android.content.Intent;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseFragment;
import com.example.dllo.food.entity.HomeBean;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;


/**
 * Created by Ren on 16/10/24.
 */
public class FoodFragment extends BaseFragment implements HomeClickListener {
    private int page;
    private PullLoadMoreRecyclerView homeFoodRv;
    private HomePageAdapter adapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_food;

    }

    @Override
    protected void initView() {
        homeFoodRv = bindView(R.id.homeFoodRv);
    }

    @Override
    protected void initData() {
        adapter = new HomePageAdapter(getActivity());
        adapter.setClickListener(this);
        page = 1;
        GsonRequest<HomeBean> gsonRequest = new GsonRequest<HomeBean>(HomeBean.class, UrlValues.HOME_HEAD + page + UrlValues.HOME_FOOD_FOOT, new Response.Listener<HomeBean>() {
            @Override
            public void onResponse(HomeBean response) {
                adapter.setBean(response);
                homeFoodRv.setAdapter(adapter);
                homeFoodRv.setLinearLayout();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);

        homeFoodRv.setColorSchemeResources(R.color.colorAccent);
        homeFoodRv.setFooterViewText("");
        homeFoodRv.setFooterViewBackgroundColor(R.color.colorTrans);

        homeFoodRv.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page = 1;
                GsonRequest<HomeBean> gsonRequest = new GsonRequest<HomeBean>(HomeBean.class, UrlValues.HOME_HEAD + page + UrlValues.HOME_FOOD_FOOT, new Response.Listener<HomeBean>() {
                    @Override
                    public void onResponse(HomeBean response) {
                        adapter.setBean(response);
                        homeFoodRv.setAdapter(adapter);
                        homeFoodRv.setLinearLayout();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
                homeFoodRv.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                page += 1;
                GsonRequest<HomeBean> gsonRequest = new GsonRequest<HomeBean>(HomeBean.class, UrlValues.HOME_HEAD + page + UrlValues.HOME_FOOD_FOOT, new Response.Listener<HomeBean>() {
                    @Override
                    public void onResponse(HomeBean response) {
                        adapter.addBean(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
                homeFoodRv.setPullLoadMoreCompleted();
            }
        });

    }


    @Override
    public void onClick(String link, int id, String title) {
        Intent intent = new Intent(getActivity(), HomeDetailActivity.class);
        intent.putExtra("link", link);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}
