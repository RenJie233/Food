package com.example.dllo.food.my;

import android.view.View;
import android.widget.Button;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;

public class LoginActivity extends BaseAty implements View.OnClickListener {

    private Button loginQQ, loginWechat, loginWeibo, loginBoohee;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        loginQQ = bindView(R.id.loginQQ);
        loginWechat = bindView(R.id.loginWechat);
        loginWeibo = bindView(R.id.loginWeibo);
        loginBoohee = bindView(R.id.loginBoohee);

        setClickListener(this, loginQQ, loginWechat, loginWeibo, loginBoohee);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginQQ:

                break;
            case R.id.loginWechat:
                break;
            case R.id.loginWeibo:
                break;
            case R.id.loginBoohee:
                break;
        }
    }
}
