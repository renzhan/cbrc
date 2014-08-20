package com.cbrc.riskmgr.utils;

import java.io.File;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.cbrc.riskmgr.GlobalConstants;
import com.cbrc.riskmgr.InitActivity;
import com.cbrc.riskmgr.bean.update.UpdateInfo;
import com.rz.framework.util.CommonUtil;
import com.rz.framework.util.PackageUtil;
import com.rz.framework.util.SharedPreferencesUtil;
import com.rz.framework.util.SystemInfoUtil;

/**
 * User: Gebing Date: 13-6-27 下午5:49
 */
public class UpdateUtil {
	static private final String TAG = TagUtil.getTag(UpdateUtil.class);

	static public void processUpdate(final Activity context,
			final UpdateInfo updateInfo) {
		// 如果不需要提示更新或无下载信息，则直接退出
		if (updateInfo.display == 2)
			return;
		final String downloadUrl = TextUtils.isEmpty(updateInfo.url_remote) ? updateInfo.url_remote
				: updateInfo.url_local;
		if (TextUtils.isEmpty(downloadUrl))
			return;
		// 如果是强制更新，则直接开始下载
		// if (updateInfo.display == 3) {
		// UpdateUtil.startUpdate(context, downloadUrl, true);
		// return;
		// }
		// 如果是非强制更新，则比较上次忽略的版本。如果小于等于忽略版本，则直接退出
		String ignoreVersion = SharedPreferencesUtil.getString(
				GlobalConstants.SHARED_PREF_UPDATE,
				GlobalConstants.SHARED_PREF_UPDATE_IGNORE, "");
		if (updateInfo.version != null
				&& updateInfo.version.compareTo(ignoreVersion) <= 0)
			return;
		// 提示用户是否更新
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		if (updateInfo.display == 3)
			builder.setTitle("重要升级")
					.setMessage("客户端进行了重大更新，导致目前版本暂不能使用，请升级至最新版本继续体验")
					.setCancelable(false);
		else
			builder.setTitle("新版本客户端发布啦").setMessage(updateInfo.message)
					.setCancelable(true);
		builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				UpdateUtil.startUpdate(context, downloadUrl,
						updateInfo.display == 3);
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (updateInfo.display == 3)
					InitActivity.exit(context);
			}
		});
		/*
		 * builder.setNeutralButton("不再提醒", new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) {
		 * SharedPreferencesUtil.setString( GlobalConstants.SHARED_PREF_UPDATE,
		 * GlobalConstants.SHARED_PREF_UPDATE_IGNORE, updateInfo.version); } });
		 */
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	@TargetApi(9)
	static public void startUpdate(final Activity context,
			final String downloadUrl, final boolean forceUpdate) {
		if (AQUtility.isDebug())
			Log.i(TAG, "startUpdate(downloadUrl=" + downloadUrl
					+ ",forceUpdate=" + forceUpdate + ")");
		// start download
		final File downloadFile = new File(
				SystemInfoUtil.isExternalStorageAvailable() ? context.getExternalCacheDir()
						: context.getFilesDir(), "update.apk");
		// setup progress dialog
		final ProgressDialog pBar = new ProgressDialog(context);
		pBar.setMessage("正在下载更新包，请稍候...");
		pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pBar.setCancelable(false);
		pBar.setCanceledOnTouchOutside(false);
		// setup callback
		final AjaxCallback<File> callback = new AjaxCallback<File>() {
			@TargetApi(9)
			@Override
			public void callback(String url, File file, AjaxStatus status) {
				if (AQUtility.isDebug())
					Log.i(TAG,
							"startUpdate() return from " + status.getSource()
									+ ": file=" + file + ",abort=" + getAbort()
									+ ")");
				if (getAbort()) {
					// do nothing
				} else if (file != null) {
					file.setReadable(true, false);
					file.setExecutable(true, false);
					// 注意：这里有先后顺序问题
					InitActivity.exit(context);
					PackageUtil.installPackage(file);
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							context);
					builder.setTitle("下载更新失败").setMessage("是否重试下载？");
					builder.setPositiveButton("重试",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									UpdateUtil.startUpdate(context,
											downloadUrl, forceUpdate);
								}
							});
					builder.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									if (forceUpdate)
										InitActivity.exit(context);
								}
							});
					builder.setCancelable(false);
					AlertDialog dialog = builder.create();
					dialog.setCanceledOnTouchOutside(false);
					dialog.show();
				}
			}

			@Override
			public void progress(String url, long current, long max) {
				StatFs stat = new StatFs(downloadFile.getAbsolutePath());
				if ((long) stat.getAvailableBlocks()
						* (long) stat.getBlockSize() < max) {
					this.abort();
					CommonUtil.toast(Toast.LENGTH_LONG, "更新取消，没有足够的空间下载更新包！");
				} else {
					max = Math.max(current, max);
					while (max >= Integer.MAX_VALUE) {
						max /= 10;
						current /= 10;
					}
					pBar.setMax((int) max);
					pBar.setProgress((int) current);
				}
			}
		};
		// show prograss
		pBar.setButton(DialogInterface.BUTTON_POSITIVE, "取消",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						callback.abort();
						if (forceUpdate)
							InitActivity.exit(context);
					}
				});
		pBar.show();
		// 保证每次都是重新下载
		if (downloadFile.exists())
			downloadFile.delete();
		new AQuery(context).progress(pBar).download(downloadUrl, downloadFile,
				callback);
	}
}
