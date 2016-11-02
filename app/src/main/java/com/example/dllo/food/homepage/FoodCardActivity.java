package com.example.dllo.food.homepage;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;
import com.example.dllo.food.entity.UrlValues;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

public class FoodCardActivity extends BaseAty {

    private TextView userNameTv, postDateTv, foodDescTv;
    private ImageView userAvatarIv, foodCardIv;
    private Button cardAgreeBtn, cardInfoBtn;
    private Button cardBackBtn;

    @Override
    protected int getLayout() {
        return R.layout.activity_food_card;
    }

    @Override
    protected void initViews() {
        userNameTv = bindView(R.id.userNameTv);
        postDateTv = bindView(R.id.postDateTv);
        foodDescTv = bindView(R.id.foodDescTv);
        userAvatarIv = bindView(R.id.userAvatarIv);
        foodCardIv = bindView(R.id.foodCardIv);
        cardAgreeBtn = bindView(R.id.cardAgreeBtn);
        cardInfoBtn = bindView(R.id.cardInfoBtn);

        cardBackBtn = bindView(R.id.cardBackBtn);





    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0000);
        GsonRequest<FoodCardBean> gsonRequest = new GsonRequest<FoodCardBean>(FoodCardBean.class, UrlValues.HOME_FIRST_DETAIL_HEAD + id, new Response.Listener<FoodCardBean>() {
            @Override
            public void onResponse(final FoodCardBean response) {
                userNameTv.setText(response.getUser_name());
                postDateTv.setText(response.getPost_date());
                foodDescTv.setText(response.getDescription());
                VolleySingleTon.getInstance().getImage(response.getUser_avatar(), userAvatarIv);
                VolleySingleTon.getInstance().getImage(response.getImage_url(), foodCardIv);
                cardAgreeBtn.setText(response.getLike_ct() + "");
                if (response.getFood_code().length() > 0) {
                    cardInfoBtn.setVisibility(View.VISIBLE);
                } else {
                    cardInfoBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
                cardAgreeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardAgreeBtn.setText(response.getLike_ct() + 1 + "");
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);


        cardBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }

}
