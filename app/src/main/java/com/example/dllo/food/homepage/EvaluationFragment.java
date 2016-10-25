package com.example.dllo.food.homepage;

import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.food.R;
import com.example.dllo.food.UrlValues;
import com.example.dllo.food.base.BaseFragment;
import com.example.dllo.food.volleyandgson.GsonRequest;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

/**
 * Created by Ren on 16/10/24.
 */
public class EvaluationFragment extends BaseFragment {
    private ListView lvHomeEvaluation;

    @Override
    protected int getLayout() {
        return R.layout.fragment_evaluation;
    }

    @Override
    protected void initView() {
        lvHomeEvaluation = bindView(R.id.lvHomeEvaluation);
    }

    @Override
    protected void initData() {
        GsonRequest<EvaluationBean> gsonRequest = new GsonRequest<EvaluationBean>(EvaluationBean.class, UrlValues.HOME_EVALUATION, new Response.Listener<EvaluationBean>() {
            @Override
            public void onResponse(EvaluationBean response) {
                EvaluationListAdapter adapter = new EvaluationListAdapter(getActivity());
                adapter.setBean(response);
                lvHomeEvaluation.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleTon.getInstance().getRequestQueue().add(gsonRequest);
    }
}
