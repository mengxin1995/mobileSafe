package com.my.mobilesafe.activity;

import android.os.Bundle;
import android.widget.TextView;

public class SDCacheClearActivity extends myActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(getApplicationContext());
		textView.setText("SDCacheClearActivity");
		setContentView(textView);
	}
}
