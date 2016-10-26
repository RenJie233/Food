package com.example.dllo.food;


import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.example.dllo.food.base.BaseAty;
import com.example.dllo.food.homepage.HomepageFragment;
import com.example.dllo.food.library.LibraryFragment;
import com.example.dllo.food.my.MyFragment;

public class MainActivity extends BaseAty implements View.OnClickListener{
    private RadioButton libraryTab, homeTab, myTab;
    private android.support.v4.app.FragmentManager manager;
    private FragmentTransaction transaction;



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

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.mainFrame, new LibraryFragment());
        transaction.commit();

    }

    // 点击事件
    @Override
    public void onClick(View v) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.libraryTab:
                transaction.replace(R.id.mainFrame, new LibraryFragment());
                break;
            case R.id.homeTab:
                transaction.replace(R.id.mainFrame, new HomepageFragment());
                break;
            case R.id.myTab:
                transaction.replace(R.id.mainFrame, new MyFragment());
                break;
        }
        transaction.commit();
    }
}
