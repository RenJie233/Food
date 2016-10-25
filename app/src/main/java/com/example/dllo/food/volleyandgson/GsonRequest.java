package com.example.dllo.food.volleyandgson;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by Ren on 16/10/25.
 */
public class GsonRequest<T>  extends Request<T> {
    private final Response.Listener<T> mlistener;
    private Gson gson;
    private Class<T> mTClass;

    public GsonRequest(int method, Class<T> tClass, String url, Response.Listener<T> mlistener, Response.ErrorListener listener) {
        super(method, url, listener);
        this.mlistener = mlistener;
        gson = new Gson();
        this.mTClass = tClass;
    }
    public GsonRequest(Class<T> tClass, String url, Response.Listener<T> mlistener, Response.ErrorListener listener) {
        this(Method.GET, tClass, url, mlistener, listener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        T t = gson.fromJson(parsed, mTClass);
        return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        mlistener.onResponse(response);
    }
}
