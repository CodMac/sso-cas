package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import server.entity.SysUser;
import server.service.SysUserService;

@RestController
@RequestMapping("/test")
public class TestControler {
	@Autowired
	SysUserService service;

	@GetMapping("/mac")
	public SysUser test(){
		SysUser one = new SysUser();
		one.setUsername("mac");
		return service.findUser(one);
	}
	
	@PostMapping("/a")
	@ResponseBody
	public String a(){
		return "a";
	}
}
