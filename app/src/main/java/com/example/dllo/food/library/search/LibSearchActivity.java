package com.example.dllo.food.library.search;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;
import com.example.dllo.food.liteorm.DBTool;
import com.example.dllo.food.liteorm.SearchHistory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class LibSearchActivity extends BaseAty implements View.OnClickListener{

    private FrameLayout searchFl;
    private ImageView searchBackIv, searchSearch;
    private EditText searchEt;
    private Button searchDelete;
    private FragmentManager manager;
    private String result;
    private int compare;
    private Intent intent;
    //    private ArrayList<String> arrayList;

    @Override
    protected int getLayout() {
        return R.layout.activity_lib_search;
    }

    @Override
    protected void initViews() {
        searchFl = bindView(R.id.searchFl);
        searchBackIv = bindView(R.id.searchBackIv);
        searchSearch = bindView(R.id.searchSearch);
        searchEt = bindView(R.id.searchEt);
        searchDelete = bindView(R.id.searchDelete);

        setClickListener(this, searchBackIv, searchSearch, searchDelete);

    }

    @Override
    protected void initData() {

        intent = getIntent();
        compare = intent.getIntExtra("compare", 15585);

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.searchFl, new SearchFragment());
        transaction.commit();
//        arrayList = new ArrayList<>();

        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
       FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.searchBackIv:
                finish();
                break;
            case R.id.searchSearch:
                String str = searchEt.getText().toString();
                try {
                    result = URLEncoder.encode(str, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
//                insertHistoryShared(arrayList);
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                bundle.putInt("compare", compare);
                SearchResultFragment fragment = new SearchResultFragment();
                fragment.setArguments(bundle);
                transaction.replace(R.id.searchFl, fragment);
                insertHistory(str);
                break;
            case R.id.searchDelete:
                searchEt.setText("");
                transaction.replace(R.id.searchFl, new SearchFragment());
                break;
        }
        transaction.commit();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchEvent(SearchEvent event) {
        String str = event.getText();
        searchEt.setText(str);
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Bundle bundle = new Bundle();
        bundle.putString("result", result);
        bundle.putInt("compare", compare);
        SearchResultFragment fragment = new SearchResultFragment();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.searchFl, fragment);
        transaction.commit();
        insertHistory(str);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void insertHistory(String str) {
        SearchHistory history = new SearchHistory();
        history.setHistory(str);
        DBTool.getInstance().insertSearchHistory(history);
    }

    public void insertHistoryShared(ArrayList<String> arrayList) {
        SharedPreferences preferences = getSharedPreferences("history", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        for (int i = 0; i < arrayList.size(); i++) {
            editor.putString(i + "", arrayList.get(i));

        }
        editor.commit();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCompareEvent(CompareEvent event) {
        if (event != null) {
//            EventBus.getDefault().post(new CompareEvent(event.getCode()));
            intent.putExtra("compare", event.getCode());
            setResult(compare, intent);
            finish();
        }
    }
}
