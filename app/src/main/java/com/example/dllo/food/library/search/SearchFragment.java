package com.example.dllo.food.library.search;

import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseFragment;
import com.example.dllo.food.entity.KeyWordsBean;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.liteorm.DBTool;
import com.example.dllo.food.liteorm.SearchHistory;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by Ren on 16/11/7.
 */
public class SearchFragment extends BaseFragment implements SearchItemClickListener, View.OnClickListener {

    private RecyclerView keyWordsRv;
    private SearchRecyclerAdapter adapter;
    private ArrayList<String> arrayList;
    private SharedPreferences preferences;
    private ListView historyLv;
    private SearchListAdapter searchListAdapter;
    private RelativeLayout deleteHistoryRl;
    private LinearLayout searchLL;


    @Override
    protected int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView() {
        keyWordsRv = bindView(R.id.keyWordsRv);
        historyLv = bindView(R.id.historyLv);
        deleteHistoryRl = bindView(R.id.deleteHistoryRl);
        searchLL = bindView(R.id.searchLL);

        setClickListener(this, deleteHistoryRl);
    }

    @Override
    protected void initData() {
        searchListAdapter = new SearchListAdapter();

        DBTool.getInstance().queryAllHistory(new DBTool.OnQueryListener() {
            @Override
            public void onQuery(ArrayList<SearchHistory> histories) {
                arrayList = new ArrayList<>();
                for (int i = 0; i < histories.size(); i++) {
                    arrayList.add(histories.get(i).getHistory());
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    for (int j = i + 1; j < arrayList.size(); j++) {
                        if(arrayList.get(i).equals(arrayList.get(j))){
                            arrayList.remove(j);
                            j--;
                        }
                    }
                }
                if (arrayList.size() > 0) {
                    searchLL.setVisibility(View.VISIBLE);
                }
                searchListAdapter.setArrayList(arrayList);
                historyLv.setAdapter(searchListAdapter);

            }
        });


        adapter = new SearchRecyclerAdapter();
        adapter.setSearchItemClickListener(this);
        GsonRequest<KeyWordsBean> gsonRequest = new GsonRequest<KeyWordsBean>(KeyWordsBean.class, UrlValues.LIB_SEARCH_KEYWORDS, new Response.Listener<KeyWordsBean>() {

            @Override
            public void onResponse(KeyWordsBean response) {
                adapter.setBean(response);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
                keyWordsRv.setLayoutManager(manager);
                keyWordsRv.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);

        historyLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventBus.getDefault().post(new SearchEvent(arrayList.get(position)));
            }
        });
    }

    @Override
    public void onItemClick(String words) {
        EventBus.getDefault().post(new SearchEvent(words));

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onClick(View v) {
        DBTool.getInstance().deleteAllHistory();
        searchLL.setVisibility(View.GONE);
    }
}
