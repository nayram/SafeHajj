package com.dev.safehajj.Utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {

    public static SharedPreferences sp;
    public static SafeHajjNetworkInterface hajjNetworkInterface;

    @Override
    public void onCreate() {
        super.onCreate();
        sp= getSharedPreferences(Constants.AppPackage, Context.MODE_PRIVATE);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SafeHajjNetworkInterface.domain)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        hajjNetworkInterface=retrofit.create(SafeHajjNetworkInterface.class);

    }
}
