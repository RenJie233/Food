package com.example.dllo.food.homepage;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;
import com.example.dllo.food.liteorm.Collection;
import com.example.dllo.food.liteorm.DBTool;

import java.util.ArrayList;


public class HomeDetailActivity extends BaseAty implements View.OnClickListener {

    private WebView homeDetailWv;
    private Button newsBackBtn;
    private ImageView homeDetailAnimIv, collectIv;
    private AnimationDrawable loadingAnim;
    private TextView collectTv;
    private LinearLayout collectLL;
    private boolean isCollected;
    private String getLink;
    private String getTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_home_detail;
    }

    @Override
    protected void initViews() {
        isCollected = false;
        homeDetailWv = bindView(R.id.homeDetailWv);
        newsBackBtn = bindView(R.id.newsBackBtn);
        homeDetailAnimIv = bindView(R.id.homeDetailAnimIv);
        collectTv = bindView(R.id.collectTv);
        collectIv = bindView(R.id.collectIv);
        collectLL = bindView(R.id.collectLL);

        setClickListener(this, newsBackBtn, collectLL, collectTv);
    }

    @Override
    protected void initData() {
        homeDetailAnimIv.setImageResource(R.drawable.anim_loading);
        loadingAnim = (AnimationDrawable) homeDetailAnimIv.getDrawable();
        loadingAnim.start();
        Intent intent = getIntent();
        getLink = intent.getStringExtra("link");
        getTitle = intent.getStringExtra("title");
        DBTool.getInstance().queryAllCollection(new DBTool.OnCollectionQueryListener() {
            @Override
            public void onQuery(ArrayList<Collection> collections) {
                for (int i = 0; i < collections.size(); i++) {
                    if (collections.get(i).getLink().equals(getLink)) {
                        collectTv.setText("已收藏");
                        collectIv.setImageResource(R.mipmap.ic_news_keep_heighlight);
                    }
                }
            }
        });
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
            loadingAnim.stop();
            homeDetailAnimIv.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collectTv:
                isCollected = !isCollected;
                if (isCollected) {
                    collectTv.setText("已收藏");
                    collectIv.setImageResource(R.mipmap.ic_news_keep_heighlight);
                    Collection collection = new Collection();
                    collection.setLink(getLink);
                    collection.setTitle(getTitle);
                    DBTool.getInstance().insertCollection(collection);
                } else {
                    collectTv.setText("收藏");
                    collectIv.setImageResource(R.mipmap.ic_news_keep_default);
                    DBTool.getInstance().deleteCollectionByLink(getLink);
                }
                break;
            case R.id.newsBackBtn:
                finish();
                break;
        }

    }
}
