package com.example.dllo.food.library;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;
import com.example.dllo.food.entity.LibDetailBean;
import com.example.dllo.food.entity.LibraryBean;
import com.example.dllo.food.entity.SortTypesBean;
import com.example.dllo.food.entity.SubTypeBean;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;


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
    private ImageView sequenceArrow;
    private SubTypeBean bean;
    private ListView subTypeLv;
    private ArrayList<SubTypeBean> been;
    private SubTypeAdapter subTypeAdapter;
    private PopupWindow subPopWindow;
    private String name;

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
        sequenceArrow = bindView(R.id.sequenceArrow);

        setClickListener(this, subTypeBtn, libDetailLL, sequenceLL);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        kind = intent.getStringExtra("kind");
        getId = intent.getIntExtra("id", 0);
        if (kind.equals("group")) {
            subTypeBtn.setVisibility(View.VISIBLE);
        }
        name = intent.getStringExtra("name");
        libTitleTv.setText(name);
        page = 1;
        // 拉取初始数据
        GsonRequest<LibDetailBean> gsonRequest = new GsonRequest<LibDetailBean>(LibDetailBean.class,
                UrlValues.LIB_DETAIL_HEAD + kind + UrlValues.LIB_DETAIL_VALUE + getId + UrlValues.LIB_DETAIL_ORDER
                        + "1" + UrlValues.LIB_DETAIL_PAGE + page + UrlValues.LIB_DETAIL_ASC + "0",
                new Response.Listener<LibDetailBean>() {
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

        // 左popwindow
        popupWindow = new PopupWindow(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT);

        // 上拉加载
        pullUpToRefresh("1");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.libDetailLL:
                // 返回按钮
                finish();
                break;
            case R.id.sequenceLL:// 营养素排序
                initSequencePopUpWindow();
                break;
            case R.id.subTypeBtn:// 子类型
                View view = LayoutInflater.from(this).inflate(R.layout.pop_sub_types, null);
                subTypeLv = (ListView) view.findViewById(R.id.subTypeLv);
                been = new ArrayList<>();
                bean = new SubTypeBean();
                // 设置第一项永远是默认
                bean.setId(0);
                bean.setName("全部");
                been.add(bean);
                subTypeAdapter = new SubTypeAdapter();
                // 拉取subType数据
                GsonRequest<LibraryBean> gsonRequest = new GsonRequest<LibraryBean>(LibraryBean.class, UrlValues.LIBRARY, new Response.Listener<LibraryBean>() {
                    @Override
                    public void onResponse(LibraryBean response) {
                        bean = new SubTypeBean();
                        for (int i = 0; i < response.getGroup().get(0).getCategories().get(getId - 1).getSub_categories().size(); i++) {
                            bean = new SubTypeBean();
                            bean.setId(response.getGroup().get(0).getCategories().get(getId - 1).getSub_categories().get(i).getId());
                            bean.setName(response.getGroup().get(0).getCategories().get(getId - 1).getSub_categories().get(i).getName());
                            been.add(bean);
                        }
                        subTypeAdapter.setBeen(been);
                        subTypeLv.setAdapter(subTypeAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);

                // 设置pop大小和位置
                subPopWindow = new PopupWindow(view, 200, LinearLayout.LayoutParams.WRAP_CONTENT);
                subPopWindow.showAsDropDown(subTypeBtn, 300, -70);
                // 设置pop里的点击事件
                subTypeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        subPopWindow.dismiss();
                        subTypeBtn.setText(been.get(position).getName());
                    }
                });
                break;
        }
    }

    private void initSequencePopUpWindow() {
        detailAdapter = new DetailGridAdapter();
        if (popupWindow.isShowing()) {
            // 箭头旋转动画
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.arrow_rotate_back);
            sequenceArrow.startAnimation(animation);
            popupWindow.dismiss();
        } else {
            // 箭头旋转动画
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.arrow_rotate);
            sequenceArrow.startAnimation(animation);
            // 加载popWindow 的view
            View view = LayoutInflater.from(this).inflate(R.layout.pop_sequence, null);
            popupWindow.setContentView(view);
            popupWindow.showAsDropDown(sequenceLL);

            libDetailGv = (GridView) view.findViewById(R.id.libDetailGv);

            // 拉取根据营养素排序网络数据
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

            // 设置营养素里item的点击事件
            libDetailGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    detailOrderBtn.setVisibility(View.VISIBLE);
                    page = 1;
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                    // 拉取营养素排序后的网络数据
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
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);

                    // 设置营养素title的名字
                    sequenceTitle.setText(sortTypesBean.getTypes().get(position).getName());
                    // 设置箭头旋转动画
                    Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.arrow_rotate_back);
                    sequenceArrow.startAnimation(animation);

                    // 上拉加载
                    pullUpToRefresh(sortTypesBean.getTypes().get(position).getIndex());
                }
            });
        }

    }

    // 上拉加载
    private void pullUpToRefresh(final String order) {
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
                                UrlValues.LIB_DETAIL_ORDER + order +
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
}
