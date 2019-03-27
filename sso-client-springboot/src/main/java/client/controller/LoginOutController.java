package client.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/loginOut")
public class LoginOutController {

	/**
	 * 跳转到默认页面
	 * 
	 * @param session
	 * @return
	 */
	@GetMapping("/outDef")
	public String outDef(HttpSession session) {
		session.invalidate();
		// 这个是直接退出，走的是默认退出方式
		return "redirect:http://localhost:8081/cas/logout";
	}

	/**
	 * 跳转到指定页面
	 * 
	 * @param session
	 * @return
	 */
	@GetMapping("/outHand")
	public String outHand(HttpSession session) {
		session.invalidate();
		// 退出登录后，跳转到退成成功的页面，不走默认页面
		return "redirect:http://localhost:8081/cas/logout?service=http://localhost:8091/loginOut/outHandPageSuccess";
	}

	// 登出跳转到指定路径
	@GetMapping("/outHandPageSuccess")
	@ResponseBody
	public String outHandPageSuccess() {
		return "cas outHandPageSuccess - 登出跳转到指定路径";
	}

}