package com.example.dllo.food.library;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


/**
 * Created by Ren on 16/11/2.
 */
public class LibDetailActivity extends BaseAty implements View.OnClickListener {

    private PullToRefreshListView libDetailLv;
    private TextView libTitleTv, sequenceTitle;
    private LinearLayout libDetailLL, sequenceLL;
    private GridView libDetailGv;
    private DetailGridAdapter detailAdapter;
    private PopupWindow popupWindow;
    private String kind;
    private int getId;
    private SortTypesBean sortTypesBean;
    private int page;
    private LibDetailAdapter adapter;
    private Button detailOrderBtn, subTypeBtn;

    @Override
    protected int getLayout() {
        return R.layout.activity_lib_detail;
    }

    @Override
    protected void initViews() {
        libDetailLv = bindView(R.id.libDetailLv);
        libTitleTv = bindView(R.id.libTitleTv);
        libDetailLL = bindView(R.id.libDetailLL);
        sequenceLL = bindView(R.id.sequenceLL);
        sequenceTitle = bindView(R.id.sequenceTitle);
        detailOrderBtn = bindView(R.id.detailOrderBtn);
        subTypeBtn = bindView(R.id.subTypeBtn);

        setClickListener(this, subTypeBtn, libDetailLL, sequenceLL);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        kind = intent.getStringExtra("kind");
        getId = intent.getIntExtra("id", 0);
        final String name = intent.getStringExtra("name");
        libTitleTv.setText(name);
        page = 1;
        GsonRequest<LibDetailBean> gsonRequest = new GsonRequest<LibDetailBean>(LibDetailBean.class,
                UrlValues.LIB_DETAIL_HEAD + kind + UrlValues.LIB_DETAIL_VALUE + getId + UrlValues.LIB_DETAIL_ORDER + "1" + UrlValues.LIB_DETAIL_PAGE + page + UrlValues.LIB_DETAIL_ASC + "0", new Response.Listener<LibDetailBean>() {
            @Override
            public void onResponse(LibDetailBean response) {
                adapter = new LibDetailAdapter();
                adapter.setBean(response);
                libDetailLv.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);

//        libDetailLL.setOnClickListener(this);
//        sequenceLL.setOnClickListener(this);
        popupWindow = new PopupWindow(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT);

        libDetailLv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        libDetailLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page += 1;
                GsonRequest<LibDetailBean> gsonRequest = new GsonRequest<LibDetailBean>(LibDetailBean.class,
                        UrlValues.LIB_DETAIL_HEAD + kind + UrlValues.LIB_DETAIL_VALUE + getId +
                                UrlValues.LIB_DETAIL_ORDER + "1" +
                                UrlValues.LIB_DETAIL_PAGE + page + UrlValues.LIB_DETAIL_ASC + "0",
                        new Response.Listener<LibDetailBean>() {
                            @Override
                            public void onResponse(LibDetailBean response) {
                                adapter.addBean(response);
                                libDetailLv.onRefreshComplete();
                                if (page == 10) {
                                    libDetailLv.setMode(PullToRefreshBase.Mode.DISABLED);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
            }

        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.libDetailLL:
                finish();
                break;
            case R.id.sequenceLL:
                detailAdapter = new DetailGridAdapter();
                if (popupWindow.isShowing()) {
                    Log.d("LibDetailActivity", "popxianshi");
                    popupWindow.dismiss();
                } else {
                    View view = LayoutInflater.from(this).inflate(R.layout.pop_sequence, null);
                    popupWindow.setContentView(view);
                    //                    popupWindow.setFocusable(true);
                    libDetailGv = (GridView) view.findViewById(R.id.libDetailGv);
                    popupWindow.showAsDropDown(sequenceLL);
                    GsonRequest<SortTypesBean> gsonRequest = new GsonRequest<SortTypesBean>(SortTypesBean.class, UrlValues.LIB_DETAIL_SORT, new Response.Listener<SortTypesBean>() {
                        @Override
                        public void onResponse(SortTypesBean response) {
                            sortTypesBean = response;
                            detailAdapter.setBean(response);
                            libDetailGv.setAdapter(detailAdapter);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
                    libDetailGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                            detailOrderBtn.setVisibility(View.VISIBLE);
                            page = 1;
                            if (popupWindow.isShowing()) {
                                popupWindow.dismiss();
                            }
                            GsonRequest<LibDetailBean> gsonRequest = new GsonRequest<LibDetailBean>(LibDetailBean.class,
                                    UrlValues.LIB_DETAIL_HEAD + kind + UrlValues.LIB_DETAIL_VALUE + getId +
                                            UrlValues.LIB_DETAIL_ORDER + sortTypesBean.getTypes().get(position).getIndex() +
                                            UrlValues.LIB_DETAIL_PAGE + page + UrlValues.LIB_DETAIL_ASC + "0",
                                    new Response.Listener<LibDetailBean>() {
                                        @Override
                                        public void onResponse(LibDetailBean response) {
                                            adapter = new LibDetailAdapter();
                                            adapter.setBean(response);
                                            libDetailLv.setAdapter(adapter);
                                            sequenceTitle.setText(sortTypesBean.getTypes().get(position).getName());

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                            VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);

                            libDetailLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                                @Override
                                public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

                                }

                                @Override
                                public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                                    page += 1;
                                    GsonRequest<LibDetailBean> gsonRequest = new GsonRequest<LibDetailBean>(LibDetailBean.class,
                                            UrlValues.LIB_DETAIL_HEAD + kind + UrlValues.LIB_DETAIL_VALUE + getId +
                                                    UrlValues.LIB_DETAIL_ORDER + sortTypesBean.getTypes().get(position).getIndex() +
                                                    UrlValues.LIB_DETAIL_PAGE + page + UrlValues.LIB_DETAIL_ASC + "0",
                                            new Response.Listener<LibDetailBean>() {
                                                @Override
                                                public void onResponse(LibDetailBean response) {
                                                    adapter.addBean(response);
                                                    libDetailLv.onRefreshComplete();
                                                    if (page == 10) {
                                                        libDetailLv.setMode(PullToRefreshBase.Mode.DISABLED);
                                                    }
                                                }
                                            }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    });
                                    VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);

                                }

                            });
                        }
                    });
                }
                break;
            case R.id.subTypeBtn:
                View view = LayoutInflater.from(this).inflate(R.layout.pop_sub_types, null);
                ListView subTypeLv = (ListView) view.findViewById(R.id.subTypeLv);
                popupWindow.setContentView(view);
                popupWindow.showAsDropDown(subTypeBtn);
                Log.d("LibDetailActivity", "dianjile");
                break;
        }
    }
}
