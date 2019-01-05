package com.example.ljh.liangminghui.application;

import android.app.Application;

public class MyApplication extends Application {
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Application getInstance(){
        return application;
    }
}
