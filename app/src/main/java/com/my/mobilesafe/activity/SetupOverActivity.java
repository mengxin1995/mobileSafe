package com.my.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.my.mobilesafe.R;
import com.my.mobilesafe.utils.ConstantValue;
import com.my.mobilesafe.utils.SpUtils;

public class SetupOverActivity extends myActivity {

    private TextView tv_phone;
    private TextView tv_reset_setup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean setup_over = SpUtils.getBoolean(this, ConstantValue.SETUP_OVER, false);
        if(setup_over) {
            setContentView(R.layout.activity_setup_over);

            initUI();
        }else{
            Intent intent = new Intent(this, Setup1Activity.class);
            startActivity(intent);

            finish();
        }
    }

    private void initUI() {
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        String phone = SpUtils.getString(this, ConstantValue.CONTACT_PHONE, "");
        tv_phone.setText(phone);

        tv_reset_setup = (TextView) findViewById(R.id.tv_reset_setup);
        tv_reset_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Setup1Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
