package com.cbrc.riskmgr.bean.update;

/**
 * Created with IntelliJ IDEA.
 * User: lfz
 * Date: 13-9-10
 * Time: 上午9:19
 * To change this template use File | Settings | File Templates.
 * 客户端升级信息
 */
public class ClientUpdateInfo {
    public long	id;						//最新客户端软件ID
    public String version;				//最新客户端软件版本号
    public int version_code;			//最新客户端软件version_code
    public int lowest_upgrade_app_version_code;//最低兼容的客户端软件版本的version_code
    public String recent_change;		//此版本修改的内容
    public String released_on;			//客户端软件release的时间 格式如下yyyymmddHHMMss
    public String download_url;			//客户端软件包下载地址
    public String start_url;			//客户端软件启动图片下载地址
}
