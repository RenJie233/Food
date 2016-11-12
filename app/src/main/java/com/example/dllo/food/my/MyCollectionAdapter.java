package com.example.dllo.food.my;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Ren on 16/11/12.
 */
public class MyCollectionAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private String[] titles = {"文章", "食物"};


    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public MyCollectionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
