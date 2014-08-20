package com.cbrc.riskmgr.protocol;

import android.text.TextUtils;

import com.androidquery.AQuery;
import com.cbrc.riskmgr.GlobalConstants;
import com.cbrc.riskmgr.bean.LoginResult;
import com.cbrc.riskmgr.bean.update.ClientUpdateInfo;
import com.rz.framework.protocol.BaseProtocol;


public class ServerApi<T> extends BaseProtocol<T> {

	public ServerApi(Class<T> type, int... option) {
		super(type, option);
		params("_client", "7", "_device", GlobalConstants.PhoneModel,
				"_screen", GlobalConstants.PhoneResolution, "_ver", "1.0");

		if(!TextUtils.isEmpty(GlobalConstants.MemberId)){
			params("_user",Long.valueOf(GlobalConstants.MemberId));
		}

	}

	@Override
	protected String getRootUrl() {
		return GlobalConstants.ServerYzlApi;
	}
	
	@Override
	public boolean execute(AQuery executor, long... expire) {
		if(!TextUtils.isEmpty(GlobalConstants.MemberId)){
			params("_user",Long.valueOf(GlobalConstants.MemberId));
		}
		return super.execute(executor, expire);
	}

	// **********************************************************************************************************
	// 所有服务器接口和方法定义
	// **********************************************************************************************************
	/**
	 * 获取客户端最新版本号
	 *
	 * @return
	 */
	static public BaseProtocol<ClientUpdateInfo> clientUpdate() {
		BaseProtocol<ClientUpdateInfo> protocol = new ServerApi(
				ClientUpdateInfo.class).method(METHOD_GET).url(
				"/client/version");
		return protocol;
	}
    /**
	 * 登录
	 * @param userId 。
	 * @return
	 */
	static public BaseProtocol<LoginResult> login(String userId, String Imei) {
		BaseProtocol<LoginResult> protocol = new ServerApi(LoginResult.class)
				.method(METHOD_GET).url("/client/login");
		if (userId != null) {
			protocol.params("userid", userId);
		}
		return protocol;
	}
	
}
