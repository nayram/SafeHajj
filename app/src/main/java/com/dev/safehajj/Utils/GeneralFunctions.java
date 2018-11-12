package com.dev.safehajj.Utils;

import android.content.SharedPreferences;
import android.net.Uri;

import com.dev.safehajj.Pojo.UserResponse;
import com.google.gson.Gson;

public class GeneralFunctions {

    public static void saveUserToken(String token){
        SharedPreferences sharedPreferences= App.sp;
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(Constants.UserToken,token);
        editor.apply();
    }

    public static String getUserToken(){
        SharedPreferences sharedPreferences=App.sp;

        return sharedPreferences.getString(Constants.UserToken,"");
    }

    public static void setLoginFlag(boolean loginFlag){
        SharedPreferences sharedPreferences= App.sp;
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean(Constants.UserLogin,loginFlag);
        editor.apply();
    }

    public static boolean getLoginFlag(){
        SharedPreferences sharedPreferences=App.sp;

        return sharedPreferences.getBoolean(Constants.UserLogin,false);
    }

    public static void setUser(String user){
        SharedPreferences sharedPreferences= App.sp;
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(Constants.UserObj,user);
        editor.apply();
    }

    public static UserResponse getUser(){
        SharedPreferences sharedPreferences=App.sp;

        String user= sharedPreferences.getString(Constants.UserObj,"");

        if (!user.isEmpty()){
            return new Gson().fromJson(user,UserResponse.class);
        }else return null;

    }

    public static void setProfileImage(Uri uri){
        SharedPreferences sharedPreferences=App.sp;
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(Constants.UserImg,uri.getPath());
        editor.apply();
    }

    public static String getProfileImage(){
        SharedPreferences sharedPreferences=App.sp;

        return sharedPreferences.getString(Constants.UserImg,null);
    }

    public static void loogout() {
        SharedPreferences sharedPreferences=App.sp;
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
