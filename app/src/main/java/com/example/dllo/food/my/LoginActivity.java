package com.example.dllo.food.my;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

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
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.authorize();

                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        PlatformDb db = platform.getDb();
                        String name = db.getUserName();
                        String icon = db.getUserIcon();
                        
                        Log.d("MainActivity", name + icon);
                        Intent intent = new Intent();
                        intent.putExtra("name", name);
                        intent.putExtra("icon", icon);
                        setResult(0, intent);
                        finish();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
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
