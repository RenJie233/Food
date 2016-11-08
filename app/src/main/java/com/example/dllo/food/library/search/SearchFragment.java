package com.example.dllo.food.library.search;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseFragment;
import com.example.dllo.food.entity.KeyWordsBean;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Ren on 16/11/7.
 */
public class SearchFragment extends BaseFragment implements SearchItemClickListener{

    private RecyclerView keyWordsRv;
    private SearchRecyclerAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView() {
        keyWordsRv = bindView(R.id.keyWordsRv);
    }

    @Override
    protected void initData() {
//        SharedPreferences preferences = getActivity().getSharedPreferences("history", Context.MODE_PRIVATE);

        adapter = new SearchRecyclerAdapter();
        adapter.setSearchItemClickListener(this);
        GsonRequest<KeyWordsBean> gsonRequest = new GsonRequest<KeyWordsBean>(KeyWordsBean.class, UrlValues.LIB_SEARCH_KEYWORDS, new Response.Listener<KeyWordsBean>() {

            @Override
            public void onResponse(KeyWordsBean response) {
                adapter.setBean(response);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
                keyWordsRv.setLayoutManager(manager);
                keyWordsRv.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
    }

    @Override
    public void onItemClick(String words) {
        EventBus.getDefault().post(new SearchEvent(words));
    }
}
