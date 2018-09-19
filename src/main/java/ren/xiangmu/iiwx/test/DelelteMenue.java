package ren.xiangmu.iiwx.test;

import ren.xiangmu.iiwx.po.AccessToken;
import ren.xiangmu.iiwx.util.WxAuthUtil;

public class DelelteMenue {

	public DelelteMenue() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccessToken accessToken = WxAuthUtil.getAccessToken();
		System.out.println("获取Token"+accessToken.getToken());
		System.out.println("获取Expires"+accessToken.getExpires());
		int result = WxAuthUtil.deleteMenue(accessToken.getToken());
		if(result==0) {
			System.out.println("删除菜单成功");
		}else {
			System.out.println(result);
		}
	}

}
