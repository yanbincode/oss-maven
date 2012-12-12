package common.support;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import common.utils.StringUtils;

/**
 * 控制层，支撑类
 * 
 * @author yanbin
 * 
 */
public abstract class AbstractControllerSupport {

	protected Log log = LogFactory.getLog(AbstractControllerSupport.class.getName());

	/**
	 * 将目标java对象转换成JSON格式的String对象
	 * 
	 * @param object
	 * @return
	 */
	protected String convert2JSONString(Object object) {
		JSONArray jsonObject = JSONArray.fromObject(object);
		return jsonObject.toString();
	}

	/**
	 * 封装ajax输出
	 * 
	 * @param response
	 * @param outputString
	 *            输出字节
	 * @throws IOException
	 */
	protected void ajaxOutput(HttpServletResponse response, String outputString) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(outputString);
		response.getWriter().flush();
	}

	/**
	 * 封装spring redirect重定向到某个页面
	 * 
	 * @param url
	 * @return
	 */
	protected String redirectTo(String url) {
		if (StringUtils.isEmpty(url) || url.startsWith("redirect:")) {
			return url;
		}
		// 利用redirect打头的url spring转发器会默认为重定向
		return "redirect:" + url;
	}

	/**
	 * 封装spring forward到某个页面
	 * 
	 * @param url
	 * @return
	 */
	protected String forwardTo(String url) {
		if (StringUtils.isEmpty(url) || url.startsWith("forward:")) {
			return url;
		}
		return "forward:" + url;
	}

	/**
	 * 获取返回链接
	 * 
	 * @param backUrl
	 * @param request
	 * @return
	 */
	protected String getBackUrl(String backUrl, HttpServletRequest request) {
		if (StringUtils.isNotEmpty(backUrl)) {
			return backUrl;
		}
		backUrl = request.getHeader("referer");
		return backUrl;
	}

	/**
	 * 判断当前用户是否成功登陆
	 * 
	 * @return
	 */

	/**
	 * 获取当前登陆用户的IP
	 */

}
