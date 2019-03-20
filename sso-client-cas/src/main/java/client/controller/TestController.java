package client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return "cas testA from client-cas";
	}

	@GetMapping("/B")
	public String testB() {
		return "redirect:http://localhost:8091/test/A";
	}

}