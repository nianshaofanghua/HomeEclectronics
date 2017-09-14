package com.yidan.homeelectronics.http;

import android.util.Log;

import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;
import com.yidan.homeelectronics.MyApplication;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by ${syj} on 2017/9/12.
 */

public class MoOkHttp {
    MyOkHttp mMoOkHttp = MyApplication.getInstance().getMyOkHttp();
String mJson;
    public String doPost(String url, Map<String, String> params) {

        mMoOkHttp
                .post().
                url(url).
                params(params).
                tag(this).
                enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {

                        mJson = response;
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
mJson = error_msg;
                    }
                });


        return mJson;

    }
}
