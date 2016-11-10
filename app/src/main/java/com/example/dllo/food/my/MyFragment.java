package com.example.dllo.food.my;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseFragment;

/**
 * Created by Ren on 16/10/21.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener{
    private RelativeLayout myPhotoRl, myCollectRl, myUploadRl, myOrderRl;
    private Button loginBtn;
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

        setClickListener(this, myPhotoRl, myCollectRl, myUploadRl, myOrderRl, loginBtn);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myPhotoRl:
                break;
            case R.id.myCollectRl:
                break;
            case R.id.myUploadRl:
                break;
            case R.id.myOrderRl:
                break;
            case R.id.loginBtn:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
