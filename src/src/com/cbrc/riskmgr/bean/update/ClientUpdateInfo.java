package com.cbrc.riskmgr.bean.update;

/**
 * Created with IntelliJ IDEA.
 * User: lfz
 * Date: 13-9-10
 * Time: ����9:19
 * To change this template use File | Settings | File Templates.
 * �ͻ���������Ϣ
 */
public class ClientUpdateInfo {
    public long	id;						//���¿ͻ������ID
    public String version;				//���¿ͻ�������汾��
    public int version_code;			//���¿ͻ������version_code
    public int lowest_upgrade_app_version_code;//��ͼ��ݵĿͻ�������汾��version_code
    public String recent_change;		//�˰汾�޸ĵ�����
    public String released_on;			//�ͻ������release��ʱ�� ��ʽ����yyyymmddHHMMss
    public String download_url;			//�ͻ�����������ص�ַ
    public String start_url;			//�ͻ����������ͼƬ���ص�ַ
}
