package web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.AccountType;
import model.OssUser;
import model.PayInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.AccountTypeService;
import service.OssUserService;
import service.PayInfoService;

import common.exception.OssRollbackCheckedException;
import common.support.AbstractControllerSupport;
import common.utils.BeanUtils;
import common.utils.MessageUtils;
import common.validation.ValidatorUtils;

/**
 * 支出控制器
 * 
 * @author yanbin
 * 
 */
@Controller
@RequestMapping("/payInfo.do")
public class PayInfoController extends AbstractControllerSupport {

	@Autowired
	private PayInfoService payInfoService;
	@Autowired
	private OssUserService ossUserServic;
	@Autowired
	private AccountTypeService accountTypeService;

	/**
	 * 显示list
	 * 
	 * @param pageIndex
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=show", method = RequestMethod.GET)
	public String showList(Long pageIndex, ModelMap model) {
		List<PayInfo> payInfos = payInfoService.getShowList(pageIndex);
		model.addAttribute("payInfos", payInfos);
		Long count = payInfoService.getCount();
		model.addAttribute("count", count);
		model.addAttribute("lastIndex", payInfoService.getLastPage(count));
		model.addAttribute("pageIndex", pageIndex);
		return "pay_info/list";
	}

	/**
	 * 添加支出信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=add", method = RequestMethod.GET)
	public String add(Long pageIndex, ModelMap model, HttpServletRequest request) {
		PayInfo payInfo = new PayInfo();
		List<OssUser> ossUsers = ossUserServic.getAllUsers();
		List<AccountType> accountTypes = accountTypeService.getAccountTypesByUse(AccountType.USE_PLACE_PAY);
		model.addAttribute("payInfo", payInfo);
		model.addAttribute("ossUsers", ossUsers);
		model.addAttribute("accountTypes", accountTypes);
		model.addAttribute("backUrl", getBackUrl(null, request));
		model.addAttribute("pageIndex", pageIndex);
		return "pay_info/add";
	}

	/**
	 * 添加支出信息
	 * 
	 * @param payInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=add", method = RequestMethod.POST)
	public String add(String backUrl, Long pageIndex, PayInfo payInfo, BindingResult result, ModelMap model) {
		model.addAttribute("payInfo", payInfo);
		List<OssUser> ossUsers = ossUserServic.getAllUsers();
		List<AccountType> accountTypes = accountTypeService.getAccountTypesByUse(AccountType.USE_PLACE_PAY);
		model.addAttribute("ossUsers", ossUsers);
		model.addAttribute("accountTypes", accountTypes);
		try {
			payInfoService.validateNotNull(payInfo);
			payInfoService.add(payInfo);
			model.addAttribute("message", MessageUtils.getInfoValue("SUCC_ADD"));
		} catch (OssRollbackCheckedException e) {
			log.error(e);
			if (null != e.getErrors() && e.hasErrors()) {
				ValidatorUtils.convert2SpringError(e.getErrors(), result);
				model.addAttribute("message", MessageUtils.getInfoValue("FAIL_ADD"));
			} else {
				model.addAttribute("message", MessageUtils.getInfoValue("FAIL_ADD") + "<br>" + e.getMessage());
			}
			model.addAttribute("backUrl", backUrl);
			model.addAttribute("pageIndex", pageIndex);
			return "pay_info/add";
		}
		return showList(pageIndex, model);
	}

	/**
	 * 跳转修改页面
	 * 
	 * @param recordId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=modify", method = RequestMethod.GET)
	public String modify(Long recordId, Long pageIndex, HttpServletRequest request, ModelMap model) {
		PayInfo payInfo = payInfoService.get(recordId);
		List<OssUser> ossUsers = ossUserServic.getAllUsers();
		List<AccountType> accountTypes = accountTypeService.getAccountTypesByUse(AccountType.USE_PLACE_PAY);
		model.addAttribute("payInfo", payInfo);
		model.addAttribute("ossUsers", ossUsers);
		model.addAttribute("accountTypes", accountTypes);
		model.addAttribute("backUrl", getBackUrl(null, request));
		model.addAttribute("pageIndex", pageIndex);
		return "pay_info/modify";
	}

	/**
	 * 修改操作
	 * 
	 * @param backUrl
	 * @param payInfo
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=modify", method = RequestMethod.POST)
	public String modify(String backUrl, Long pageIndex, PayInfo payInfo, BindingResult result, ModelMap model) {
		// 复制修改人
		PayInfo dbPayInfo = payInfoService.get(payInfo.getRecordId());
		BeanUtils.copySpecialProperties(payInfo, dbPayInfo, "creator,createdTime,modifier,modifiedTime");
		model.addAttribute("payInfo", payInfo);
		List<OssUser> ossUsers = ossUserServic.getAllUsers();
		List<AccountType> accountTypes = accountTypeService.getAccountTypesByUse(AccountType.USE_PLACE_PAY);
		model.addAttribute("ossUsers", ossUsers);
		model.addAttribute("accountTypes", accountTypes);
		try {
			payInfoService.validateNotNull(payInfo);
			payInfoService.modify(payInfo);
			model.addAttribute("message", MessageUtils.getInfoValue("SUCC_MODIFY"));
		} catch (OssRollbackCheckedException e) {
			log.error(e);
			if (null != e.getErrors() && e.hasErrors()) {
				ValidatorUtils.convert2SpringError(e.getErrors(), result);
				model.addAttribute("message", MessageUtils.getInfoValue("FAIL_MODIFY"));
			} else {
				model.addAttribute("message", MessageUtils.getInfoValue("FAIL_MODIFY") + "<br>" + e.getMessage());
			}
			model.addAttribute("backUrl", backUrl);
			model.addAttribute("pageIndex", pageIndex);
			return "pay_info/modify";
		}
		return showList(pageIndex, model);
	}

	/**
	 * 查看支出信息
	 * 
	 * @param backUrl
	 * @param recordId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=details", method = RequestMethod.GET)
	public String details(String backUrl, Long recordId, HttpServletRequest request, ModelMap model) {
		PayInfo payInfo = payInfoService.get(recordId);
		model.addAttribute("payInfo", payInfo);
		model.addAttribute("backUrl", getBackUrl(backUrl, request));
		return "pay_info/details";
	}

	/**
	 * 删除 支出信息
	 * 
	 * @param recordId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=delete", method = RequestMethod.GET)
	public String delete(Long pageIndex, Long recordId, ModelMap model) {
		PayInfo payInfo = payInfoService.get(recordId);
		try {
			payInfoService.delete(payInfo);
			model.addAttribute("message", MessageUtils.getInfoValue("SUCC_DELETE"));
		} catch (OssRollbackCheckedException e) {
			log.error(e);
			model.addAttribute("message", MessageUtils.getInfoValue("FAIL_MODIFY"));
		}
		return showList(pageIndex, model);
	}

}
