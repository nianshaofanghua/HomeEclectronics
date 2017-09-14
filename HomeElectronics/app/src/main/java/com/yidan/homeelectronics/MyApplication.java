package com.yidan.homeelectronics;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.yidan.homeelectronics.http.DownloadMgr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by tsy on 2016/12/6.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;
    private MyOkHttp mMyOkHttp;
    private DownloadMgr mDownloadMgr;
    public static Context app;
    private List<Activity> mList;
    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplicationContext();
        mInstance = this;
mList = new ArrayList<>();
        //持久化存储cookie
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));

        //log拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //自定义OkHttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)       //设置开启cookie
                .addInterceptor(logging)            //设置开启log
                .build();
        mMyOkHttp = new MyOkHttp(okHttpClient);

        //默认
//        mMyOkHttp = new MyOkHttp();

        mDownloadMgr = (DownloadMgr) new DownloadMgr.Builder()
                .myOkHttp(mMyOkHttp)
                .maxDownloadIngNum(5)       //设置最大同时下载数量（不设置默认5）
                .saveProgressBytes(50 * 1204)   //设置每50kb触发一次saveProgress保存进度 （不能在onProgress每次都保存 过于频繁） 不设置默认50kb
                .build();

        mDownloadMgr.resumeTasks();     //恢复本地所有未完成的任务
    }


    public static Context getAppContext() {
        return app;
    }
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public MyOkHttp getMyOkHttp() {
        return mMyOkHttp;
    }

    public DownloadMgr getDownloadMgr() {
        return mDownloadMgr;
    }
    public void setActivityList(Activity activity){
        mList.add(activity);
    }
    public void remaveActivity(Activity activity){
       if( mList.contains(activity)){
           mList.remove(activity);
       };
    }
    public void goOut(){
        for (Activity a : mList) {
            a.finish();
        }
    }
}
