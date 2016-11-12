package com.example.dllo.food.homepage;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Ren on 16/10/21.
 */
public class HomepageFragment extends BaseFragment{

    private TabLayout homeTab;
    private ViewPager homeVp;
    private ArrayList<Fragment> fragments;


    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        homeTab = bindView(R.id.homeTab);
        homeVp = bindView(R.id.homeVp);
        fragments = new ArrayList<>();
        fragments.add(new FirstPageFragment());
        fragments.add(new EvaluationFragment());
        fragments.add(new KnowledgeFragment());
        fragments.add(new FoodFragment());

        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getFragmentManager());
        adapter.setFragments(fragments);
        homeVp.setAdapter(adapter);
        homeTab.setupWithViewPager(homeVp);

    }

    @Override
    protected void initData() {

    }
}
