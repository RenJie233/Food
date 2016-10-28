package com.example.dllo.food.library;

import android.widget.GridView;
import android.widget.ScrollView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.base.BaseFragment;
import com.example.dllo.food.entity.LibraryBean;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

import java.util.ArrayList;

/**
 * Created by Ren on 16/10/21.
 */
public class LibraryFragment extends BaseFragment {
    private GridView libGroupGv, libBrandGv, libRestGv;
    private ScrollView librarySv;


    @Override
    protected int getLayout() {
        return R.layout.fragment_library;
    }

    @Override
    protected void initView() {
        libGroupGv = bindView(R.id.libGroupGv);
        libBrandGv = bindView(R.id.libBrandGv);
        libRestGv = bindView(R.id.libRestGv);
        librarySv = bindView(R.id.librarySv);
        librarySv.smoothScrollBy(0,20);
    }

    @Override
    protected void initData() {
        GsonRequest<LibraryBean> gsonRequest = new GsonRequest<LibraryBean>(LibraryBean.class, UrlValues.LIBRARY, new Response.Listener<LibraryBean>() {
            @Override
            public void onResponse(LibraryBean response) {
                LibGridAdapter groupAdapter = new LibGridAdapter(getActivity());
                groupAdapter.setCategoriesBeen((ArrayList<LibraryBean.GroupBean.CategoriesBean>) response.getGroup().get(0).getCategories());
                libGroupGv.setAdapter(groupAdapter);

                LibGridAdapter brandAdapter = new LibGridAdapter(getActivity());
                brandAdapter.setCategoriesBeen((ArrayList<LibraryBean.GroupBean.CategoriesBean>) response.getGroup().get(1).getCategories());
                libBrandGv.setAdapter(brandAdapter);

                LibGridAdapter restAdapter = new LibGridAdapter(getActivity());
                restAdapter.setCategoriesBeen((ArrayList<LibraryBean.GroupBean.CategoriesBean>) response.getGroup().get(2).getCategories());
                libRestGv.setAdapter(restAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
    }
}
