package com.my.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.mobilesafe.R;
import com.my.mobilesafe.utils.ConstantValue;
import com.my.mobilesafe.utils.SpUtils;
import com.my.mobilesafe.utils.ToastUtil;

public class Setup3Activity extends BaseSetupActivity {

    private EditText et_phone_number;
    private Button bt_select_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);

        intiUI();
    }

    @Override
    protected void showNextPage() {
        String phone = et_phone_number.getText().toString();
//        String contact_phone = SpUtils.getString(this, ConstantValue.CONTACT_PHONE, "");
        if(!TextUtils.isEmpty(phone)) {
            Intent intent = new Intent(this, Setup4Activity.class);
            startActivity(intent);

            finish();
            SpUtils.putString(this, ConstantValue.CONTACT_PHONE, phone);
        }else{
            ToastUtil.show(this, "请输入电话号码");
        }
    }

    @Override
    protected void showPrePage() {
        Intent intent = new Intent(this, Setup2Activity.class);
        startActivity(intent);

        finish();
    }

    private void intiUI() {
        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
        String contact_phone = SpUtils.getString(this, ConstantValue.CONTACT_PHONE, "");
        et_phone_number.setText(contact_phone);

        bt_select_number = (Button) findViewById(R.id.bt_select_number);
        bt_select_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        ContactListActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null) {
            String phone = data.getStringExtra("phone");
            phone = phone.replace("-", "").replace(" ", "").trim();

            et_phone_number.setText(phone);

            SpUtils.putString(this, ConstantValue.CONTACT_PHONE, phone);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
