package com.cbrc.riskmgr;

import com.rz.framework.BaseApplication;
import com.rz.framework.util.ConfigUtil;
import com.rz.framework.util.PackageUtil;
import com.rz.framework.util.SystemInfoUtil;



public class MainApplication extends BaseApplication {


	@Override
	public void onCreate() {
		super.onCreate();
		try {
			ConfigUtil config = ConfigUtil.getInstance();
			// ��ȡ�ֻ�ϵͳ����
			GlobalConstants.PhoneBrand = SystemInfoUtil.getBrand();
			GlobalConstants.PhoneManufacturer = SystemInfoUtil.getManufacturer();
			GlobalConstants.PhoneModel = SystemInfoUtil.getModel();
			GlobalConstants.PhoneResolution = SystemInfoUtil.getResolution();
			GlobalConstants.PhoneImei = SystemInfoUtil.getPhoneImei();
			GlobalConstants.SimImsi = SystemInfoUtil.getSimImsi();
			// �������ļ���ȡ�ͻ��˳�������
			GlobalConstants.ClientStoreId = config
					.getProperty("CLIENT_STORE_ID");
			GlobalConstants.ClientCityId = config.getProperty("CLIENT_CITY_ID");
			GlobalConstants.ClientVersion = config
					.getProperty("CLIENT_VERSION_CODE")
					+ "_" + PackageUtil.getAppVersionCode();
			GlobalConstants.ClientVersionName = config.getProperty("CLIENT_VERSION_NAME");
			// �������ļ���ȡ��������������
			GlobalConstants.ServerHxCsmp = config.getProperty("SERVER_HXCSMP");
			GlobalConstants.ServerHxEcp = config.getProperty("SERVER_HXECP");
			GlobalConstants.ServerHxImage = config
					.getProperty("SERVER_HXIMAGE");
			GlobalConstants.ServerYzlApi = config.getProperty("SERVER_YZLAPI");
			GlobalConstants.ServerYzlImage = config
					.getProperty("SERVER_YZLIMAGE");
			// �������ļ���ȡ���г���ȱʡ����
			GlobalConstants.DefaultCacheRefreshTime = config.getNumber(
					"DEFAULT_CACHE_REFRESHTIME", 10) * 60 * 1000;
			GlobalConstants.DefaultSmsWaitTime = config.getNumber(
					"DEFAULT_SMSWAIT_TIME", 10) * 60 * 1000;
			GlobalConstants.DefaultCheckUpdateTime = config.getNumber(
					"DEFAULT_CHECKUPDATE_TIME", 12) * 60 * 60 * 1000;
			GlobalConstants.DefaultCheckConfigTime = config.getNumber(
					"DEFAULT_CHECKCONFIG_TIME", 12) * 60 * 60 * 1000;
			GlobalConstants.DefaultCheckOneToOneTime = config.getNumber(
					"DEFAULT_CHECKONETOONE_TIME", 1) * 60 * 1000;
			GlobalConstants.DefaultShowProgressDialog = config.getBoolean(
					"DEFAULT_SHOW_PROGRESSDIALOG", true);
			GlobalConstants.DefaultDownloadDirectory = config.getProperty(
					"DEFAULT_DOWNLOAD_DIRECTORY",
					SystemInfoUtil.getSdCardDirectory());
		} catch (Throwable t) {
			t.printStackTrace();
			return;
		}
	}
}
