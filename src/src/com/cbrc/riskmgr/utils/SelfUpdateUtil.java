package com.cbrc.riskmgr.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.cbrc.riskmgr.bean.update.ClientUpdateInfo;
import com.cbrc.riskmgr.bean.update.UpdateInfo;
import com.cbrc.riskmgr.event.NewVersionEvent;
import com.cbrc.riskmgr.protocol.ServerApi;
import com.google.gson.Gson;
import com.rz.framework.protocol.BaseProtocol;
import com.rz.framework.util.CommonUtil;
import com.rz.framework.util.PackageUtil;

import de.greenrobot.event.EventBus;

public class SelfUpdateUtil {
	static private final String TAG = TagUtil.getTag(SelfUpdateUtil.class);

	public static void checkClientUpdate(final AQuery aq,
			final boolean isShowToast, final boolean isCheckAds) {
		BaseProtocol<ClientUpdateInfo> request = ServerApi
				.clientUpdate();
		request.callback(new AjaxCallback<ClientUpdateInfo>() {
			@Override
			public void callback(String url, ClientUpdateInfo result,
					AjaxStatus status) {
				if (AQUtility.isDebug())
					Log.i(TAG,
							"checkClientUpdate() return from "
									+ status.getSource() + ".\n"
									+ new Gson().toJson(result));
				if (result != null) {
					if (!isCheckAds) {
						// 将ClientUpdateInfo里的信息转移到UpdateInfo中
						if (result.version == null
								|| result.download_url == null) {
							CommonUtil.toast(Toast.LENGTH_SHORT, "获取信息有误");
						} else if (result.version_code > PackageUtil
								.getAppVersionCode()
								&& versionCompare(result.version,
										PackageUtil.getAppVersionName(), 3) > 0
						/*
						 * !result.version.equals(PackageUtil.getAppVersionName()
						 * )
						 */) {
							UpdateInfo updateInfo = new UpdateInfo();
							updateInfo.url_local = updateInfo.url_remote = result.download_url;
							updateInfo.version = result.version;
							if (result.lowest_upgrade_app_version_code > PackageUtil
									.getAppVersionCode())
								updateInfo.display = 3;
							updateInfo.message = result.recent_change;
							updateInfo.version_code = "" + result.version_code;
							updateInfo.remark = result.released_on;
							UpdateUtil.processUpdate(
									(Activity) aq.getContext(), updateInfo);
							// 通知ui有新版本
							if (!isShowToast) {
								EventBus.getDefault()
										.post(new NewVersionEvent());
							}

						} else {
							if (isShowToast) {
								CommonUtil.toast(Toast.LENGTH_SHORT, "已经是最新版本");
							}
						}
					}
				} else {
					if (!isCheckAds && isShowToast) {
						CommonUtil.toast(Toast.LENGTH_SHORT, "无新版本");
					}
				}
			}
		}).execute(aq, -1);
	}

	public static void checkClientUpdate(final AQuery aq,
			final boolean isShowToast) {
		checkClientUpdate(aq, isShowToast, false);
	}

	public static boolean isNewClient() {
		BaseProtocol<ClientUpdateInfo> request = ServerApi
				.clientUpdate();
		ClientUpdateInfo result = request.getCacheResult(0);
		if (result != null) {
			if (result.version == null || result.download_url == null) {
				return false;
			} else if (result.version_code > PackageUtil.getAppVersionCode()
					&& versionCompare(result.version,
							PackageUtil.getAppVersionName(), 3) > 0
			/* !result.version.equals(PackageUtil.getAppVersionName()) */) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public static int versionCompare(String str1, String str2, int cmplen) {
		String[] s1 = str1.substring(1).split("\\.");
		String[] s2 = str2.substring(1).split("\\.");

		int n1 = s1.length;
		int n2 = s2.length;
		int ret = 0;
		if (cmplen <= 0)
			cmplen = Math.min(n1, n2);
		for (int i = 0; i < cmplen; i++) {
			try {
				if (Integer.parseInt(s1[i]) - Integer.parseInt(s2[i]) > 0) {
					ret = 1;
					break;
				}
				;
				if (Integer.parseInt(s1[i]) - Integer.parseInt(s2[i]) < 0) {
					ret = -1;
					break;
				}
				;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				break;
			}
		}

		if (ret == 0 && cmplen <= 0) {
			if (n1 > n2) {
				ret = 1;
			}
			if (n1 < n2) {
				ret = -1;
			}
		}
		if (AQUtility.isDebug())
			Log.d(TAG, "versionCompare(" + str1 + ", " + str2 + ") ret " + ret);
		return ret;
	}
}
