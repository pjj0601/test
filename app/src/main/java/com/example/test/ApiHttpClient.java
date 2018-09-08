package com.example.test;

/**
 * Created by 潘俊杰 on 2018/9/5.
 */

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ApiHttpClient {
    private static volatile ApiHttpClient client = null;
    private AsyncHttpClient asyncHttpClient = null;

    private ApiHttpClient(){
        asyncHttpClient = new AsyncHttpClient();
    }

    public static ApiHttpClient getInstance(){
        if(client == null){
            synchronized (ApiHttpClient.class){
                if(client == null){
                    client = new ApiHttpClient();
                }
            }
        }
        return client;
    }

    public void get(String url, AsyncHttpResponseHandler handler){
        asyncHttpClient.get(url, handler);
    }
    public void post(String url, RequestParams params, AsyncHttpResponseHandler handler){
        asyncHttpClient.post(url,params, handler);
    }
}

