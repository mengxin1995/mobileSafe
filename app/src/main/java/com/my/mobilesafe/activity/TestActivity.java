package com.my.mobilesafe.activity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by mengxin on 16-8-6.
 */
public class TestActivity extends myActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("TestActivity");
        setContentView(textView);
    }
}
