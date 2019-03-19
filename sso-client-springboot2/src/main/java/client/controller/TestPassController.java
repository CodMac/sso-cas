package client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 允许跳过cas认证 - 在 client.pulgins.cas.CasConfig.filterAuthenticationRegistration() 中配置 ignorePattern
 * @author mac
 *
 */
@RestController
@RequestMapping("/testPass")
public class TestPassController {

	@GetMapping("/A")
	@ResponseBody
	public String testA(){
		return "cas testPassA from client2, config on client.pulgins.cas.CasConfig.filterAuthenticationRegistration()";
	}
}
