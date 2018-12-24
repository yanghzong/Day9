package com.bway.day9.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 择木 on 2018/12/7.
 */

public class MyApplication  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
