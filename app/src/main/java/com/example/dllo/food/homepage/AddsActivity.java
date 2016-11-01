package com.example.dllo.food.homepage;

import android.content.Intent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;

public class AddsActivity extends BaseAty {

    private WebView addsWv;

    @Override
    protected int getLayout() {
        return R.layout.activity_adds;
    }

    @Override
    protected void initViews() {
        addsWv = bindView(R.id.addsWv);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String getLink = intent.getStringExtra("link");
        if (getLink != null) {
            addsWv.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return false;
                }
            });
            addsWv.loadUrl(getLink);
        }
    }
}
