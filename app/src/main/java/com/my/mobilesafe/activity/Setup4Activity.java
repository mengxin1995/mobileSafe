package com.my.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.my.mobilesafe.R;
import com.my.mobilesafe.utils.ConstantValue;
import com.my.mobilesafe.utils.SpUtils;
import com.my.mobilesafe.utils.ToastUtil;

public class Setup4Activity extends myActivity {

    private CheckBox cb_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);
        initUI();
    }

    private void initUI() {
        cb_box = (CheckBox) findViewById(R.id.cb_box);
        boolean open_security = SpUtils.getBoolean(this, ConstantValue.OPEN_SECURITY, false);
        cb_box.setChecked(open_security);
        if(open_security){
            cb_box.setText("安全设置已开启");
        }else{
            cb_box.setText("安全设置已关闭");
        }
        cb_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SpUtils.putBoolean(getApplicationContext(), ConstantValue.OPEN_SECURITY, b);
                if(b){
                    cb_box.setText("安全设置已开启");
                }else{
                    cb_box.setText("安全设置已关闭");
                }
            }
        });

    }

    public void nextPage(View view) {
        boolean open_security = SpUtils.getBoolean(this, ConstantValue.OPEN_SECURITY, false);
        if(open_security) {
            Intent intent = new Intent(this, SetupOverActivity.class);
            startActivity(intent);
            SpUtils.putBoolean(this, ConstantValue.SETUPOVER, true);
            finish();
        }else{
            ToastUtil.show(this, "请开启防盗设置");
        }
    }

    public void prePage(View view) {
        Intent intent = new Intent(this, Setup3Activity.class);
        startActivity(intent);

        finish();
    }
}
