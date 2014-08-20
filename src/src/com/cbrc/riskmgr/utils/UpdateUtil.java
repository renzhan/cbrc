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
 * User: Gebing Date: 13-6-27 ����5:49
 */
public class UpdateUtil {
	static private final String TAG = TagUtil.getTag(UpdateUtil.class);

	static public void processUpdate(final Activity context,
			final UpdateInfo updateInfo) {
		// �������Ҫ��ʾ���»���������Ϣ����ֱ���˳�
		if (updateInfo.display == 2)
			return;
		final String downloadUrl = TextUtils.isEmpty(updateInfo.url_remote) ? updateInfo.url_remote
				: updateInfo.url_local;
		if (TextUtils.isEmpty(downloadUrl))
			return;
		// �����ǿ�Ƹ��£���ֱ�ӿ�ʼ����
		// if (updateInfo.display == 3) {
		// UpdateUtil.startUpdate(context, downloadUrl, true);
		// return;
		// }
		// ����Ƿ�ǿ�Ƹ��£���Ƚ��ϴκ��Եİ汾�����С�ڵ��ں��԰汾����ֱ���˳�
		String ignoreVersion = SharedPreferencesUtil.getString(
				GlobalConstants.SHARED_PREF_UPDATE,
				GlobalConstants.SHARED_PREF_UPDATE_IGNORE, "");
		if (updateInfo.version != null
				&& updateInfo.version.compareTo(ignoreVersion) <= 0)
			return;
		// ��ʾ�û��Ƿ����
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		if (updateInfo.display == 3)
			builder.setTitle("��Ҫ����")
					.setMessage("�ͻ��˽������ش���£�����Ŀǰ�汾�ݲ���ʹ�ã������������°汾��������")
					.setCancelable(false);
		else
			builder.setTitle("�°汾�ͻ��˷�����").setMessage(updateInfo.message)
					.setCancelable(true);
		builder.setPositiveButton("����", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				UpdateUtil.startUpdate(context, downloadUrl,
						updateInfo.display == 3);
			}
		});
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (updateInfo.display == 3)
					InitActivity.exit(context);
			}
		});
		/*
		 * builder.setNeutralButton("��������", new
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
		pBar.setMessage("�������ظ��°������Ժ�...");
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
					// ע�⣺�������Ⱥ�˳������
					InitActivity.exit(context);
					PackageUtil.installPackage(file);
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							context);
					builder.setTitle("���ظ���ʧ��").setMessage("�Ƿ��������أ�");
					builder.setPositiveButton("����",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									UpdateUtil.startUpdate(context,
											downloadUrl, forceUpdate);
								}
							});
					builder.setNegativeButton("ȡ��",
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
					CommonUtil.toast(Toast.LENGTH_LONG, "����ȡ����û���㹻�Ŀռ����ظ��°���");
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
		pBar.setButton(DialogInterface.BUTTON_POSITIVE, "ȡ��",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						callback.abort();
						if (forceUpdate)
							InitActivity.exit(context);
					}
				});
		pBar.show();
		// ��֤ÿ�ζ�����������
		if (downloadFile.exists())
			downloadFile.delete();
		new AQuery(context).progress(pBar).download(downloadUrl, downloadFile,
				callback);
	}
}
