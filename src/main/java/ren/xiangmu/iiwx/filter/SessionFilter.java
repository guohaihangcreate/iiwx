package ren.xiangmu.iiwx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {

	// 标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
	String NO_LOGIN = "您还未登录";

	// 不需要登录就可以访问的路径(比如:注册登录等)
	String[] includeUrls = new String[] { "/login_wx", "/ajaxLogIn", "/resetpassword", "/wxAuthLogIn",
			"/ajaxRegistered", "/callback", "/CodeServlet", "/checkResetLink", "/css/weui.css", "/css/example.css",
			"/images/icon_tabbar.png","/js/example.js","/js/zepto.min.js","/js/jweixin-1.0.0.js"};

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();

		System.out.println("filter url:" + uri);
		// 是否需要过滤
		boolean needFilter = isNeedFilter(uri);
		//如果url中有点存在放行，标识是请求资源文件例如 .css,.image,.jpg,.js等等
		if(uri.indexOf(".")>0) {
			filterChain.doFilter(request, response);
		}
		else if (!needFilter) { // 不需要过滤直接传给下一个过滤器
			filterChain.doFilter(servletRequest, servletResponse);
		} else { // 需要过滤器
			// session中包含user对象,则是登录状态
			if (session != null && session.getAttribute("userinfo") != null) {
				// System.out.println("user:"+session.getAttribute("user"));
				filterChain.doFilter(request, response);
			} else {
				String requestType = request.getHeader("X-Requested-With");
				// 判断是否是ajax请求
				if (requestType != null && "XMLHttpRequest".equals(requestType)) {
					response.getWriter().write(this.NO_LOGIN);
				} else {
					// 重定向到登录页(需要在static文件夹下建立此html文件)
					// response.sendRedirect(request.getContextPath() + "/login_wx");
						response.sendRedirect("/login_wx");
				}
				return;
			}
		}
	}

	/**
	 * @Author: xxxxx
	 * @Description: 是否需要过滤
	 * @Date: 2018-03-12 13:20:54
	 * @param uri
	 */
	public boolean isNeedFilter(String uri) {

		for (String includeUrl : includeUrls) {
			if (includeUrl.equals(uri)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}