package com.winsland.mobilemagazine;

import com.winsland.findapp.view.main.MainActivity;
import com.winsland.framework.BaseTestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

/**
 * User: Gebing
 * Date: 13-6-8 ÉÏÎç1:30
 */
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest extends BaseTestCase {
	@Before
	public void setUp() throws Exception {
		super.setup();
	}

	@Test
	public void testInitView() throws Exception {
		enterTestCase();
		// create and start MainActivity
		ActivityController control = Robolectric.buildActivity(MainActivity.class).create().start();
		waitTestComplete(10000);
		// get member name
//		SherlockFragmentActivity activity = (SherlockFragmentActivity) control.get();
//		TextView tv = (TextView) activity.findViewById(R.id.splash_memberName);
//		Assert.assertTrue(tv.getText().length() > 0);

		exitTestCase();
	}
}
