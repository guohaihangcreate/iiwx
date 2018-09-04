package ren.xiangmu.iiwx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import ren.xiangmu.iiwx.entity.Wx_user;
import ren.xiangmu.iiwx.service.WxUserService;
import ren.xiangmu.iiwx.util.WxAuthUtil;

@Controller
public class CallBackController {

	private static final Logger LOG = LoggerFactory.getLogger(CallBackController.class);

	public CallBackController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private WxUserService wxUserService;

	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	private String callback(ModelMap map,
			HttpServletRequest request) { /** @RequestParam("name")绑定请求地址中的name到参数name中 ModelMap map 存放返回内容 */
		String optiontype = request.getParameter("optiontype");
		HttpSession session= request.getSession();
		String dispatcherpage = "login_wx";
		Wx_user userinfo = null;
		List<Wx_user> userinfos = null;
		if (StringUtils.isNotBlank(optiontype) && "registered".equals(optiontype)) {
			Wx_user user = new Wx_user();
			Wx_user wx_user = (Wx_user) request.getSession().getAttribute("userinfo");
			String openid = wx_user.getOpenid();
			if (StringUtils.isNotBlank(openid)) {
				user.setOpenid(openid);
			}
			String realname = request.getParameter("realname");
			if (StringUtils.isNotBlank(realname)) {
				user.setRealname(realname);
			}
			String email = request.getParameter("email");
			if (StringUtils.isNotBlank(email)) {
				user.setEmail(email);
			}
			String password = request.getParameter("password");
			if (StringUtils.isNotBlank(password)) {
				user.setPassword(password);
			}
			String sex = request.getParameter("sex");
			if (StringUtils.isNotBlank(sex)) {
				user.setSex(Integer.valueOf(sex));
			}
			String birthday = request.getParameter("birthday");
			if (StringUtils.isNotBlank(sex)) {
				user.setBirthday(birthday);
			}
			String enterDay = request.getParameter("enterDay");
			if (StringUtils.isNotBlank(enterDay)) {
				user.setEnterday(enterDay);
			}
			String mobile = request.getParameter("mobile");
			if (StringUtils.isNotBlank(mobile)) {
				user.setMobile(mobile);
			}
			String ino = request.getParameter("ino");
			if (StringUtils.isNotBlank(ino)) {
				user.setIdno(ino);
			}
			if (wx_user != null) {
				user.setId(wx_user.getId());
			}
			if (StringUtils.isNotBlank(ino)) {
				user.setIdno(ino);
			}
			// 用注册信息中的信息完善user
			wxUserService.updateByPrimaryKeyId(user); 
			session.setAttribute("userinfo", wxUserService.getOne(user.getId()));
			dispatcherpage = "wx/nav_tabbar";
		}
		if (StringUtils.isNotBlank(optiontype) && "login".equals(optiontype)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Map queryparam = new HashMap();
			if (StringUtils.isNotBlank(email)) {
				queryparam.put("email", email);
			}
			if (StringUtils.isNotBlank(password)) {
				queryparam.put("password", password);
			}
			List<Wx_user> wxusers = wxUserService.pageListByParamMap(queryparam);
			if (wxusers != null && wxusers.size() > 0) {
				Wx_user user = wxusers.get(0);
			}
		}
		if (StringUtils.isBlank(optiontype)) {
			// 首次进入公众号
			String code = request.getParameter("code");
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxAuthUtil.APPID + "&secret="
					+ WxAuthUtil.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
			JSONObject authObject = WxAuthUtil.doGetJson(url);
			String token = authObject.getString("access_token");
			String openid = authObject.getString("openid");
			String userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token + "&openid=" + openid
					+ "&lang=zh_CN";
			if (StringUtils.isNotBlank(openid)) {
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
				Map queryMap = new HashMap<String,String>();
				queryMap.put("openid", openid);
				List<Wx_user> wx_users = wxUserService.pageListByParamMap(queryMap);
				if(wx_users!=null&&wx_users.size()>0) {
					Wx_user wx_user = wx_users.get(0);
					wxUserService.updateByPrimaryKeyId(wx_user);
					session.setAttribute("userinfo", wxUserService.getOne(wx_user.getId()));
				}else {
					wxUserService.insert(userinfo);
					session.setAttribute("userinfo", wxUserService.getOne(userinfo.getId()));
				}
				/*
				 * 更新session中的用户微信注册信息
				 */
				if(session.getAttribute("userinfo")!=null) {
					Wx_user wx_user = (Wx_user) session.getAttribute("userinfo");
					//session中用户名和密码都不为空直接允许登陆
					if(wx_user.getEmail()!=null&&wx_user.getPassword()!=null) {
						dispatcherpage = "wx/nav_tabbar";
					}else {
						//跳入登陆页面
						dispatcherpage = "login_wx";
					}
				}else {
					//跳入登陆页面
					dispatcherpage = "login_wx";
				}
			} 
		}
		return dispatcherpage; /** 返回的是显示数据的html的文件名 */
	}

