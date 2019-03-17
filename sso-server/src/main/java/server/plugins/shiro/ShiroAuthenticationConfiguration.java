package server.plugins.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import server.plugins.shiro.matcher.CredentialsMatcher;
import server.plugins.shiro.realm.ShiroRealm;

/**
 * @description: shiro配置
 */
@Configuration("shiroAuthenticationConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class ShiroAuthenticationConfiguration  implements AuthenticationEventExecutionPlanConfigurer {

    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    @Bean(name="securityManager")
    public SecurityManager securityManager() {
        DefaultSecurityManager securityManager =  new DefaultSecurityManager();
        //设置自定义realm.
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCachingEnabled(false);
        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        shiroRealm.setAuthenticationCachingEnabled(false);
        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        shiroRealm.setAuthorizationCachingEnabled(false);
        //设置自定义密码校验器
        shiroRealm.setCredentialsMatcher(new CredentialsMatcher());
        return shiroRealm;
    }

    /**
     * Spring静态注入
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean(){
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[]{securityManager()});
        return factoryBean;
    }


    @Bean
    public AuthenticationHandler shiroAuthenticationHandler() {
        ShiroAuthenticationHandler handler = new ShiroAuthenticationHandler(ShiroAuthenticationHandler.class.getSimpleName(), servicesManager, new DefaultPrincipalFactory(),10);
        return handler;
    }

    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(shiroAuthenticationHandler());
    }

}
