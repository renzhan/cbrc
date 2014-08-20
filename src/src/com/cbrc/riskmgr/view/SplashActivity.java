package com.cbrc.riskmgr.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.androidquery.util.AQUtility;
import com.cbrc.riskmgr.R;
import com.cbrc.riskmgr.utils.TagUtil;

public class SplashActivity extends Activity{
	static private final String TAG = TagUtil.getTag(SplashActivity.class);
	private static final int SPLASH_DISPLAY_DELAY = 3000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		AQUtility.postDelayed(new Runnable(){

			@Override
			public void run() {
				Intent intent = new Intent(SplashActivity.this, MainActivity.class);
				SplashActivity.this.startActivity(intent);
				SplashActivity.this.finish();
			}
		}, SPLASH_DISPLAY_DELAY);
	}
}
