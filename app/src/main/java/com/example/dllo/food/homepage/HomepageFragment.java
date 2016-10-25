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

    private TabLayout tabHome;
    private ViewPager vpHome;
    private ArrayList<Fragment> fragments;


    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        tabHome = bindView(R.id.tabHome);
        vpHome = bindView(R.id.vpHome);
        fragments = new ArrayList<>();
        fragments.add(new FirstPageFragment());
        fragments.add(new EvaluationFragment());
        fragments.add(new KnowledgeFragment());
        fragments.add(new FoodFragment());
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getFragmentManager());
        adapter.setFragments(fragments);
        vpHome.setAdapter(adapter);
        tabHome.setupWithViewPager(vpHome);

    }

    @Override
    protected void initData() {

    }
}