	@RequestMapping(value = "/login_wx", method = RequestMethod.GET)
	private String dispatchLogin_wx(ModelMap map) { /** @RequestParam("name")绑定请求地址中的name到参数name中 ModelMap map 存放返回内容 */
		return "login_wx"; /** 返回的是显示数据的html的文件名 */
	}

	@RequestMapping(value = "/forgetPassWord", method = RequestMethod.GET)
	private String dispatchForgetPassWord(
			ModelMap map) { /** @RequestParam("name")绑定请求地址中的name到参数name中 ModelMap map 存放返回内容 */
		return "forgetPassWord"; /** 返回的是显示数据的html的文件名 */
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	private String dispatchRegister(ModelMap map) { /** @RequestParam("name")绑定请求地址中的name到参数name中 ModelMap map 存放返回内容 */
		return "register"; /** 返回的是显示数据的html的文件名 */
	}

	@RequestMapping(value = "/wx_main", method = RequestMethod.GET)
	private String dispatchWxmain(ModelMap map) { /** @RequestParam("name")绑定请求地址中的name到参数name中 ModelMap map 存放返回内容 */
		return "wx/wx_main"; /** 返回的是显示数据的html的文件名 */
	}

	@RequestMapping(value = "/adressList", method = RequestMethod.GET)
	private String dispatchAdressList(
			ModelMap map) { /** @RequestParam("name")绑定请求地址中的name到参数name中 ModelMap map 存放返回内容 */
		return "wx/adressList"; /** 返回的是显示数据的html的文件名 */
	}

	@RequestMapping(value = "/myinfo", method = RequestMethod.GET)
	private String dispatchmyinfo(ModelMap map) { /** @RequestParam("name")绑定请求地址中的name到参数name中 ModelMap map 存放返回内容 */
		return "wx/myinfo"; /** 返回的是显示数据的html的文件名 */
	}

	@RequestMapping(value = "/found", method = RequestMethod.GET)
	private String dispatchfound(ModelMap map) { /** @RequestParam("name")绑定请求地址中的name到参数name中 ModelMap map 存放返回内容 */
		return "wx/found"; /** 返回的是显示数据的html的文件名 */
	}

	@RequestMapping(value = "/ajaxcheckEmailPassWord", method = RequestMethod.POST)
	@ResponseBody
	private boolean ajax_date(HttpServletRequest request,
			HttpServletResponse response) { /** @RequestParam("name")绑定请求地址中的name到参数name中 ModelMap map 存放返回内容 */
		boolean IsOK = false;
		Wx_user wx_user = (Wx_user) request.getSession().getAttribute("userinfo");
		String email = request.getParameter("email");
		response.setContentType("text/plain");// 等同于response.setHeader("Content-Type", "image/jpeg");
		// 9.设置响应头控制浏览器不要缓存
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		Map paraMap = new HashMap<String, String>();
		if (StringUtils.isNotBlank(email)) {
			paraMap.put("email", email);
		}
		List<Wx_user> wx_users = wxUserService.pageListByParamMap(paraMap);
		if (wx_users != null && wx_users.size() > 0) {
			IsOK = true;
		}
		return IsOK;
	}

	@RequestMapping(value = "/salraypage", method = RequestMethod.GET)
	private String salraypage(HttpServletRequest request,ModelMap map) { /** @RequestParam("name")绑定请求地址中的name到参数name中 ModelMap map 存放返回内容 */
		request.getSession().getAttribute("userinfo");
		return "wx/salraypage"; /** 返回的是显示数据的html的文件名 */
	}

}
