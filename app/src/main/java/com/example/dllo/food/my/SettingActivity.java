package com.example.dllo.food.my;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseAty;

public class SettingActivity extends BaseAty implements View.OnClickListener {

    private Button settingBack, signOut;


    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews() {
        settingBack = bindView(R.id.settingBack);
        signOut = bindView(R.id.signOut);

        setClickListener(this, settingBack, signOut);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settingBack:
                setResult(4);
                finish();
                break;
            case R.id.signOut:
                SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
//                Intent intent = new Intent();
                setResult(3);
                finish();
                break;
        }
    }
}
