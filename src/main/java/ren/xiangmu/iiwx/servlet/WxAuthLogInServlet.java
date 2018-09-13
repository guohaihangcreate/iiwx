package ren.xiangmu.iiwx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import net.sf.json.JSONObject;
import ren.xiangmu.iiwx.entity.Wx_user;
import ren.xiangmu.iiwx.service.WxUserService;
import ren.xiangmu.iiwx.util.Md5;
import ren.xiangmu.iiwx.util.WxAuthUtil;
//@WebServlet("/wxAuthLogIn")
@WebServlet(urlPatterns = "/wxAuthLogIn")
public class WxAuthLogInServlet extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(WxAuthLogInServlet.class);
	public void init() throws ServletException {
		super.init();
		//获取autowire的bean
		WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

	/*@Bean
	public InternalResourceViewResolver viewResolver() {
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setPrefix("WEB-INF/wx/");
	        viewResolver.setSuffix(".jsp");
	        return viewResolver;
	 }
*/
	
	@Autowired
	private WxUserService wxUserService ;
	
	Md5 md5 = new Md5();

	private static final long serialVersionUID = -2957653326431110490L;
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String option_type = request.getParameter("type");
		if(StringUtils.isNotBlank(option_type)&&"auth".equals(option_type)) {
			String callbackUrl = "http://weixin.xiangmu.ren/callback";
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WxAuthUtil.APPID
					+ "&redirect_uri="+URLEncoder.encode(callbackUrl)
					+ "&response_type=code"
					+ "&scope=snsapi_userinfo"
					+ "&state=STATE#wechat_redirect";
			response.sendRedirect(url);
		}else if(StringUtils.isNotBlank(option_type)&&"register".equals(option_type)){//ע�����
			try {
				Wx_user wx_u = new Wx_user();
				String openid = request.getParameter("openid");
				wx_u.setOpenid(openid);
				String realname =new String(request.getParameter("realname").getBytes("iso8859-1"),"utf-8");
				wx_u.setRealname(realname);
				String email = request.getParameter("email");
				wx_u.setEmail(email);
				String password = "";
				if(StringUtils.isNotBlank(request.getParameter("password"))) {
					 password = new String(request.getParameter("password").getBytes("iso8859-1"),"utf-8");
				}
				if(StringUtils.isNotBlank(password)) {
					wx_u.setPassword(md5.GetMD5Code(password));
				}
				String sex = request.getParameter("sex");
				if(StringUtils.isNotBlank(sex)) {
					wx_u.setSex(Integer.valueOf(sex));
				}
				String birthday = request.getParameter("birthday");
				String enterDay = request.getParameter("enterDay");
				String mobile = request.getParameter("mobile");
				wx_u.setMobile(mobile);
				String idno = request.getParameter("ino");
				wx_u.setIdno(idno);
				try {
					wx_u.setEnterday(enterDay);
					wx_u.setBirthday(birthday);
					wx_u.setLogintype(1);//
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Map queryParam = new HashMap();
				queryParam.put("openid", wx_u.getOpenid());
				queryParam.put("realname", wx_u.getRealname());
				queryParam.put("email", wx_u.getEmail());
				List<Wx_user> list_wxuser = wxUserService.pageListByParamMap(queryParam);
				boolean IsOK = false;
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out =response.getWriter();
				if(list_wxuser!=null&&list_wxuser.size()>0) {
					IsOK = true;
				}else {
					IsOK = false;
					wx_u.setLogintype(1);//״̬
					wxUserService.updateByPrimaryKeyId(wx_u);
					request.getSession().setAttribute("userinfo", wxUserService.getOne(wx_u.getId()));//
				}
				JSONObject json = new JSONObject();
				json.put("IsOK", IsOK);
				json.put("openid", wx_u.getOpenid());
				out.println(json.toString());
				out.close();
//				request.getRequestDispatcher("/xzcx_month.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(StringUtils.isNotBlank(option_type)&&"register".equals(option_type)){//��½����
			try {
				request.getRequestDispatcher("/xzcx_month.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		this.doGet(request, response);
	}

}
