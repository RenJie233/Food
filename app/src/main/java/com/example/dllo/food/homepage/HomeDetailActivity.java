package com.example.dllo.food.homepage;

import android.content.Intent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;


public class HomeDetailActivity extends BaseAty implements View.OnClickListener {

    private WebView homeDetailWv;
    private Button newsBackBtn;

    @Override
    protected int getLayout() {
        return R.layout.activity_home_detail;
    }

    @Override
    protected void initViews() {
//        homeDetailWv = (WebView) findViewById(R.id.homeDetailWv);
        homeDetailWv = bindView(R.id.homeDetailWv);
        newsBackBtn = bindView(R.id.newsBackBtn);
        setClickListener(this, newsBackBtn);
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


    @Override
    public void onClick(View v) {
        finish();
    }
}
