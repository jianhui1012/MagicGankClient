package com.golike.magicgankclient.http;

import android.support.annotation.NonNull;

import com.golike.magicgankclient.model.SearchData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018/2/2.
 */

public class HttpUtils {

    public static final String BASE_URL = "http://gank.io/api/";

    private static final int DEFAULT_TIMEOUT = 3;

    private Retrofit retrofit;

    private ApiService apiService;

    private HttpUtils(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder().client(builder.build()).addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(BASE_URL).build();

        apiService = retrofit.create(ApiService.class);
    }

    /**
     * 防止多线程出现多个实例化
     */
    private static class SingleHolder{
        private static final HttpUtils httpUtils = new HttpUtils();
    }

    public static HttpUtils getInstance(){
        return SingleHolder.httpUtils;
    }

    public void getHomeData(Subscriber<SearchData> subscriber, @NonNull String type, @NonNull int count, @NonNull int page){
        apiService.getHomeData(type,count,page).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

    //=========================================================================================
    //================================================ 列出所有的接口
    //=========================================================================================

   public Observable<SearchData> getSearchData(String type, int count,int page){
        if(apiService==null) {
            return null;
        }
        return apiService.getSearchData(type,count,page);
    }

    public Observable<SearchData> getHomeData(String type,int count,int page){
        if(apiService==null) {
            return null;
        }
        return apiService.getHomeData(type,count,page);
    }

    public Observable<SearchData> getHistoryData(int count,int page){
        if(apiService==null) {
            return null;
        }
        return apiService.getHistoryData(count,page);
    }

    public Observable<SearchData> getHistoryDataByDate(String date){
        if(apiService==null) {
            return null;
        }
        return apiService.getHistoryDataByDate(date);
    }

    public Observable<SearchData> add2Gank(String url,String desc,String who,String type){
        if(apiService==null) {
            return null;
        }
        return apiService.add2Gank(url,desc,who,type);
    }

    public Observable<SearchData> getRandomData(String type,int count){
        if(apiService==null) {
            return null;
        }
        return apiService.getRandomData(type,count);
    }
}
