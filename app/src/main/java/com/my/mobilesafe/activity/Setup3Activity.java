package com.my.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.mobilesafe.R;

public class Setup3Activity extends myActivity {

    private EditText et_phone_number;
    private Button bt_select_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);

        intiUI();
    }

    private void intiUI() {
        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
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

    public void nextPage(View view) {
        Intent intent = new Intent(this, Setup4Activity.class);
        startActivity(intent);

        finish();
    }

    public void prePage(View view) {
        Intent intent = new Intent(this, Setup2Activity.class);
        startActivity(intent);

        finish();
    }
}
