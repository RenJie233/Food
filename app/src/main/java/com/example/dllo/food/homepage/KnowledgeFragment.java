package com.example.dllo.food.homepage;

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
public class KnowledgeFragment extends BaseFragment {
    private int page;
    private PullLoadMoreRecyclerView homeKnowledgeRv;
    private HomePageAdapter adapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {
        homeKnowledgeRv = bindView(R.id.homeKnowledgeRv);
    }

    @Override
    protected void initData() {
        adapter = new HomePageAdapter(getActivity());
        page = 1;
        GsonRequest<HomeBean> gsonRequest = new GsonRequest<HomeBean>(HomeBean.class, UrlValues.HOME_HEAD + page + UrlValues.HOME_KNOWLEDGE_FOOT, new Response.Listener<HomeBean>() {
            @Override
            public void onResponse(HomeBean response) {

                adapter.setBean(response);
                homeKnowledgeRv.setAdapter(adapter);
                homeKnowledgeRv.setLinearLayout();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);

        homeKnowledgeRv.setColorSchemeResources(R.color.colorAccent);
        homeKnowledgeRv.setFooterViewText("");
        homeKnowledgeRv.setFooterViewBackgroundColor(R.color.colorTrans);
        homeKnowledgeRv.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page = 1;
                GsonRequest<HomeBean> gsonRequest = new GsonRequest<HomeBean>(HomeBean.class, UrlValues.HOME_HEAD + page + UrlValues.HOME_KNOWLEDGE_FOOT, new Response.Listener<HomeBean>() {
                    @Override
                    public void onResponse(HomeBean response) {
                        adapter.setBean(response);
                        homeKnowledgeRv.setAdapter(adapter);
                        homeKnowledgeRv.setLinearLayout();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
                homeKnowledgeRv.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                page += 1;
                GsonRequest<HomeBean> gsonRequest = new GsonRequest<HomeBean>(HomeBean.class, UrlValues.HOME_HEAD + page + UrlValues.HOME_KNOWLEDGE_FOOT, new Response.Listener<HomeBean>() {
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
                homeKnowledgeRv.setPullLoadMoreCompleted();
            }
        });

    }
}
