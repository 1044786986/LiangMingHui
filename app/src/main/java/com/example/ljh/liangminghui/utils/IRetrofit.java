package com.example.ljh.liangminghui.utils;

import com.example.ljh.liangminghui.home.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Retrofit服务
 */
public interface IRetrofit {
//    @FormUrlEncoded
//    @POST("")
//    Observable<HomeBean.HomeResBean> searchData(@Field("key") String key, @Field("q") String q,@Field("full") int full);

    @GET("dream/query")
    Observable<HomeBean.HomeResBean> searchData(@Query("key") String key, @Query("q") String q, @Query("full") int full);
}
