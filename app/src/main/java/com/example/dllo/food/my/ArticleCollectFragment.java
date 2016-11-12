package com.example.dllo.food.my;

import android.widget.ListView;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseFragment;

/**
 * Created by Ren on 16/11/12.
 */
public class ArticleCollectFragment extends BaseFragment {

    private ListView articleCollectLv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_article_collect;
    }

    @Override
    protected void initView() {
        articleCollectLv = bindView(R.id.articleCollectLv);
    }

    @Override
    protected void initData() {

    }
}
