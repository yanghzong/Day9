package com.bway.day9.inter;

import com.bway.day9.bean.BannerBean;
import com.bway.day9.bean.ShowBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by 择木 on 2018/12/7.
 */

public interface MynetInterface {

    @GET("0/json")
    Call<ShowBean> getData(@QueryMap Map<String , String> map);
    @GET("banner/json")
    Call<BannerBean> getBanner(@QueryMap Map<String,String> map1);
}
