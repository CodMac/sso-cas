package client.pulgins.cas;

import java.util.HashMap;
import java.util.Map;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CasConfig {
	
	@Value("${cas.server-url-prefix}")
    private String CAS_SERVER_URL_PREFIX;
    @Value("${cas.server-login-url}")
	private String CAS_SERVER_LOGIN_URL;
    @Value("${cas.client-host-url}")
    private String CAS_CLIENT_HOST_URL;

	/**
	 * 授权过滤器 - 跳过CAS认证
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterAuthenticationRegistration() {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(new AuthenticationFilter());
	    // 设定匹配的路径
	    registration.addUrlPatterns("/*");
	    Map<String,String>  initParameters = new HashMap<String, String>();
	    initParameters.put("casServerLoginUrl", CAS_SERVER_LOGIN_URL);
	    initParameters.put("serverName", CAS_CLIENT_HOST_URL);
	    initParameters.put("ignorePattern", "/static/*|/js/*|/images/*|/css/*|testPass/*");
	    registration.setInitParameters(initParameters);
	    // 设定加载的顺序
	    registration.setOrder(1);
	    return registration;
	}
}
