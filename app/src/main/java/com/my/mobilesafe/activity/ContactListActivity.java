package com.my.mobilesafe.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.my.mobilesafe.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private ListView lv_contact;
    private List<HashMap<String, String>> contactList =  new ArrayList<HashMap<String, String>>();
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            lv_contact.setAdapter(new MyAdapter());
        }
    };

    private class MyAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return contactList.size();
        }

        @Override
        public HashMap<String, String> getItem(int i) {
            return contactList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = View.inflate(getApplicationContext(), R.layout.listview_contact_item, null);
            TextView tv_name = (TextView) view1.findViewById(R.id.tv_name);
            TextView tv_phone = (TextView) view1.findViewById(R.id.tv_phone);

            tv_name.setText(getItem(i).get("name"));
            tv_phone.setText(getItem(i).get("phone"));
            return view1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        initUI();
        initData();
    }

    private void initData() {
        new Thread(){
            @Override
            public void run() {
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(
                        Uri.parse("content://com.android.contacts/raw_contacts"),
                        new String[]{"contact_id"},
                        null, null, null);
                contactList.clear();
                while(cursor.moveToNext()){
                    String id = cursor.getString(0);
                    Cursor indexCursor = contentResolver.query(
                            Uri.parse("content://com.android.contacts/data"),
                            new String[]{"data1", "mimetype"},
                            "raw_contact_id = ?", new String[]{id}, null
                    );
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    while(indexCursor.moveToNext()){
                        String data = indexCursor.getString(0);
                        String type = indexCursor.getString(1);
                        if(type.equals("vnd.android.cursor.item/phone_v2")){
                            if(!TextUtils.isEmpty(data)) {
                                hashMap.put("phone", "data");
                            }
                        }else if(type.equals("vnd.android.cursor.item/name")){
                            if(!TextUtils.isEmpty(data)) {
                                hashMap.put("name", data);
                            }
                        }
                    }
                    indexCursor.close();
                    contactList.add(hashMap);
                }
                cursor.close();
                //发送一个空的消息
                mHandler.sendEmptyMessage(0);
            }
        }.start();
    }

    private void initUI() {
        lv_contact = (ListView) findViewById(R.id.lv_contact);
    }
}
