package com.example.dllo.food.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseFragment;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

/**
 * Created by Ren on 16/10/21.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener{
    private RelativeLayout myPhotoRl, myCollectRl, myUploadRl, myOrderRl;
    private Button loginBtn, myResetBtn, MySetting;
    private TextView myName;
    private ImageView myDefaultIcon;

    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        myPhotoRl = bindView(R.id.myPhotoRl);
        myCollectRl = bindView(R.id.myCollectRl);
        myUploadRl = bindView(R.id.myUploadRl);
        myOrderRl = bindView(R.id.myOrderRl);
        loginBtn = bindView(R.id.loginBtn);
        myResetBtn = bindView(R.id.myResetBtn);
        myName = bindView(R.id.myName);
        myDefaultIcon = bindView(R.id.myDefaultIcon);
        MySetting = bindView(R.id.MySetting);

        setClickListener(this, myPhotoRl, myCollectRl, myUploadRl, myOrderRl, loginBtn, MySetting);
    }

    @Override
    protected void initData() {
        SharedPreferences getSp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String name = getSp.getString("name", "");
        String icon = getSp.getString("icon", "");
        if (name.equals("")|| icon.equals("")) {

        } else {
            loginBtn.setVisibility(View.GONE);
            myName.setVisibility(View.VISIBLE);
            myResetBtn.setVisibility(View.VISIBLE);
            myName.setText(name);
            VolleySingleTon.getInstance().getImage(icon, myDefaultIcon);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myPhotoRl:
                break;
            case R.id.myCollectRl:
                Intent collectIntent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(collectIntent);
                break;
            case R.id.myUploadRl:
                break;
            case R.id.myOrderRl:
                break;
            case R.id.loginBtn:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.MySetting:
                Intent setIntent = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(setIntent, 2);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            String name = data.getStringExtra("name");
            String icon = data.getStringExtra("icon");
            loginBtn.setVisibility(View.GONE);
            myName.setVisibility(View.VISIBLE);
            myResetBtn.setVisibility(View.VISIBLE);
            myName.setText(name);
            VolleySingleTon.getInstance().getImage(icon, myDefaultIcon);

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("icon", icon);
            editor.commit();

        } else if (resultCode == 3) {
            loginBtn.setVisibility(View.VISIBLE);
            myName.setVisibility(View.GONE);
            myResetBtn.setVisibility(View.GONE);
            myDefaultIcon.setImageResource(R.mipmap.ic_analyse_default);

        } else {

        }
    }
}
