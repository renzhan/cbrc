package com.cbrc.riskmgr.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.util.AQUtility;
import com.cbrc.riskmgr.InitActivity;
import com.cbrc.riskmgr.R;
import com.cbrc.riskmgr.event.NewVersionEvent;
import com.cbrc.riskmgr.utils.SelfUpdateUtil;
import com.cbrc.riskmgr.utils.TagUtil;

import de.greenrobot.event.EventBus;

public class MainActivity  extends Activity{
	static private final String TAG = TagUtil.getTag(MainActivity.class);
	
	private AQuery aq;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		aq = new AQuery(this);

		EventBus.getDefault().register(this);

		initDisplay(savedInstanceState);
		initData();
	}
	
	@Override
	protected void onDestroy() {
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	private void initDisplay(Bundle savedInstanceState) {
		
	}
	
	private void initData() {
		// 异步UI线程检查更新
		AQUtility.post(new Runnable() {
			@Override
			public void run() {
				SelfUpdateUtil.checkClientUpdate(aq, false);
			}
		});
	}
	
	public void onEventMainThread(NewVersionEvent event) {
		Log.d(TAG, "NewVersionEvent");

	}

	private long backPressTime = 0;

	@Override
	public void onBackPressed() {
		if (System.currentTimeMillis() - backPressTime > 2000) {
			Toast.makeText(this, "再按一次退出程序!", Toast.LENGTH_SHORT).show();
			backPressTime = System.currentTimeMillis();
		} else {
			InitActivity.exit(this);
		}
	}

}