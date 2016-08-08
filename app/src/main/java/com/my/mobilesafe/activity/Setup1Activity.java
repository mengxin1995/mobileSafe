package com.my.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.my.mobilesafe.R;

public class Setup1Activity extends myActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup1);
    }

    public void nextPage(View view) {
        Intent intent = new Intent(this, Setup2Activity.class);
        startActivity(intent);

        finish();
        overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);
    }
}
