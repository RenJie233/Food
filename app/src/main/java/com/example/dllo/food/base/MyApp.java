package com.example.dllo.food.base;

import android.app.Application;
import android.content.Context;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Ren on 16/10/25.
 */
public class MyApp extends Application {


    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ShareSDK.initSDK(this,"18dd8dc5e47ac");
    }

    public static Context getContext() {
        return context;
    }
}
