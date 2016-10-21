package com.example.dllo.food;


import android.view.View;
import android.widget.RadioButton;

import com.example.dllo.food.base.BaseAty;

public class MainActivity extends BaseAty implements View.OnClickListener{
    private RadioButton libraryTab, homeTab, myTab;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        libraryTab = bindView(R.id.libraryTab);
        homeTab = bindView(R.id.homeTab);
        myTab = bindView(R.id.myTab);
        setClickListener(this, libraryTab, homeTab, myTab);
    }

    // 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.libraryTab:
                break;
            case R.id.homeTab:
                break;
            case R.id.myTab:
                break;
        }
    }
}
