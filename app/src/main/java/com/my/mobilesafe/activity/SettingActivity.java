package com.my.mobilesafe.activity;

import android.os.Bundle;
import android.view.View;

import com.my.mobilesafe.R;
import com.my.mobilesafe.utils.ConstantValue;
import com.my.mobilesafe.utils.SpUtils;
import com.my.mobilesafe.view.SettingItemView;

public class SettingActivity extends myActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initUpdate();
    }

    private void initUpdate() {
        final SettingItemView siv_updatae = (SettingItemView) findViewById(R.id.siv_update);
        boolean open_update = SpUtils.getBoolean(getApplicationContext(), ConstantValue.OPEN_UPDATE, false);
        siv_updatae.setCheck(open_update);
        siv_updatae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean ischeck = siv_updatae.isCheck();
                siv_updatae.setCheck(!ischeck);
                SpUtils.putBoolean(getApplicationContext(), ConstantValue.OPEN_UPDATE, !ischeck);
            }
        });
    }
}
