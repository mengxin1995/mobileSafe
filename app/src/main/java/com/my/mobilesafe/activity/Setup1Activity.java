package com.my.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.my.mobilesafe.R;

public class Setup1Activity extends myActivity {

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup1);

        //监听手势移动
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //监听手势移动
                if (e1.getX() - e2.getX() > 0) {
                    Intent intent = new Intent(getApplicationContext(), Setup2Activity.class);
                    startActivity(intent);

                    finish();
                    overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);
                } else if(e1.getX() - e2.getX() < 0){

                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    public void nextPage(View view) {
        Intent intent = new Intent(this, Setup2Activity.class);
        startActivity(intent);

        finish();
        overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //通过手势处理类
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
