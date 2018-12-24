package com.bway.day9.utils;

import android.os.Handler;


import com.bway.day9.bean.BannerBean;
import com.bway.day9.bean.ShowBean;
import com.bway.day9.inter.Cantant;
import com.bway.day9.inter.ICallBack;
import com.bway.day9.inter.MynetInterface;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 择木 on 2018/12/6.
 */

public class HttpUtils {

   private static  HttpUtils instance;

   private Handler handler=new Handler();

   private OkHttpClient client;
    private MynetInterface mynetInterface;

    public HttpUtils(){
       client=new OkHttpClient.Builder()
               .writeTimeout(5000, TimeUnit.MILLISECONDS)
               .connectTimeout(5000, TimeUnit.MILLISECONDS)
               .readTimeout(5000, TimeUnit.MILLISECONDS)
               .build();

   }


    public static HttpUtils getInstance() {
       //单例双重锁
        if (instance==null){
            synchronized (HttpUtils.class){
                if (instance==null){
                    instance=new HttpUtils();
                }
            }
        }
        return instance;
    }
   int i=0;
   public void  doget(final ICallBack callBack){
       Retrofit retrofit=new Retrofit.Builder()
               .client(client)
               .baseUrl(Cantant.urlpath)
               .addConverterFactory(GsonConverterFactory.create())
               .build();


       mynetInterface = retrofit.create(MynetInterface.class);


       HashMap<String,String> map=new HashMap<>();

       Call<ShowBean> showcall = mynetInterface.getData(map);

       showcall.enqueue(new Callback<ShowBean>() {
           @Override
           public void onResponse(Call<ShowBean> call, Response<ShowBean> response) {
               ShowBean.DataBean data = response.body().getData();
               List<ShowBean.DataBean.DatasBean> datas = data.getDatas();
               if (response!=null &&response.isSuccess()){
                    callBack.success(datas);
                }
           }

           @Override
           public void onFailure(Call<ShowBean> call, Throwable t) {

           }
       });

   }
    public void dogetbanner(final ICallBack callBack) {
        Retrofit retrofit1 = new Retrofit.Builder()
                .client(client)
                .baseUrl(Cantant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mynetInterface = retrofit1.create(MynetInterface.class);

        HashMap<String, String> map1 = new HashMap<>();




        Call<BannerBean> Banner = mynetInterface.getBanner(map1);

        Banner.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response1) {
                List<BannerBean.DataBean> data = response1.body().getData();
                if (response1 != null && response1.isSuccess()) {
                    callBack.success(data);
                }
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

            }
        });


    }
}
