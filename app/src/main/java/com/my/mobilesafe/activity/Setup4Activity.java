package com.my.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.my.mobilesafe.R;
import com.my.mobilesafe.utils.ConstantValue;
import com.my.mobilesafe.utils.SpUtils;

public class Setup4Activity extends myActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);
    }

    public void nextPage(View view) {
        Intent intent = new Intent(this, SetupOverActivity.class);
        startActivity(intent);
        SpUtils.putBoolean(this, ConstantValue.SETUPOVER, true);
        finish();
    }

    public void prePage(View view) {
        Intent intent = new Intent(this, Setup3Activity.class);
        startActivity(intent);

        finish();
    }
}
