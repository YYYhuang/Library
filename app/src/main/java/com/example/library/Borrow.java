package com.example.library;

import android.content.Context;
import android.content.SharedPreferences;

public class Borrow {
    private String code;
    private String msg;
    public String show(){
        return code;
//        SharedPreferences.Editor editor = context.getSharedPreferences("borrow", Context.MODE_PRIVATE).edit();
//        editor.putString("code", code);
//        editor.putString("msg",msg);
//        editor.apply();
    }
}
