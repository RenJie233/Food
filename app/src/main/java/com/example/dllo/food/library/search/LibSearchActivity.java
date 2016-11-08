package com.example.dllo.food.library.search;

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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LibSearchActivity extends BaseAty implements View.OnClickListener{

    private FrameLayout searchFl;
    private ImageView searchBackIv, searchSearch;
    private EditText searchEt;
    private Button searchDelete;
    private FragmentManager manager;
    private String result;
//    private SharedPreferences.Editor editor;
//    private SharedPreferences sharedPreferences;

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
//        sharedPreferences = getSharedPreferences("history", MODE_PRIVATE);
//        editor = sharedPreferences.edit();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.searchFl, new SearchFragment());
        transaction.commit();

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
//                editor.putString("str", str);
//                editor.commit();
                try {
                    result = URLEncoder.encode(str, "UTF-8");
//                    result = URLDecoder.decode(str, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                SearchResultFragment fragment = new SearchResultFragment();
                fragment.setArguments(bundle);

                transaction.replace(R.id.searchFl, fragment);
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
        String query = event.getText();
        searchEt.setText(query);
        try {
            result = URLEncoder.encode(query, "UTF-8");
//            result = URLDecoder.decode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        Log.d("LibSearchActivity", result);
        Bundle bundle = new Bundle();
        bundle.putString("result", result);
        SearchResultFragment fragment = new SearchResultFragment();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.searchFl, fragment);
        transaction.commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
