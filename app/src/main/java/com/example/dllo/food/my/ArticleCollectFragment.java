package com.example.dllo.food.my;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseFragment;
import com.example.dllo.food.homepage.HomeDetailActivity;
import com.example.dllo.food.liteorm.Collection;
import com.example.dllo.food.liteorm.DBTool;

import java.util.ArrayList;

/**
 * Created by Ren on 16/11/12.
 */
public class ArticleCollectFragment extends BaseFragment {

    private ListView articleCollectLv;
    private ArrayList<Collection> arrayList;
    private String link;

    @Override
    protected int getLayout() {
        return R.layout.fragment_article_collect;
    }

    @Override
    protected void initView() {
        articleCollectLv = bindView(R.id.articleCollectLv);
        arrayList = new ArrayList<>();

        articleCollectLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), HomeDetailActivity.class);
                intent.putExtra("link", arrayList.get(position).getLink());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        DBTool.getInstance().queryAllCollection(new DBTool.OnCollectionQueryListener() {
            @Override
            public void onQuery(ArrayList<Collection> collections) {
                arrayList = collections;
                CollectionListAdapter adapter = new CollectionListAdapter();
                adapter.setCollections(collections);
                articleCollectLv.setAdapter(adapter);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        DBTool.getInstance().queryAllCollection(new DBTool.OnCollectionQueryListener() {
            @Override
            public void onQuery(ArrayList<Collection> collections) {
                arrayList = collections;
                CollectionListAdapter adapter = new CollectionListAdapter();
                adapter.setCollections(collections);
                articleCollectLv.setAdapter(adapter);

            }
        });
    }
}
