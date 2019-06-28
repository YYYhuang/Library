package com.example.library;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
        private String code;
        private String msg;
        private String userId;
        private String userName;
        private String password;
        private String date;
        private String permission;


    public void show(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("data", Context.MODE_PRIVATE).edit();
        editor.putString("code", code);
        editor.putString("msg",msg);
        editor.apply();
    }
    public void getData(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences("data", Context.MODE_PRIVATE).edit();
        editor.putString("userId", userId);
        editor.putString("userName",userName);
        editor.putString("password",password);
        editor.putString("date",date);
        editor.putString("permission",permission);
        editor.apply();
    }
}