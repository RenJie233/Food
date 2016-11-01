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
public class FirstPageFragment extends BaseFragment implements HomeClickListener {

    private int page;
    private PullLoadMoreRecyclerView homeFirstRv;
    private HomePageAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView() {
        homeFirstRv = bindView(R.id.homeFirstRv);

    }

    @Override
    protected void initData() {
        adapter = new HomePageAdapter(getActivity());
        adapter.setClickListener(this);
        page = 1;
        GsonRequest<HomeBean> gsonRequest = new GsonRequest<HomeBean>(HomeBean.class, UrlValues.HOME_HEAD + page + UrlValues.HOME_FIRST_PAGE_FOOT, new Response.Listener<HomeBean>() {
            @Override
            public void onResponse(HomeBean response) {
                adapter.setBean(response);
                homeFirstRv.setAdapter(adapter);
                homeFirstRv.setStaggeredGridLayout(2);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);

        homeFirstRv.setColorSchemeResources(R.color.colorAccent);
        homeFirstRv.setFooterViewText("");
        homeFirstRv.setFooterViewBackgroundColor(R.color.colorTrans);
        homeFirstRv.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page = 1;
                GsonRequest<HomeBean> gsonRequest = new GsonRequest<HomeBean>(HomeBean.class, UrlValues.HOME_HEAD + page + UrlValues.HOME_FIRST_PAGE_FOOT, new Response.Listener<HomeBean>() {
                    @Override
                    public void onResponse(HomeBean response) {
                        adapter.setBean(response);
                        homeFirstRv.setAdapter(adapter);
                        homeFirstRv.setStaggeredGridLayout(2);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);

                homeFirstRv.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                page += 1;
                GsonRequest<HomeBean> gsonRequest = new GsonRequest<HomeBean>(HomeBean.class, UrlValues.HOME_HEAD + page + UrlValues.HOME_FIRST_PAGE_FOOT, new Response.Listener<HomeBean>() {
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
                homeFirstRv.setPullLoadMoreCompleted();
            }

        });




    }



    @Override
    public void onClick(String link, int id) {
        if (link != null) {
            Intent intent = new Intent(getActivity(), AddsActivity.class);
            intent.putExtra("link", link);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getActivity(), FoodCardActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }
}
