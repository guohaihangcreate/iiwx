package ren.xiangmu.iiwx.test;

import net.sf.json.JSONObject;
import ren.xiangmu.iiwx.po.AccessToken;
import ren.xiangmu.iiwx.util.WxAuthUtil;

public class CreateMenue {

	public CreateMenue() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccessToken accessToken = WxAuthUtil.getAccessToken();
		System.out.println("获取token"+accessToken.getToken());
		System.out.println("获取Expires"+accessToken.getExpires());
		String menue = JSONObject.fromObject(WxAuthUtil.initMenue()).toString();
		int result = WxAuthUtil.createMenue(accessToken.getToken(), menue);
		System.out.println(result);
		if(result==0) {
			System.out.println("创建菜单成功!");
		}else {
			System.out.println(result);
		}
	}

}
