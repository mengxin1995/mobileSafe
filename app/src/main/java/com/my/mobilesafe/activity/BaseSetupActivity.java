package com.my.mobilesafe.activity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class BaseSetupActivity extends myActivity {

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //监听手势移动
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //监听手势移动
                if (e1.getX() - e2.getX() > 0) {
                    showNextPage();
                } else if(e1.getX() - e2.getX() < 0){
                    showPrePage();
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //通过手势处理类
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    protected abstract void showNextPage();

    protected abstract void showPrePage();

    public void nextPage(View view) {
        showNextPage();
    }

    public void prePage(View view) {
        showPrePage();
    }
}
