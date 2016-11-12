package com.example.dllo.food.my;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;

import java.util.ArrayList;


public class CollectionActivity extends BaseAty implements View.OnClickListener {

    private TabLayout collectionTab;
    private ViewPager collectionVp;
    private ArrayList<Fragment> fragments;
    private Button collectionBack;


    @Override
    protected int getLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initViews() {
        collectionTab = bindView(R.id.collectionTab);
        collectionVp = bindView(R.id.collectionVp);
        collectionBack = bindView(R.id.collectionBack);

        setClickListener(this, collectionBack);

        fragments = new ArrayList<>();
        fragments.add(new ArticleCollectFragment());
        fragments.add(new FoodCollectFragment());

        MyCollectionAdapter adapter = new MyCollectionAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        collectionVp.setAdapter(adapter);
        collectionTab.setupWithViewPager(collectionVp);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
