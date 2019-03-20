package client.pulgins.mvc;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * mvc 配置
 * 
 * @author mc
 *
 */
@Configurable
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	// 这种方式会在默认的基础上增加/static/**映射到classpath:/static/，不会影响默认的方式，可以同时使用。
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/images/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
	}
	
	
	/**
     * 添加视图解析器控制
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //设定路径"/" 重定向到 "/index"
        registry.addViewController("/").setViewName("forward:/test/index");

        //设定匹配的优先级
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        //添加视图控制类
        super.addViewControllers(registry);
    }
}
