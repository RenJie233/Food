package com.example.dllo.food;

import android.content.Intent;
import android.os.CountDownTimer;

import com.example.dllo.food.base.BaseAty;

public class WelcomeActivity extends BaseAty {


    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews() {
        CountDownTimer timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.start();
    }

    @Override
    protected void initData() {

    }

}
