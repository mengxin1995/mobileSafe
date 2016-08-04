package com.my.mobilesafe.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by mengxin on 16-8-3.
 */
public class ToastUtil {
    public static void show(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
