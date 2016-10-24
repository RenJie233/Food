package com.example.dllo.food.my;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseFragment;

/**
 * Created by dllo on 16/10/21.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener{
    private RelativeLayout rLMyPhoto, rlMyCollect, rlMyUpload, rlMyOrder;
    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        rLMyPhoto = bindView(R.id.rlMyPhoto);
        rlMyCollect = bindView(R.id.rlMyCollect);
        rlMyUpload = bindView(R.id.rlMyUpload);
        rlMyOrder = bindView(R.id.rlMyOrder);

        setClickListener(this, rLMyPhoto, rlMyCollect, rlMyUpload, rlMyOrder);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlMyPhoto:
                break;
            case R.id.rlMyCollect:
                break;
            case R.id.rlMyUpload:
                break;
            case R.id.rlMyOrder:
                break;
        }
    }
}
