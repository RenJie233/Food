package com.example.dllo.food.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ren on 16/10/25.
 */
public class MyApp extends Application {


    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
