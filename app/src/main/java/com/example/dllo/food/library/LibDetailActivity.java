package com.example.dllo.food.library;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

/**
 * Created by Ren on 16/11/2.
 */
public class LibDetailActivity extends BaseAty {

    private ListView libDetailLv;
    private TextView libTitleTv;
    private LinearLayout libDetailLL;

    @Override
    protected int getLayout() {
        return R.layout.activity_lib_detail;
    }

    @Override
    protected void initViews() {
        libDetailLv = bindView(R.id.libDetailLv);
        libTitleTv = bindView(R.id.libTitleTv);
        libDetailLL = bindView(R.id.libDetailLL);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String kind = intent.getStringExtra("kind");
        int id = intent.getIntExtra("id", 0);
        final String name = intent.getStringExtra("name");
        libTitleTv.setText(name);

        GsonRequest<LibDetailBean> gsonRequest = new GsonRequest<LibDetailBean>(LibDetailBean.class,
                UrlValues.LIB_DETAIL_HEAD + kind + UrlValues.LIB_DETAIL_VALUE + id + UrlValues.LIB_DETAIL_ORDER + "1" + UrlValues.LIB_DETAIL_PAGE + "1" + UrlValues.LIB_DETAIL_ASC + "0", new Response.Listener<LibDetailBean>() {
            @Override
            public void onResponse(LibDetailBean response) {
                LibDetailAdapter adapter = new LibDetailAdapter();
                adapter.setBean(response);
                libDetailLv.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);

        libDetailLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
