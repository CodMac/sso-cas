package server.controller;

import java.net.MalformedURLException;
import java.net.URL;

import org.apereo.cas.services.RegexRegisteredService;
import org.apereo.cas.services.RegisteredService;
import org.apereo.cas.services.ReturnAllAttributeReleasePolicy;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CasServiceManageController {

	@Autowired
	@Qualifier("servicesManager")
	private ServicesManager servicesManager;

	@GetMapping("/addClient/{serviceId}/{id}")
	public String addClient(@PathVariable("serviceId") String serviceId, @PathVariable("id") int id) throws MalformedURLException {
		RegexRegisteredService service = new RegexRegisteredService();
		service.setAttributeReleasePolicy(new ReturnAllAttributeReleasePolicy());
		
		String serviceIdStr = "^(https|imaps|http)://" + serviceId + ".*";
		service.setServiceId(serviceIdStr);
		service.setId(id);
		service.setName("client-" + serviceId);
		service.setLogoutUrl(new URL("http://" + serviceId));
		
		servicesManager.save(service);
		// 执行load让他生效
		servicesManager.load();

		return "通过servicesManager动态添加 ["+serviceId+"] 完成";

	}
	
	@GetMapping("/deleteClient/{serviceId}/{id}")
	public String deleteClient(@PathVariable("serviceId") String serviceId, @PathVariable("id") int id) throws MalformedURLException {
		
		RegisteredService service = servicesManager.findServiceBy(id);
		servicesManager.delete(service);
		//执行load生效
        servicesManager.load();
		
		return "通过servicesManager动态删除 ["+serviceId+"] 完成";

	}
}
