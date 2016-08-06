package com.my.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.my.mobilesafe.R;

/**
 * Created by mengxin on 16-8-6.
 */
public class SettingItemView extends RelativeLayout {

    public static final String NAMESPACE = "http://schemas.android.com/apk/res/com.my.mobilesafe";
    private CheckBox cb_box;
    private TextView tv_des;
    private String mDestitle;
    private String mDesoff;
    private String mDeson;

    public SettingItemView(Context context) {
        this(context, null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //xml--->view
//        View view = View.inflate(context, R.layout.setting_item_view, null);
//        this.addView(view);
        View.inflate(context, R.layout.setting_item_view, this);
        TextView tv_title = (TextView)findViewById(R.id.tv_title);
        tv_des = (TextView) findViewById(R.id.tv_des);
        cb_box = (CheckBox) findViewById(R.id.cb_box);
        //获取自定义原生属性的操作
        initAttrs(attrs);
        tv_title.setText(mDestitle);
    }

    /**
     * @param attrs
     * 返回属性集合中自定义属性属性值
     */
    private void initAttrs(AttributeSet attrs) {
        mDestitle = attrs.getAttributeValue(NAMESPACE, "destitle");
        mDesoff = attrs.getAttributeValue(NAMESPACE, "desoff");
        mDeson = attrs.getAttributeValue(NAMESPACE, "deson");

    }

    /**
     * 判断
     * @return 当前条目是否选中
     */
    public boolean isCheck(){
        return cb_box.isChecked();
    }

    /**
     * @param isCheck 是否开启
     */
    public void setCheck(boolean isCheck){
        cb_box.setChecked(isCheck);
        if(isCheck){
            tv_des.setText(mDeson);
        }else{
            tv_des.setText(mDesoff);
        }
    }
}
