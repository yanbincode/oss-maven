package web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import common.support.AbstractControllerSupport;
import common.utils.MessageUtils;

@Controller
@RequestMapping("/login")
public class LoginController extends AbstractControllerSupport {

	/**
	 * 跳转登录页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "method=login", method = RequestMethod.GET)
	public String login() {
		return "door/login";
	}

	/**
	 * 登录失败，跳转还到登录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=fail", method = RequestMethod.GET)
	public String fail(ModelMap model) {
		// 登录失败，请确认用户名和密码！
		model.addAttribute("message", MessageUtils.getInfoValue("LOGIN_FAIL"));
		return "door/login";
	}

	/**
	 * 登录成功，跳转到主页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "method=success", method = RequestMethod.GET)
	public String success() {
		return "main";
	}

	/**
	 * 注销，跳转到登录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		// 注销成功，请重新登录！
		model.addAttribute("message", MessageUtils.getInfoValue("LOGOUT"));
		return "door/login";
	}

	/**
	 * session 超时，需要重新登录
	 * 
	 * @param model
	 * @return
	 */
	public String timeout(ModelMap model) {
		// 搁置时间太长，请重新登录！
		model.addAttribute("message", MessageUtils.getInfoValue("TIMEOUT"));
		return "door/login";
	}

	/**
	 * 提交登录，（不需要实现，利用spring security 来实现）
	 * 
	 * @return
	 */
	@RequestMapping(params = "method=login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return null;
	}

}
