package client.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {

	@GetMapping("/index")
	public String index(HttpServletRequest request, ModelAndView modelView) {
		return "index";
	}

	@GetMapping("/A")
	@ResponseBody
	public String testA(HttpServletRequest request) throws IOException {
		// 获得当前的登录信息
		AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
		String principalName = principal.getName();

		return "cas testA from springboot1 , principalName: " + principalName;
	}

	@GetMapping("/B")
	public String testB() {
		return "redirect:http://localhost:8092/test/A";
	}

}
