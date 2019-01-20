package com.example.yuekao3.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttps {

    private final OkHttpClient okHttpClient;

    public OkHttps() {
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .callTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();


    }
    public static  OkHttps getInstance(){
       return OkHolder.okli;
    }

    static class OkHolder{
        private static final  OkHttps okli=new OkHttps();
    }

public String get(String url) throws IOException{
    Request request=new Request.Builder().url(url).build();
        return okHttpClient.newCall(request).execute().body().string();
}
public String post(String url,String key,String value) throws IOException{
    RequestBody body=new FormBody.Builder().add(key,value).build();
    Request request=new Request.Builder().url(url).post(body).build();

        return okHttpClient.newCall(request).execute().body().string();
}


}
