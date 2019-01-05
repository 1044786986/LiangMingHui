package com.example.ljh.liangminghui.utils;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit网络请求管理类
 */
public class RetrofitUtils {
    private static RetrofitUtils mRetrofitUtils;
    private  Retrofit mRetrofit;
    private static final int READ_TIME = 5;
    private static final int CONN_TIME = 5;

    private final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .readTimeout(READ_TIME, TimeUnit.SECONDS)
            .connectTimeout(CONN_TIME,TimeUnit.SECONDS)
            .build();

    public static RetrofitUtils getInstance(){
        if(mRetrofitUtils == null){
            synchronized(RetrofitUtils.class){
                if(mRetrofitUtils == null){
                    mRetrofitUtils = new RetrofitUtils();
                }
            }
        }
        return mRetrofitUtils;
    }

    public IRetrofit getIRetrofitRx2Gson(String baseUrl){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit.create(IRetrofit.class);
    }
}
