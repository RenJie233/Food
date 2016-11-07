package com.example.dllo.food.library;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseFragment;
import com.example.dllo.food.entity.LibraryBean;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.library.search.LibSearchActivity;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

import java.util.ArrayList;

/**
 * Created by Ren on 16/10/21.
 */
public class LibraryFragment extends BaseFragment implements View.OnClickListener {
    private GridView libGroupGv, libBrandGv, libRestGv;
    private LinearLayout libLL;
    private Button homeSearch;


    @Override
    protected int getLayout() {
        return R.layout.fragment_library;
    }

    @Override
    protected void initView() {
        libGroupGv = bindView(R.id.libGroupGv);
        libBrandGv = bindView(R.id.libBrandGv);
        libRestGv = bindView(R.id.libRestGv);
        libLL = bindView(R.id.libLL);
        homeSearch = bindView(R.id.homeSearch);
        setClickListener(this, homeSearch);

        libLL.setFocusable(true);
        libLL.setFocusableInTouchMode(true);
        libLL.requestFocus();
    }

    @Override
    protected void initData() {
        GsonRequest<LibraryBean> gsonRequest = new GsonRequest<LibraryBean>(LibraryBean.class, UrlValues.LIBRARY, new Response.Listener<LibraryBean>() {
            @Override
            public void onResponse(final LibraryBean response) {
                // 食物分类
                LibGridAdapter groupAdapter = new LibGridAdapter(getActivity());
                groupAdapter.setCategoriesBeen((ArrayList<LibraryBean.GroupBean.CategoriesBean>) response.getGroup().get(0).getCategories());
                libGroupGv.setAdapter(groupAdapter);
                libGroupGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), LibDetailActivity.class);
                        intent.putExtra("kind", response.getGroup().get(0).getKind());
                        intent.putExtra("id", response.getGroup().get(0).getCategories().get(position).getId());
                        intent.putExtra("name", response.getGroup().get(0).getCategories().get(position).getName());
                        startActivity(intent);
                    }
                });
                // 热门品牌
                LibGridAdapter brandAdapter = new LibGridAdapter(getActivity());
                brandAdapter.setCategoriesBeen((ArrayList<LibraryBean.GroupBean.CategoriesBean>) response.getGroup().get(1).getCategories());
                libBrandGv.setAdapter(brandAdapter);
                libBrandGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), LibDetailActivity.class);
                        intent.putExtra("kind", response.getGroup().get(1).getKind());
                        intent.putExtra("id", response.getGroup().get(1).getCategories().get(position).getId());
                        intent.putExtra("name", response.getGroup().get(1).getCategories().get(position).getName());
                        startActivity(intent);
                    }
                });
                // 连锁餐饮
                LibGridAdapter restAdapter = new LibGridAdapter(getActivity());
                restAdapter.setCategoriesBeen((ArrayList<LibraryBean.GroupBean.CategoriesBean>) response.getGroup().get(2).getCategories());
                libRestGv.setAdapter(restAdapter);
                libRestGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), LibDetailActivity.class);
                        intent.putExtra("kind", response.getGroup().get(2).getKind());
                        intent.putExtra("id", response.getGroup().get(2).getCategories().get(position).getId());
                        intent.putExtra("name", response.getGroup().get(2).getCategories().get(position).getName());
                        startActivity(intent);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeSearch:
                Intent intent = new Intent(getActivity(), LibSearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
