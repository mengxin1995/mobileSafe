package com.my.mobilesafe.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;

import com.my.mobilesafe.R;
import com.my.mobilesafe.utils.ConstantValue;
import com.my.mobilesafe.utils.SpUtils;
import com.my.mobilesafe.utils.ToastUtil;
import com.my.mobilesafe.view.SettingItemView;

public class Setup2Activity extends myActivity {

    private SettingItemView siv_sim_bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup2);

        initUI();
    }

    private void initUI() {
        siv_sim_bound = (SettingItemView) findViewById(R.id.siv_sim_bound);
        String sim_number = SpUtils.getString(this, ConstantValue.SIM_NUMBER, "");
        if(TextUtils.isEmpty(sim_number)){
            siv_sim_bound.setCheck(false);
        }else{
            siv_sim_bound.setCheck(true);
        }
        siv_sim_bound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCheck = siv_sim_bound.isCheck();
                siv_sim_bound.setCheck(!isCheck);
                if(!isCheck){
                    TelephonyManager manager = (TelephonyManager)
                            getSystemService(Context.TELEPHONY_SERVICE);
                    String simSerialNumber = manager.getSimSerialNumber();
                    SpUtils.putString(getApplicationContext(), ConstantValue.SIM_NUMBER, simSerialNumber);
                }else{
                    //删除sp节点
                    SpUtils.remove(getApplicationContext(),ConstantValue.SIM_NUMBER);
                }
            }
        });
    }

    public void nextPage(View view) {
        String simNumber = SpUtils.getString(getApplicationContext(), ConstantValue.SIM_NUMBER, "");
        if(!TextUtils.isEmpty(simNumber)) {
            Intent intent = new Intent(this, Setup3Activity.class);
            startActivity(intent);

            finish();
        }else{
            ToastUtil.show(getApplicationContext(), "请绑定sim卡");
        }
    }

    public void prePage(View view) {
        Intent intent = new Intent(this, Setup1Activity.class);
        startActivity(intent);

        finish();
    }
}
