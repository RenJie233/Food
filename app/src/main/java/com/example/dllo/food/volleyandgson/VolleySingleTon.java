package com.example.dllo.food.volleyandgson;

import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.dllo.food.R;

/**
 * Created by Ren on 16/10/25.
 */
public class VolleySingleTon {

    private static VolleySingleTon volleySingleTon;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private VolleySingleTon() {
        requestQueue = Volley.newRequestQueue(MyApp.getContext());

        imageLoader = new ImageLoader(requestQueue, new MemoryCache());
    }

    public static VolleySingleTon getInstance() {
        if (volleySingleTon == null) {
            synchronized (VolleySingleTon.class) {
                if (volleySingleTon == null) {
                    volleySingleTon = new VolleySingleTon();
                }
            }
        }
        return volleySingleTon;
    }


    public void getImage(String url, ImageView imageView) {
        imageLoader.get(url, ImageLoader.getImageListener(imageView, R.mipmap.img_none, R.mipmap.img_none));
    }


    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void addRequest(Request<T> request) {
        requestQueue.add(request);
    }
}
