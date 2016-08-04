package com.my.mobilesafe.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class myActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(getClass().getSimpleName());
    }
}
