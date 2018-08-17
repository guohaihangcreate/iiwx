package ren.xiangmu.iiwx.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.sf.json.JSONObject;
import ren.xiangmu.iiwx.entity.Wx_user;
import ren.xiangmu.iiwx.service.WxUserService;
import ren.xiangmu.iiwx.util.WxAuthUtil;

@WebServlet(urlPatterns = "/callback")
public class CallBackServlet extends HttpServlet {

	public CallBackServlet() {
		// TODO Auto-generated constructor stub
	}
	
	public void init() throws ServletException {
		super.init();
		//获取autowire的bean
		WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

	@Autowired
	private WxUserService wxUserService;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String optiontype = request.getParameter("optiontype");
		String dispatcherpage = "/login_wx.jsp";
		Wx_user userinfo = null;
		List<Wx_user> userinfos = null;
		if (StringUtils.isNotBlank(optiontype) && "registered".equals(optiontype)) {
			String openid = request.getParameter("openid");
			if (StringUtils.isNotBlank(openid)) {
				Map queryparam = new HashMap();
				queryparam.put("openid", openid);
				userinfos = wxUserService.pageListByParamMap(queryparam);
			}
			if (userinfos != null && userinfos.size() > 0) {
				// 跳转到主页面
				if (userinfos != null && userinfos.size() > 0) {
					userinfo = userinfos.get(0);
					String pass = userinfo.getPassword();
					int logintype = userinfo.getLogintype();
					if (StringUtils.isNotBlank(pass) && logintype == 1) {
						dispatcherpage = "wx/wx_main.jsp";
					}
				}
			}
		} else {
			String code = request.getParameter("code");
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxAuthUtil.APPID + "&secret="
					+ WxAuthUtil.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
			JSONObject authObject = WxAuthUtil.doGetJson(url);
			String token = authObject.getString("access_token");
			String openid = authObject.getString("openid");
			String userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token + "&openid=" + openid
					+ "&lang=zh_CN";
			if (StringUtils.isNotBlank(openid)) {
				Map queryparam = new HashMap();
				queryparam.put("openid", openid);
				userinfos = wxUserService.pageListByParamMap(queryparam);
			}
			if (userinfos == null || userinfos.size() == 0) {
				userinfo = new Wx_user();
				JSONObject userInfoObject = WxAuthUtil.doGetJson(userUrl);
				String nickname = userInfoObject.getString("nickname");
				String country = userInfoObject.getString("country");
				String city = userInfoObject.getString("city");
				String province = userInfoObject.getString("province");
				String headimgurl = userInfoObject.getString("headimgurl");
				String privilege = userInfoObject.getString("privilege");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (userInfoObject.has("unionid")) {
					String unionid = userInfoObject.getString("unionid");
					userinfo.setUnionid(unionid);
				}
				String sex = userInfoObject.getString("sex");
				userinfo.setNickname(nickname);
				userinfo.setCountry(country);
				userinfo.setCity(city);
				userinfo.setProvince(province);
				userinfo.setHeadimgurl(headimgurl);
				userinfo.setPrivilege(privilege);
				userinfo.setLogintype(0);// 注册状态，1为已经注册，0为未注册
				String time = sdf.format(new Date());
				try {
					Date createtime = sdf.parse(time);
					userinfo.setCreatetime(createtime);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (StringUtils.isNotBlank(sex)) {
					userinfo.setSex(Integer.valueOf(sex));
				}
				userinfo.setOpenid(openid);
				// 根据openId查询该用户是否已经注册
				wxUserService.insert(userinfo);
			} else {
				if (userinfos != null && userinfos.size() > 0) {
					userinfo = userinfos.get(0);
					String pass = userinfo.getPassword();
					int logintype = userinfo.getLogintype();
					// 标识已经注册
					if (StringUtils.isNotBlank(pass) && logintype == 1) {
						dispatcherpage = "wx/nav_tabbar.jsp";
					}
				}
			}
		}
		request.getSession().setAttribute("userinfo", userinfo);
		request.setAttribute("userinfo", userinfo);
		request.getRequestDispatcher(dispatcherpage).forward(request, response);
	}

}
