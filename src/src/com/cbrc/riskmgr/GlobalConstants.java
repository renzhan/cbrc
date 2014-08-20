package com.cbrc.riskmgr;

public class GlobalConstants {
	static public final String INTENT_EXTRA_MAIN = "start";
	static public final String INTENT_EXTRA_DOWNLOAD_URL = "url";
	static public final String INTENT_EXTRA_DOWNLOAD_NAME = "name";
	static public final String INTENT_EXTRA_APP_TOPIC = "topic";
	static public final String INTENT_EXTRA_APP_DETAIL = "detail";
	static public final String INTENT_EXTRA_PROMOTION = "promotion";
	static public final String INTENT_EXTRA_PRODUCT = "product";
	static public final String INTENT_EXTRA_INTEREST = "interest";

	static public final String SHARED_PREF_UPDATE = "update";
	static public final String SHARED_PREF_UPDATE_IGNORE = "IgnoreVersion";
	static public final String SHARED_PREF_REGISTER = "Register";
	static public final String SHARED_PREF_ISSPLASH = "isSplash";

	// �û���Ա��Ϣ
	public static String MemberId;
	public static String MemberStoreId;
	public static String MemberSubstoreId;
	public static String MemberAccountManagerId;
	public static String MemberShopName;
	public static String device_token;
	public static boolean UserInfoNeedUpdate;

	// �û��豸��Ϣ
	public static String PhoneModel;
	public static String PhoneManufacturer;
	public static String PhoneBrand;
	public static String PhoneResolution;
	public static String PhoneImei;
	public static String SimImsi;

	// ������������ַ��Ϣ
	public static String ServerHxCsmp;
	public static String ServerHxEcp;
	public static String ServerHxImage;
	public static String ServerYzlApi;
	public static String ServerYzlImage;

	// �ͻ��˳���������Ϣ
	public static String ClientCityId;
	public static String ClientStoreId;
	public static String ClientVersion;
	public static String ClientVersionName;

	// �ͻ��˳���ȱʡ����
	public static long DefaultCacheRefreshTime;
	public static long DefaultCheckUpdateTime;
	public static long DefaultCheckConfigTime;
	public static long DefaultSmsWaitTime;
	public static long DefaultCheckOneToOneTime;
	public static String DefaultDownloadDirectory;
	public static boolean DefaultShowProgressDialog;
}
