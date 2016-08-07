package com.my.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;

import com.my.mobilesafe.R;
import com.my.mobilesafe.utils.ConstantValue;
import com.my.mobilesafe.utils.SpUtils;

public class SetupOverActivity extends myActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean setup_over = SpUtils.getBoolean(this, ConstantValue.SETUPOVER, false);
        if(setup_over) {
            setContentView(R.layout.activity_setup_over);
        }else{
            Intent intent = new Intent(this, Setup1Activity.class);
            startActivity(intent);

            finish();
        }
    }
}
