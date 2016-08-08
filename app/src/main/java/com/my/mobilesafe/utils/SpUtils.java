package com.my.mobilesafe.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mengxin on 16-8-6.
 */
public class SpUtils {

    private static SharedPreferences sp;

    public static void putBoolean(Context context, String key, boolean value){
        if(sp == null)
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue){
        if(sp == null)
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void putString(Context context, String key, String value){
        if(sp == null)
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defValue){
        if(sp == null)
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void remove(Context context, String key) {
        if(sp == null)
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }
}
