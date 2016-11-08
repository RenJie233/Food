package com.example.dllo.food.library.search;

import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.example.dllo.food.base.BaseFragment;
import com.example.dllo.food.entity.SearchResultBean;
import com.example.dllo.food.entity.SortTypesBean;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.library.DetailGridAdapter;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * Created by Ren on 16/11/7.
 */
public class SearchResultFragment extends BaseFragment implements View.OnClickListener {

    private PullToRefreshListView searchResultLv;
    private DetailGridAdapter detailAdapter;
    private SortTypesBean sortTypesBean;
    private ArrayList<SortTypesBean.TypesBean> arrayList;
    private TextView noResultTv, searchSequenceTitle;
    private LinearLayout searchSequenceLL;
    private PopupWindow popupWindow;
    private ImageView searchSequenceArrow, searchOrderIv, resultAnimIv;
    private GridView libDetailGv;
    private int page;
    private String currentCode;
    private String result, order;
    private CheckBox recommendCb;
    private Button searchOrderBtn;
    private boolean isChecked;
    private String url;
    private SearchResultAdapter resultAdapter;
    private String urlFoot;
    private static final String DEFAULT_CODE = "none";
    private AnimationDrawable loadingAnim;

    @Override
    protected int getLayout() {
        return R.layout.fragment_result;
    }

    @Override
    protected void initView() {
        searchResultLv = bindView(R.id.searchResultLv);
        noResultTv = bindView(R.id.noResultTv);
        searchSequenceLL = bindView(R.id.searchSequenceLL);
        searchSequenceArrow = bindView(R.id.searchSequenceArrow);
        searchSequenceTitle = bindView(R.id.searchSequenceTitle);
        recommendCb = bindView(R.id.recommendCb);
        searchOrderBtn = bindView(R.id.searchOrderBtn);
        searchOrderIv = bindView(R.id.searchOrderIv);
        resultAnimIv = bindView(R.id.resultAnimIv);

        setClickListener(this, searchSequenceLL, searchOrderBtn, recommendCb);
    }

