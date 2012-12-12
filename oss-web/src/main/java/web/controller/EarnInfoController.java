package web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.AccountType;
import model.EarnInfo;
import model.OssUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.AccountTypeService;
import service.EarnInfoService;
import service.OssUserService;

import common.exception.OssRollbackCheckedException;
import common.support.AbstractControllerSupport;
import common.utils.BeanUtils;
import common.utils.MessageUtils;
import common.validation.ValidatorUtils;

@Controller
@RequestMapping("/earnInfo.do")
public class EarnInfoController extends AbstractControllerSupport {

	@Autowired
	private OssUserService ossUserService;
	@Autowired
	private EarnInfoService earnInfoService;
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
		List<EarnInfo> earnInfos = earnInfoService.getShowList(pageIndex);
		model.addAttribute("earnInfos", earnInfos);
		Long count = earnInfoService.getCount();
		model.addAttribute("count", count);
		model.addAttribute("lastIndex", earnInfoService.getLastPage(count));
		model.addAttribute("pageIndex", pageIndex);
		return "earn_info/list";
	}

	/**
	 * 到添加信息页面
	 * 
	 * @param backUrl
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=add", method = RequestMethod.GET)
	public String add(String backUrl, Long pageIndex, ModelMap model, HttpServletRequest request) {
		EarnInfo earnInfo = new EarnInfo();
		List<OssUser> ossUsers = ossUserService.getAllUsers();
		List<AccountType> accountTypes = accountTypeService.getAccountTypesByUse(AccountType.USE_PLACE_EARN);
		model.addAttribute("earnInfo", earnInfo);
		model.addAttribute("backUrl", getBackUrl(backUrl, request));
		model.addAttribute("ossUsers", ossUsers);
		model.addAttribute("accountTypes", accountTypes);
		model.addAttribute("pageIndex", pageIndex);
		return "earn_info/add";
	}

	/**
	 * 添加收入信息操作
	 * 
	 * @param backUrl
	 * @param earnInfo
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=add", method = RequestMethod.POST)
	public String add(String backUrl, Long pageIndex, EarnInfo earnInfo, BindingResult result, ModelMap model) {
		model.addAttribute("earnInfo", earnInfo);
		List<OssUser> ossUsers = ossUserService.getAllUsers();
		List<AccountType> accountTypes = accountTypeService.getAccountTypesByUse(AccountType.USE_PLACE_EARN);
		model.addAttribute("ossUsers", ossUsers);
		model.addAttribute("accountTypes", accountTypes);
		try {
			earnInfoService.validateNotNull(earnInfo);
			earnInfoService.add(earnInfo);
			model.addAttribute("message", MessageUtils.getInfoValue("SUCC_ADD"));
		} catch (OssRollbackCheckedException e) {
			log.error(e);
			if (null != e.getErrors() && e.getErrors().hasErrors()) {
				ValidatorUtils.convert2SpringError(e.getErrors(), result);
				model.addAttribute("message", MessageUtils.getInfoValue("FAIL_ADD"));
			} else {
				model.addAttribute("message", MessageUtils.getInfoValue("FAIL_ADD") + "<br>" + e.getMessage());
			}
			model.addAttribute("backUrl", backUrl);
			model.addAttribute("pageIndex", pageIndex);
			return "earn_info/add";
		}
		return showList(pageIndex, model);
	}

	/**
	 * 进入修改页面
	 * 
	 * @param backUrl
	 * @param pageIndex
	 * @param recordId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=modify", method = RequestMethod.GET)
	public String modify(String backUrl, Long pageIndex, Long recordId, ModelMap model, HttpServletRequest request) {
		model.addAttribute("backUrl", getBackUrl(backUrl, request));
		model.addAttribute("pageIndex", pageIndex);
		List<OssUser> ossUsers = ossUserService.getAllUsers();
		model.addAttribute("ossUsers", ossUsers);
		List<AccountType> accountTypes = accountTypeService.getAccountTypesByUse(AccountType.USE_PLACE_EARN);
		model.addAttribute("accountTypes", accountTypes);
		model.addAttribute("earnInfo", earnInfoService.get(recordId));
		return "earn_info/modify";
	}

	/**
	 * 修改操作
	 * 
	 * @param backUrl
	 * @param pageIndex
	 * @param earnInfo
	 * @param result
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(params = "method=modify", method = RequestMethod.POST)
	public String modify(String backUrl, Long pageIndex, EarnInfo earnInfo, BindingResult result, ModelMap model) {
		EarnInfo dbEarnInfo = earnInfoService.get(earnInfo.getRecordId());
		BeanUtils.copySpecialProperties(earnInfo, dbEarnInfo, "creator,createdTime,modifier,modifiedTime");
		model.addAttribute("earnInfo", earnInfo);
		List<OssUser> ossUsers = ossUserService.getAllUsers();
		List<AccountType> accountTypes = accountTypeService.getAccountTypesByUse(AccountType.USE_PLACE_EARN);
		model.addAttribute("ossUsers", ossUsers);
		model.addAttribute("accountTypes", accountTypes);
		try {
			earnInfoService.validateNotNull(earnInfo);
			earnInfoService.modify(earnInfo);
			model.addAttribute("message", MessageUtils.getInfoValue("SUCC_MODIFY"));
		} catch (OssRollbackCheckedException e) {
			log.error(e);
			if (null != e.getErrors() && e.getErrors().hasErrors()) {
				ValidatorUtils.convert2SpringError(e.getErrors(), result);
				model.addAttribute("message", MessageUtils.getInfoValue("FAIL_MODIFY"));
			} else {
				model.addAttribute("message", MessageUtils.getInfoValue("FAIL_MODIFY") + "<br>" + e.getMessage());
			}
			model.addAttribute("backUrl", backUrl);
			model.addAttribute("pageIndex", pageIndex);
			return "earn_info/modify";
		}
		return showList(pageIndex, model);
	}

	/**
	 * 删除操作
	 * 
	 * @param pageIndex
	 * @param recordId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=delete", method = RequestMethod.GET)
	public String delete(Long pageIndex, Long recordId, ModelMap model) {
		EarnInfo earnInfo = earnInfoService.get(recordId);
		try {
			earnInfoService.delete(earnInfo);
			model.addAttribute("message", MessageUtils.getInfoValue("SUCC_DELETE"));
		} catch (OssRollbackCheckedException e) {
			log.error(e);
			model.addAttribute("message", MessageUtils.getInfoValue("FAIL_DELETE") + "<br>" + e.getMessage());
		}
		return showList(pageIndex, model);
	}

	/**
	 * 查看操作
	 * 
	 * @param backUrl
	 * @param recordId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=details", method = RequestMethod.GET)
	public String details(String backUrl, Long recordId, HttpServletRequest request, ModelMap model) {
		EarnInfo earnInfo = earnInfoService.get(recordId);
		model.addAttribute("earnInfo", earnInfo);
		model.addAttribute("backUrl", getBackUrl(backUrl, request));
		return "earn_info/details";
	}

}
