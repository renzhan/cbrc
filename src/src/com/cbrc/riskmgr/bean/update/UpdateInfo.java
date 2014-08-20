package com.cbrc.riskmgr.bean.update;

/**
 * 客户端更新信息
 */
public class UpdateInfo {
	public int display; // 显示标志。1-显示更新;2-不显示更新;3-强制更新
	public String version; // 更新客户端版本号
	public String message; // 客户端更新信息
	public String remark; // 备注
	public String url_local; // 更新客户端本地下载地址，下载客户端时使用该地址
	public String url_remote; // 更新客户端远端下载地址
	public String version_code; // 客户端版本字符串
	public String phone_type; // 客户端类型
}
