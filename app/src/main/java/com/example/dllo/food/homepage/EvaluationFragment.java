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
public class EvaluationFragment extends BaseFragment implements HomeClickListener {
    private int page;
    private PullLoadMoreRecyclerView homeEvaluationRv;
    private HomePageAdapter adapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_evaluation;
    }

    @Override
    protected void initView() {
        homeEvaluationRv = bindView(R.id.homeEvaluationRv);
    }

    @Override
    protected void initData() {
        adapter = new HomePageAdapter(getActivity());
        adapter.setClickListener(this);
        page = 1;
        GsonRequest<HomeBean> gsonRequest = new GsonRequest<HomeBean>(HomeBean.class, UrlValues.HOME_HEAD + page + UrlValues.HOME_EVALUATION_FOOT, new Response.Listener<HomeBean>() {
            @Override
            public void onResponse(HomeBean response) {
                adapter.setBean(response);
                homeEvaluationRv.setAdapter(adapter);
                homeEvaluationRv.setLinearLayout();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);

        // 设置下拉刷新和上拉加载
        homeEvaluationRv.setColorSchemeResources(R.color.colorAccent);
        homeEvaluationRv.setFooterViewText("");
        homeEvaluationRv.setFooterViewBackgroundColor(R.color.colorTrans);
        homeEvaluationRv.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            // 下拉刷新
            @Override
            public void onRefresh() {
                page = 1;
                GsonRequest<HomeBean> gsonRequest = new GsonRequest<HomeBean>(HomeBean.class, UrlValues.HOME_HEAD + page + UrlValues.HOME_EVALUATION_FOOT, new Response.Listener<HomeBean>() {
                    @Override
                    public void onResponse(HomeBean response) {
                        adapter.setBean(response);
                        homeEvaluationRv.setAdapter(adapter);
                        homeEvaluationRv.setLinearLayout();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
                homeEvaluationRv.setPullLoadMoreCompleted();
            }

            // 上拉加载
            @Override
            public void onLoadMore() {
                page += 1;
                GsonRequest<HomeBean> gsonRequest = new GsonRequest<HomeBean>(HomeBean.class, UrlValues.HOME_HEAD + page + UrlValues.HOME_EVALUATION_FOOT, new Response.Listener<HomeBean>() {
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
                homeEvaluationRv.setPullLoadMoreCompleted();
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
