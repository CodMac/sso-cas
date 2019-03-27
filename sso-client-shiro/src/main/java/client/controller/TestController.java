package client.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.buji.pac4j.subject.Pac4jPrincipal;

@Controller
@RequestMapping("/test")
public class TestController {

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/A")
	@ResponseBody
	public String testA() {
		Pac4jPrincipal principal = (Pac4jPrincipal) SecurityUtils.getSubject().getPrincipal();
		String name = principal.getName();
		System.out.println(principal);
		return "cas testA from shrio-2017 . name: " + name;
	}

	@GetMapping("/B")
	public String testB() {
		return "redirect:http://localhost:8091/test/A";
	}

}