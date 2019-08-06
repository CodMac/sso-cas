package client.pulgins.cas;

import java.util.HashMap;
import java.util.Map;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
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
	    initParameters.put("ignorePattern", "/static/*|testPass/*");
	    registration.setInitParameters(initParameters);
	    // 设定加载的顺序
	    registration.setOrder(1);
	    return registration;
	}
	
	/**
	 * 单点登出filter
	 * @return
	 */
	@Bean
    public SingleSignOutFilter singleSignOutFilter(){
		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		singleSignOutFilter.setCasServerUrlPrefix(CAS_SERVER_URL_PREFIX);
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }
    @Bean
    public FilterRegistrationBean singleSignOutFilterBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(singleSignOutFilter());
//        filterRegistrationBean.addInitParameter("targetFilterLifecycle","true")
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setName("singleFilter");
        System.out.println("================================singleFilter执行");
        return filterRegistrationBean;
    }
    /**
     * 单点登出Listener
     * @return
     */
    @Bean
    public SingleSignOutHttpSessionListener singleSignOutHttpSessionListener(){
        return new SingleSignOutHttpSessionListener();
    }
    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListenerBean(){
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listenerRegistrationBean= new ServletListenerRegistrationBean<>();
        listenerRegistrationBean.setEnabled(true);
        listenerRegistrationBean.setListener(singleSignOutHttpSessionListener());
        listenerRegistrationBean.setOrder(3);
        listenerRegistrationBean.setName("singleListener");
        System.out.println("================================singleListener执行");
        return listenerRegistrationBean;
    }
    
}
