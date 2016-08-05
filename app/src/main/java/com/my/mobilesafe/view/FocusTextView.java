package com.my.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by mengxin on 16-8-5.
 */
public class FocusTextView extends TextView{
    //通过java代码创建控件
    public FocusTextView(Context context) {
        super(context);
    }
    //由系统调用(带属性+上下文环境)
    public FocusTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //由系统调用(带属性+上下文环境+样式)
    public FocusTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //重写获取焦点的方法

    @Override
    public boolean isFocused() {
        return true;
    }
}
