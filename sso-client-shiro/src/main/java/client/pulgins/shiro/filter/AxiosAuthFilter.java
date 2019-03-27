package client.pulgins.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.jasig.cas.client.authentication.AttributePrincipal;

public class AxiosAuthFilter extends AdviceFilter {

	/**
	 * 
	 * @param request
	 * @param response
	 * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
	 * @throws Exception
	 */
	@Override
	protected boolean preHandle(ServletRequest req, ServletResponse resp) throws Exception {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
		
		//已在前端做了拦截添加，每次axios请求都会带Header['zq-request-type': 'axios']
		String zqRequestType = request.getHeader("zq-request-type");
		
		// 获得当前的cas登录信息 - 无法得到cas登录信息，还没到cas校验的filter
		AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
		
		// 获得当前的shiro代理cas登录信息 - 无法得到cas登录信息，还没到cas校验的shiroFilter
		//Pac4jPrincipal principalShiro = (Pac4jPrincipal) SecurityUtils.getSubject().getPrincipal();
		
		if (principal == null && StringUtils.equals(zqRequestType, "axios")) {
			response.setHeader("authCode", "401");// 打上权限校验标记
			response.getWriter().append("No Access").flush();
			return false;
		} else {
			response.setHeader("authCode", "205");//前端可捕获，代表已鉴权通过
			return true;
		}
	}

}
