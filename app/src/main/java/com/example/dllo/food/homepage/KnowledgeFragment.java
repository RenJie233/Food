package com.example.dllo.food.homepage;

import android.widget.ListView;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseFragment;

/**
 * Created by Ren on 16/10/24.
 */
public class KnowledgeFragment extends BaseFragment {
    private ListView lvHomeKnowledge;
    @Override
    protected int getLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {
        lvHomeKnowledge = bindView(R.id.lvHomeEvaluation);

    }

    @Override
    protected void initData() {

    }
}
