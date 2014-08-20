package com.cbrc.riskmgr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.androidquery.util.AQUtility;
import com.cbrc.riskmgr.utils.TagUtil;
import com.cbrc.riskmgr.view.SplashActivity;
import com.rz.framework.util.SharedPreferencesUtil;

public class InitActivity extends Activity {
	static private final String TAG = TagUtil.getTag(InitActivity.class);

	private static boolean isExit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!isExit) {
			boolean b = SharedPreferencesUtil.getBoolean(
					GlobalConstants.SHARED_PREF_ISSPLASH,
					GlobalConstants.SHARED_PREF_ISSPLASH, false);
			Intent intent = new Intent(this, SplashActivity.class);
			startActivity(intent);
		} else {
			isExit = false;
			finish();
			System.exit(0);
		}
	}

	// ÍË³ö³ÌÐò
	public static void exit(Context context) {
		if (AQUtility.isDebug())
			Log.i(TAG, "Exit application.");
		isExit = true;
		Intent intent = new Intent(context, InitActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}
	
}
