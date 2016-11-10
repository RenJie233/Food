package com.example.dllo.food.library;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;
import com.example.dllo.food.entity.CompareBean;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.library.search.CompareEvent;
import com.example.dllo.food.library.search.LibSearchActivity;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**

 * Created by Ren on 16/11/9.
 */
public class LibCompareActivity extends BaseAty implements View.OnClickListener {

    private ImageView compareLeftIv, compareRightIv;
    private Button compareBack;
    private ListView compareLv;

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

        setClickListener(this, compareLeftIv, compareRightIv, compareBack);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.compareLeftIv:
                Intent intent = new Intent(this, LibSearchActivity.class);
                intent.putExtra("compare", 10010);
                startActivity(intent);
                break;
            case R.id.compareRightIv:
                Intent intent1 = new Intent(this, LibSearchActivity.class);
                intent1.putExtra("compare", 10086);
                startActivity(intent1);
                break;
            case R.id.compareBack:
                finish();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCompareEvent(CompareEvent event) {
        Log.d("LibCompareActivity", "response:" + "1513132132132");
        if (event != null) {
            String code = event.getCode();
            GsonRequest<CompareBean> gsonRequest = new GsonRequest<CompareBean>(CompareBean.class, UrlValues.LIB_COMPARE + code + UrlValues.LIB_COMPARE_FOOT, new Response.Listener<CompareBean>() {
                @Override
                public void onResponse(CompareBean response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
        }
    }
}
