package com.example.dllo.food.library.compare;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;
import com.example.dllo.food.entity.CompareBean;
import com.example.dllo.food.entity.CompareItemBean;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.library.search.LibSearchActivity;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

import java.util.ArrayList;

/**
 * Created by Ren on 16/11/9.
 */
public class LibCompareActivity extends BaseAty implements View.OnClickListener {

    private ImageView compareLeftIv, compareRightIv, compareLeftClear, compareRightClear;
    private Button compareBack;
    private ListView compareLv;
    private TextView compareLeftTv, compareRightTv;
    private CompareAdapter adapter;
    private ArrayList<CompareItemBean> arrayList, leftArrayList, rightArrayList;

    @Override
    protected int getLayout() {
        return R.layout.activity_compare;
    }

    @Override
    protected void initViews() {
        compareLeftIv = bindView(R.id.compareLeftIv);
        compareRightIv = bindView(R.id.compareRightIv);
        compareBack = bindView(R.id.compareBack);
        compareLv = bindView(R.id.compareLv);
        compareLeftTv = bindView(R.id.compareLeftTv);
        compareRightTv = bindView(R.id.compareRightTv);
        compareLeftClear = bindView(R.id.compareLeftClear);
        compareRightClear = bindView(R.id.compareRightClear);

        setClickListener(this, compareLeftIv, compareRightIv, compareBack, compareLeftClear, compareRightClear);
    }

    @Override
    protected void initData() {
        adapter = new CompareAdapter();
        arrayList = new ArrayList<>();
        leftArrayList = new ArrayList<>();
        rightArrayList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.compareLeftIv:
                Intent leftIntent = new Intent(this, LibSearchActivity.class);
                leftIntent.putExtra("compare", 10010);
                startActivityForResult(leftIntent, 10010);
                break;
            case R.id.compareRightIv:
                Intent rightIntent = new Intent(this, LibSearchActivity.class);
                rightIntent.putExtra("compare", 10086);
                startActivityForResult(rightIntent, 10086);
                break;
            case R.id.compareBack:
                finish();
                break;
            case R.id.compareLeftClear:
                compareLeftClear.setVisibility(View.GONE);
//                if (leftArrayList.size() >= rightArrayList.size()) {
//                    for (int i = 0; i < arrayList.size(); i++) {
//                        CompareItemBean itemBean = new CompareItemBean();
//                        itemBean.setRight(arrayList.get(i).getRight());
//                        itemBean.setCenter(arrayList.get(i).getCenter());
//                        rightArrayList.add(itemBean);
//
//
//                    }
//                }
                for (int i = 0; i < rightArrayList.size(); i++) {
                    rightArrayList.get(i).setLeft("");
                }
                compareLeftIv.setImageResource(R.mipmap.img_compare_add);
                compareLeftTv.setText("");
                adapter.setBeen(rightArrayList);
                leftArrayList.clear();
                break;
            case R.id.compareRightClear:
//                if (rightArrayList.size() >= leftArrayList.size()) {
//                    for (int i = 0; i < arrayList.size(); i++) {
//                        CompareItemBean itemBean = new CompareItemBean();
//                        itemBean.setLeft(arrayList.get(i).getLeft());
//                        itemBean.setCenter(arrayList.get(i).getCenter());
//                        leftArrayList.add(itemBean);
//
//                    }
//                }
                compareRightClear.setVisibility(View.GONE);
                for (int i = 0; i < leftArrayList.size(); i++) {
                    leftArrayList.get(i).setRight("");
                }
                compareRightIv.setImageResource(R.mipmap.img_compare_add);
                compareRightTv.setText("");
                adapter.setBeen(leftArrayList);
                rightArrayList.clear();
                break;
        }
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10010) {
            final String compare = data.getStringExtra("compare");
            GsonRequest<CompareBean> gsonRequest = new GsonRequest<CompareBean>(CompareBean.class, UrlValues.LIB_COMPARE + compare + UrlValues.LIB_COMPARE_FOOT, new Response.Listener<CompareBean>() {
                @Override
                public void onResponse(CompareBean response) {
                    VolleySingleTon.getInstance().getImage(response.getThumb_image_url(), compareLeftIv);
                    compareLeftClear.setVisibility(View.VISIBLE);
                    compareLeftTv.setText(response.getName());
//                    arrayList = new ArrayList<>();

                    for (int i = 0; i < response.getNutrition().size(); i++) {
                        CompareItemBean bean = new CompareItemBean();
                        if (response.getNutrition().get(i).getUnit() == null) {
                            bean.setLeft(response.getNutrition().get(i).getValue());
                        } else {
                            bean.setLeft(response.getNutrition().get(i).getValue() + response.getNutrition().get(i).getUnit());
                        }
                        bean.setCenter(response.getNutrition().get(i).getName());
                        leftArrayList.add(bean);
                    }
                    initArrayList();
                    adapter.setBeen(arrayList);
                    compareLv.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
        } else if (resultCode == 10086) {
            final String compare = data.getStringExtra("compare");
            GsonRequest<CompareBean> gsonRequest = new GsonRequest<CompareBean>(CompareBean.class, UrlValues.LIB_COMPARE + compare + UrlValues.LIB_COMPARE_FOOT, new Response.Listener<CompareBean>() {
                @Override
                public void onResponse(CompareBean response) {
                    VolleySingleTon.getInstance().getImage(response.getThumb_image_url(), compareRightIv);
                    compareRightClear.setVisibility(View.VISIBLE);
                    compareRightTv.setText(response.getName());

                    for (int i = 0; i < response.getNutrition().size(); i++) {
                        CompareItemBean bean = new CompareItemBean();
                        if (response.getNutrition().get(i).getUnit() == null) {
                            bean.setRight(response.getNutrition().get(i).getValue());
                        } else {
                            bean.setRight(response.getNutrition().get(i).getValue() + response.getNutrition().get(i).getUnit());
                        }

                        bean.setCenter(response.getNutrition().get(i).getName());
                        rightArrayList.add(bean);
                    }
                    initArrayList();
                    adapter.setBeen(arrayList);
                    compareLv.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
        }
    }

    public void initArrayList () {
        arrayList.clear();
        if (leftArrayList.size() >= rightArrayList.size()) {
            arrayList.addAll(leftArrayList);
            for (int i = leftArrayList.size() - 1 ; i >= 0;i--){
                for (int j = rightArrayList.size() - 1;j >= 0 ;j--){
                    if (rightArrayList.get(j).getCenter().equals(leftArrayList.get(i).getCenter())){
                        arrayList.get(i).setRight(rightArrayList.get(j).getRight());
                        rightArrayList.remove(j);
                    }
                }
            }
            for (int i = 0; i < rightArrayList.size(); i++) {
                arrayList.add(rightArrayList.get(i));
            }
        } else {
            arrayList.addAll(rightArrayList);
            for (int i = rightArrayList.size() - 1;i >= 0 ;i--){
                for (int j = leftArrayList.size() - 1 ;j >= 0 ;j--){
                    if (leftArrayList.get(j).getCenter().equals(rightArrayList.get(i).getCenter()) ){
                        arrayList.get(i).setLeft(leftArrayList.get(j).getLeft());
                        leftArrayList.remove(j);
                    }
                }
            }
            for (int i = 0; i < leftArrayList.size(); i++) {
                arrayList.add(leftArrayList.get(i));
            }
        }
    }


}

