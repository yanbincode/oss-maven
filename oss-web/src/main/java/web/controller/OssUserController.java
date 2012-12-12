package web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.OssUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.OssUserService;

import common.support.AbstractControllerSupport;
import common.type.LogicType;
import common.utils.StringUtils;

/**
 * 成员信息控制器
 * 
 * @author yanbin
 * 
 */
@Controller
@RequestMapping("/ossUser.do")
public class OssUserController extends AbstractControllerSupport {

	@Autowired
	private OssUserService ossUserService;

	/**
	 * 进入登录页面判断
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		try {
			// 先检查在session或cookie中是否已经存在了用户名和密码，如果存在跳过登录
			if (null != getSessionInfo(request) || null != getCookieInfo(request)) {
				return "main";
			}
		} catch (Exception e) {
			log.error(e);
		}
		// 转向登录
		return "oss_user/login";
	}

	@RequestMapping(params = "method=login", method = RequestMethod.POST)
	public String login(String userName, String passWord, String saveFlag, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		OssUser ossUser = ossUserService.getByUserNameAndPassWord(userName, passWord);
		// 进行判断，为true，进入主页面；如果为false则提示用户名密码有错
		if (null != ossUser) {
			if (LogicType.LOGIC_YES.toString().equals(saveFlag)) {
				saveSession(request, response, ossUser);
				saveCookie(request, response, ossUser);
			}
			return "main";
		} else {
			model.addAttribute("message", "请输入正确的用户名和密码！");
			return "oss_user/login";
		}
	}

	@RequestMapping(params = "method=test", method = RequestMethod.GET)
	public String test() {
		OssUser ossUser = ossUserService.get(1L);
		ossUser.setName("xxxx");
		ossUserService.add(ossUser);
		System.out.println("aaa");
		return "error";
	}

	/**
	 * 从session取值
	 * 
	 * @param request
	 * @return
	 */
	private OssUser getSessionInfo(HttpServletRequest request) throws Exception {
		// 获取session
		HttpSession session = request.getSession();
		Object userName = session.getAttribute("userName");
		Object passWord = session.getAttribute("passWord");
		if (null == userName || null == passWord) {
			return null;
		}
		OssUser ossUser = ossUserService.getByUserNameAndPassWord(userName.toString(), passWord.toString());
		return ossUser;
	}

	/**
	 * 从cookie中取值
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private OssUser getCookieInfo(HttpServletRequest request) throws Exception {
		String userName = null;
		String passWord = null;
		// 获取所有的cookie数组。
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			// 循环判断cookie对应的名称，并定位取值
			for (Cookie cookie : cookies) {
				if ("userName".equals(cookie.getName())) {
					userName = cookie.getValue();
				}
				if ("passWord".equals(cookie.getName())) {
					passWord = cookie.getValue();
				}
			}
		}
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
			return null;
		}
		OssUser ossUser = ossUserService.getByUserNameAndPassWord(userName, passWord);
		return ossUser;
	}

	/**
	 * 用于保存session中
	 * 
	 * @param request
	 * @param response
	 * @param ossUser
	 */
	private void saveSession(HttpServletRequest request, HttpServletResponse response, OssUser ossUser) {
		// 将登录信息放置到session中，在浏览器开着的时候保持登录
		HttpSession session = request.getSession();
		// 设定Session的失效时间 1800秒
		session.setMaxInactiveInterval(1800);
		// session中保存用户的登录名，密码和名字
		session.setAttribute("name", ossUser.getName());
		session.setAttribute("userName", ossUser.getUserName());
		session.setAttribute("passWord", ossUser.getPassWord());
	}

	/**
	 * 用于保存cookie中
	 * 
	 * @param request
	 * @param response
	 * @param ossUser
	 */
	private void saveCookie(HttpServletRequest request, HttpServletResponse response, OssUser ossUser) {
		// 将登录信息保存到cookie中
		// 一个键值队的，
		Cookie ckUserName = new Cookie("userName", ossUser.getName());
		Cookie ckPassWord = new Cookie("passWord", ossUser.getPassWord());
		// 设置这个Cookie的有效期为1天 ；没有这句代码，cookie存在于内存中
		ckUserName.setMaxAge(60 * 60 * 24);
		ckPassWord.setMaxAge(60 * 60 * 24);
		response.addCookie(ckUserName);
		response.addCookie(ckPassWord);
	}

}