    @Override
    protected void initData() {
        resultAnimIv.setImageResource(R.drawable.anim_loading);
        loadingAnim = (AnimationDrawable) resultAnimIv.getDrawable();
        loadingAnim.start();
        order = "desc";
        isChecked = false;

        // 设置第一个为常见
        SortTypesBean.TypesBean typesBean = new SortTypesBean.TypesBean();
        arrayList = new ArrayList<>();
        typesBean.setIndex("1");
        typesBean.setCode("normal");
        typesBean.setName("常见");
        arrayList.add(typesBean);
//        sortTypesBean = new SortTypesBean();
//        sortTypesBean.setTypes(arrayList);

        detailAdapter = new DetailGridAdapter();
        GsonRequest<SortTypesBean> gsonRequest = new GsonRequest<SortTypesBean>(SortTypesBean.class, UrlValues.LIB_DETAIL_SORT, new Response.Listener<SortTypesBean>() {
            @Override
            public void onResponse(SortTypesBean response) {
                for (int i = 0; i < response.getTypes().size(); i++) {
                    arrayList.add(response.getTypes().get(i));
                }
//                arrayList.add(response.getTypes().get())
                sortTypesBean = new SortTypesBean();
//                sortTypesBean = response;
                sortTypesBean.setTypes(arrayList);
                detailAdapter.setBean(sortTypesBean);
                //                libDetailGv.setAdapter(detailAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);



        page = 1;
        result = getArguments().getString("result");
//        try {
//            result = URLDecoder.decode(result, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        Log.d("SearchResultFragment", result);
        urlFoot = UrlValues.LIB_SEARCH_FOOT + result;
        url = UrlValues.LIB_SEARCH + page + urlFoot;
        currentCode = DEFAULT_CODE;
        initInternetData(url, currentCode);
        pullUpToRefresh(urlFoot);

        popupWindow = new PopupWindow(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchSequenceLL:
                initSequencePopUpWindow();
                break;
            case R.id.searchOrderBtn:
                resultAnimIv.setVisibility(View.VISIBLE);
                loadingAnim.start();
                page = 1;
                if (order.equals("desc")) {
                    order = "asc";
                    searchOrderBtn.setText("由低到高");
                    searchOrderIv.setImageResource(R.mipmap.ic_food_ordering_up);
                    url = UrlValues.LIB_SEARCH + page + UrlValues.LIB_DETAIL_ASC + order + UrlValues.LIB_SEARCH_Q + result + UrlValues.LIB_DETAIL_ORDER + currentCode;
                    urlFoot = UrlValues.LIB_DETAIL_ASC + order + UrlValues.LIB_SEARCH_Q + result + UrlValues.LIB_DETAIL_ORDER + currentCode;
                    initInternetData(url, currentCode);
                    pullUpToRefresh(urlFoot);
                } else {
                    order = "desc";
                    searchOrderBtn.setText("由高到低");
                    searchOrderIv.setImageResource(R.mipmap.ic_food_ordering_down);
                    url = UrlValues.LIB_SEARCH + page + UrlValues.LIB_DETAIL_ASC + order + UrlValues.LIB_SEARCH_Q + result + UrlValues.LIB_DETAIL_ORDER + currentCode;
                    urlFoot = UrlValues.LIB_DETAIL_ASC + order + UrlValues.LIB_SEARCH_Q + result + UrlValues.LIB_DETAIL_ORDER + currentCode;
                    initInternetData(url, currentCode);
                    pullUpToRefresh(urlFoot);
                }
                break;
            case R.id.recommendCb:
                resultAnimIv.setVisibility(View.VISIBLE);
                loadingAnim.start();
                page = 1;
                isChecked = !isChecked;
                if (isChecked) {
                    url = UrlValues.LIB_SEARCH + page + UrlValues.LIB_SEARCH_FOOT + result + UrlValues.LIB_SEARCH_RECOMMEND;
                    urlFoot = UrlValues.LIB_SEARCH_FOOT + result + UrlValues.LIB_SEARCH_RECOMMEND;
                } else {
                    url = UrlValues.LIB_SEARCH + page + UrlValues.LIB_SEARCH_FOOT + result;
                    urlFoot = UrlValues.LIB_SEARCH_FOOT + result;
                }
                initInternetData(url, currentCode);
                pullUpToRefresh(urlFoot);
                break;
        }
    }




    public void initSequencePopUpWindow() {
        //        detailAdapter = new DetailGridAdapter();
        if (popupWindow.isShowing()) {
            // 箭头旋转动画
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.arrow_rotate_back);
            searchSequenceArrow.startAnimation(animation);
            popupWindow.dismiss();
        } else {
            // 箭头旋转动画
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.arrow_rotate);
            searchSequenceArrow.startAnimation(animation);
            // 加载popWindow 的view
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_sequence, null);
            popupWindow.setContentView(view);
            popupWindow.showAsDropDown(searchSequenceLL);

            libDetailGv = (GridView) view.findViewById(R.id.libDetailGv);
            libDetailGv.setAdapter(detailAdapter);


            // 设置营养素里item的点击事件
            libDetailGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    resultAnimIv.setVisibility(View.VISIBLE);
                    loadingAnim.start();
                    page = 1;
                    if (position == 0) {
//                        currentOrder = sortTypesBean.getTypes().get(position).getIndex();
//                        currentCode = sortTypesBean.getTypes().get(position).getCode();
                        recommendCb.setVisibility(View.VISIBLE);
                        searchOrderBtn.setVisibility(View.GONE);
                        searchOrderIv.setVisibility(View.GONE);
                        currentCode = sortTypesBean.getTypes().get(position).getCode();
                        urlFoot = UrlValues.LIB_SEARCH_FOOT + result;
                        url = UrlValues.LIB_SEARCH + page + urlFoot;
                    } else {
                        recommendCb.setVisibility(View.GONE);
                        searchOrderBtn.setVisibility(View.VISIBLE);
                        searchOrderIv.setVisibility(View.VISIBLE);
                        currentCode = sortTypesBean.getTypes().get(position).getCode();
                        urlFoot = UrlValues.LIB_SEARCH_FOOT + result + UrlValues.LIB_DETAIL_ORDER + currentCode;
                        url = UrlValues.LIB_SEARCH + page + urlFoot;
                    }

                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
//                    // 拉取营养素排序后的网络数据


//                    // 获取数据
                    initInternetData(url, currentCode);
                    // 设置营养素title的名字
                    searchSequenceTitle.setText(sortTypesBean.getTypes().get(position).getName());
                    // 设置箭头旋转动画
                    Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.arrow_rotate_back);
                    searchSequenceArrow.startAnimation(animation);
//
//                    // 上拉加载
                    pullUpToRefresh(urlFoot);
                }
            });
        }

    }


    private void initInternetData(String url, final String code) {
        GsonRequest<SearchResultBean> resultGonRequest = new GsonRequest<SearchResultBean>(SearchResultBean.class, url, new Response.Listener<SearchResultBean>() {
            @Override
            public void onResponse(SearchResultBean response) {
                if (response.getTotal_pages() == 0) {
                    noResultTv.setVisibility(View.VISIBLE);
                    loadingAnim.stop();
                    resultAnimIv.setVisibility(View.GONE);
                } else {
                    resultAdapter = new SearchResultAdapter();
                    resultAdapter.setBean(response);
                    resultAdapter.setCode(code);
                    searchResultLv.setAdapter(resultAdapter);
                    loadingAnim.stop();
                    resultAnimIv.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(resultGonRequest);
    }

    private void pullUpToRefresh(final String urlFoot) {

        searchResultLv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        searchResultLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page += 1;
                url = UrlValues.LIB_SEARCH + page + urlFoot;
                GsonRequest<SearchResultBean> gsonRequest = new GsonRequest<SearchResultBean>(SearchResultBean.class, url, new Response.Listener<SearchResultBean>() {
                    @Override
                    public void onResponse(SearchResultBean response) {

                        resultAdapter.addBean(response);
                        resultAdapter.setCode(currentCode);
                        searchResultLv.onRefreshComplete();
                        if (page == 10) {
                            searchResultLv.setMode(PullToRefreshBase.Mode.DISABLED);
                        } else if (response.getTags().isEmpty()) {
                            searchResultLv.setMode(PullToRefreshBase.Mode.DISABLED);
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
