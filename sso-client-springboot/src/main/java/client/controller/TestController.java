package client.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import client.entity.SysUser;
import client.utils.JacksonUtil;

@Controller
@RequestMapping("/test")
public class TestController {
	final private String PRINCIPAL_MAP_USER_KEY = "zq-user";

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/A")
	@ResponseBody
	public String testA(HttpServletRequest request) throws IOException {
		// 获得当前的登录信息
		AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
		String principalName = principal.getName();
		Map<String, Object> attributes = principal.getAttributes();
		String userJSON = attributes.get(PRINCIPAL_MAP_USER_KEY).toString();
		SysUser sysUser = JacksonUtil.defaultInstance().json2pojo(userJSON, SysUser.class);

		return "cas testA from springboot1 , principalName: " + principalName + ", userName: " + sysUser.getUsername();
	}

	@GetMapping("/B")
	public String testB() {
		return "redirect:http://localhost:8092/test/A";
	}

}
