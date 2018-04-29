package com.dev.safehajj.Utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import retrofit2.Retrofit;


public class App extends Application {

    public static SharedPreferences sp;


    @Override
    public void onCreate() {
        super.onCreate();
        sp= getSharedPreferences(Constants.AppPackage, Context.MODE_PRIVATE);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SafeHajjNetworkInterface.domain)
                .build();

    }
}
