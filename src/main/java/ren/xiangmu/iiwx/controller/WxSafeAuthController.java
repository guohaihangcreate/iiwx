package ren.xiangmu.iiwx.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ren.xiangmu.iiwx.entity.Wx_user;
import ren.xiangmu.iiwx.service.WxUserService;
import ren.xiangmu.iiwx.util.Md5;
import ren.xiangmu.iiwx.util.SendMailUtil;

@Controller
public class WxSafeAuthController {

	private static final Logger LOG = LoggerFactory.getLogger(WxSafeAuthController.class);

	public WxSafeAuthController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private WxUserService wxUserService;

	@RequestMapping(value = "forget_password")
	@ResponseBody
	public Map forgetPass(HttpServletRequest request, String email) {
		Map paramap = new HashMap<String, String>();
		paramap.put("email", email);
		Wx_user users = wxUserService.pageListByParamMap(paramap).get(0);
		Map map = new HashMap<String, String>();
		String msg = "";
		if (users == null) { // 用户名不存在
			msg = "用户名不存在,你不会忘记用户名了吧?";
			map.put("msg", msg);
			return map;
		}
		try {
			String secretKey = UUID.randomUUID().toString(); // 密钥
			Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期
			long date = outDate.getTime() / 1000 * 1000; // 忽略毫秒数
			users.setValidateCode(secretKey);
			users.setOutDate(outDate);
			wxUserService.updateByPrimaryKeyId(users); // 保存到数据库
			String key = users.getEmail() + "$" + date + "$" + secretKey;
			String digitalSignature = Md5.GetMD5Code(key); // 数字签名

			String emailTitle = "柯锐特微信平台密码找回";
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			String resetPassHref = basePath + "checkResetLink?sid=" + digitalSignature + "&email=" + users.getEmail();
			String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href=" + resetPassHref + " target='_BLANK'>点击我重新设置密码</a>"
					+ "<br/>tips:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'" + key + "\t" + digitalSignature;
			System.out.print(resetPassHref);
			/*
			 * to发送给谁,cs,ms密送，邮件主题，邮件主题
			 */
			SendMailUtil sendMailUtil = new SendMailUtil();
			// SendMail.getInstance().sendHtmlMail(emailTitle, emailContent,
			// users.getEmail());
			String[] to = { users.getEmail() };
			String[] cs = { "", "", "" };
			String[] ms = { "" };
			String[] fileList = null;
			// 第一个参数to 收件箱地址，第二个抄送，第三个秘送，第四个邮件抬头，第五个邮件正文，第六个发件箱地址，第七个附list，
			sendMailUtil.send_(to, cs, ms, emailTitle, emailContent, "weixin@xiangmu.ren", fileList, "smtp.mxhichina.com",
					"weixin@xiangmu.ren", "AAaa1234");
			msg = "操作成功,已经发送找回密码链接到您邮箱。请在30分钟内重置密码";
			// logInfo(request, email, "申请找回密码");
		} catch (Exception e) {
			e.printStackTrace();
			msg = "邮箱不存在？未知错误,联系管理员吧。";
		}
		map.put("msg", msg);
		return map;
	}

	@RequestMapping(value = "checkResetLink", method = RequestMethod.GET)
	public String checkResetLink(String sid, String email,HttpServletRequest request) {
		ModelAndView model = new ModelAndView("error");
		String msg = "";
		if (sid.equals("") || email.equals("")) {
			msg = "链接不完整,请重新生成";
//			model.addObject("msg", msg);
			// logInfo(userName, "找回密码链接失效");
			// return model;
		}
		Map paramap = new HashMap<String, String>();
		paramap.put("email", email);
		Wx_user users = wxUserService.pageListByParamMap(paramap).get(0);
		if (users == null) {
			msg = "链接错误,无法找到匹配用户,请重新申请找回密码.";
//			model.addObject("msg", msg);
			// logInfo(userName, "找回密码链接失效");
			// return model;
		}
		Timestamp outDate = users.getOutDate();
		if (outDate.getTime() <= System.currentTimeMillis()) { // 表示已经过期
			msg = "链接已经过期,请重新申请找回密码.";
//			model.addObject("msg", msg);
			// logInfo(userName, "找回密码链接失效");
			// return model;
		}
		String key = users.getEmail() + "$" + outDate.getTime() / 1000 * 1000 + "$" + users.getValidateCode(); // 数字签名
		String digitalSignature = Md5.GetMD5Code(key);
		System.out.println(key + "\t" + digitalSignature);
		if (!digitalSignature.equals(sid)) {
			msg = "链接不正确,是否已经过期了?重新申请吧";
			model.addObject("msg", msg);
			// logInfo(userName, "找回密码链接失效");
			// return model;
		}
		request.setAttribute("email", email);
		return "wx/reset_password";
	}

	@RequestMapping(value = "resetpassword", method = RequestMethod.POST)
	@ResponseBody
	public Map resetpassword(String sid, String email,HttpServletRequest request ) {
		Map returnMap = new HashMap<String, String>();
		String msg = "";
		boolean IsOK = false;
		Md5 md5 = new Md5();
		String password = request.getParameter("password");
		Map paramap = new HashMap<String, String>();
		paramap.put("email", email);
		Wx_user users = wxUserService.pageListByParamMap(paramap).get(0);
		if (users != null) {
			users.setPassword(md5.GetMD5Code(password));//
		}else {
			msg = "邮箱不存在？未知错误,联系管理员吧。";
		}
		int i = wxUserService.updateByPrimaryKeyId(users);
		if(i>0) {
			IsOK = true;
			msg = "密码已经修改成功，请用新密码登陆微服务!";
		}else {
			msg = "密码修改失败,联系管理员吧。";
		}
		returnMap.put("msg", msg);
		returnMap.put("IsOK", IsOK);
		return returnMap;
	}
	
	@RequestMapping(value = "modifypassword", method = RequestMethod.POST)
	@ResponseBody
	public Map modifypassword(String sid, String email,String passworded,String passowrd_new,HttpServletRequest request ) {
		Map returnMap = new HashMap<String, String>();
		String msg = "";
		boolean IsOK = false;
		Md5 md5 = new Md5();
		Map paramap = new HashMap<String, String>();
		paramap.put("email", email);
		if(StringUtils.isNotBlank(passworded)) {
			paramap.put("password", md5.GetMD5Code(passworded));
		}
		Wx_user user = null;
		List<Wx_user> users = wxUserService.pageListByParamMap(paramap);
		if(users!=null&&users.size()>0) {
			 user = wxUserService.pageListByParamMap(paramap).get(0);
		}
		if (user != null) {
			user.setPassword(md5.GetMD5Code(passowrd_new));//
		}else {
			msg = "邮箱不存在?未知错误,联系管理员吧。";
		}
		int i = wxUserService.updateByPrimaryKeyId(user);
		if(i>0) {
			IsOK = true;
			msg = "密码已经修改成功，请用新密码登陆微服务!";
		}else {
			msg = "密码修改失败,联系管理员吧。";
		}
		returnMap.put("IsOK", IsOK);
		returnMap.put("msg", msg);
		return returnMap;
	}


}
