package com.example.dllo.food.homepage;

import android.content.Intent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;

public class HomeDetailActivity extends BaseAty {

    private WebView homeDetailWv;

    @Override
    protected int getLayout() {
        return R.layout.activity_home_detail;
    }

    @Override
    protected void initViews() {
        homeDetailWv = (WebView) findViewById(R.id.homeDetailWv);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String getLink = intent.getStringExtra("link");
        if (getLink != null) {
            homeDetailWv.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return false;
                }
            });
            WebSettings settings = homeDetailWv.getSettings();
            settings.setJavaScriptEnabled(true);
            homeDetailWv.loadUrl(getLink);
        }
    }


}
