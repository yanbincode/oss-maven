package web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.AccountType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.AccountTypeService;

import common.exception.OssRollbackCheckedException;
import common.support.AbstractControllerSupport;
import common.utils.BeanUtils;
import common.utils.MessageUtils;
import common.validation.ValidatorUtils;

@Controller
@RequestMapping("/accountType.do")
public class AccountTypeController extends AbstractControllerSupport {

	@Autowired
	private AccountTypeService accTypeService;

	/**
	 * 显示列表信息
	 * 
	 * @param pageIndex
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=show", method = RequestMethod.GET)
	public String showList(Long pageIndex, ModelMap model) {
		List<AccountType> accountTypes = accTypeService.getShowList(pageIndex);
		Long count = accTypeService.getCount(pageIndex);
		model.addAttribute("accountTypes", accountTypes);
		model.addAttribute("count", count);
		model.addAttribute("lastPage", accTypeService.getLastPage(count));
		model.addAttribute("pageIndex", pageIndex);
		return "account_type/list";
	}

	/**
	 * 进入添加页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=add", method = RequestMethod.GET)
	public String add(Long pageIndex, HttpServletRequest request, ModelMap model) {
		AccountType accountType = new AccountType();
		model.addAttribute("accountType", accountType);
		model.addAttribute("backUrl", getBackUrl(null, request));
		model.addAttribute("pageIndex", pageIndex);
		return "account_type/add";
	}

	/**
	 * 进行添加操作
	 * 
	 * @param backUrl
	 * @param pageIndex
	 * @param accountType
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=add", method = RequestMethod.POST)
	public String add(String backUrl, Long pageIndex, AccountType accountType, BindingResult result, ModelMap model) {
		model.addAttribute("accountType", accountType);
		try {
			accTypeService.validateNotNull(accountType);
			accTypeService.add(accountType);
			model.addAttribute("message", MessageUtils.getInfoValue("SUCC_ADD"));
		} catch (OssRollbackCheckedException e) {
			log.error(e);
			if (null != e.getErrors() && e.getErrors().hasErrors()) {
				ValidatorUtils.convert2SpringError(e.getErrors(), result);
				model.addAttribute("message", MessageUtils.getInfoValue("FAIL_ADD"));
			} else {
				model.addAttribute("message", MessageUtils.getInfoValue("FAIL_ADD") + "<br>" + e.getMessage());
			}
			return "account_type/add";
		}
		model.addAttribute("backUrl", backUrl);
		model.addAttribute("pageIndex", pageIndex);
		return showList(pageIndex, model);
	}

	/**
	 * 进入修改页面
	 * 
	 * @param backUrl
	 * @param recordId
	 * @param pageIndex
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=modify", method = RequestMethod.GET)
	public String modify(String backUrl, Long recordId, Long pageIndex, HttpServletRequest request, ModelMap model) {
		AccountType accountType = accTypeService.get(recordId);
		model.addAttribute("accountType", accountType);
		model.addAttribute("backUrl", getBackUrl(backUrl, request));
		model.addAttribute("pageIndex", pageIndex);
		return "account_type/modify";
	}

	/**
	 * 修改操作
	 * 
	 * @param backUrl
	 * @param pageIndex
	 * @param accountType
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=modify", method = RequestMethod.POST)
	public String modify(String backUrl, Long pageIndex, AccountType accountType, BindingResult result, ModelMap model) {
		AccountType dbAccountType = accTypeService.get(accountType.getRecordId());
		BeanUtils.copySpecialProperties(accountType, dbAccountType, "creator,createdTime,modifier,modifiedTime");
		model.addAttribute("accountType", accountType);
		try {
			accTypeService.validateNotNull(accountType);
			accTypeService.modify(accountType);
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
			return "account_type/modify";
		}
		return showList(pageIndex, model);
	}

	/**
	 * 进入查看页面
	 * 
	 * @param recordId
	 * @param pageIndex
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=details", method = RequestMethod.GET)
	public String details(Long recordId, ModelMap model, HttpServletRequest request) {
		AccountType accountType = accTypeService.get(recordId);
		model.addAttribute("accountType", accountType);
		model.addAttribute("backUrl", getBackUrl(null, request));
		return "account_type/details";
	}

	/**
	 * 删除操作
	 * 
	 * @param recordId
	 * @param pageIndex
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=delete", method = RequestMethod.GET)
	public String delete(Long recordId, Long pageIndex, ModelMap model) {
		AccountType accountType = accTypeService.get(recordId);
		try {
			accTypeService.delete(accountType);
			model.addAttribute("message", MessageUtils.getInfoValue("SUCC_DELETE"));
		} catch (OssRollbackCheckedException e) {
			log.error(e);
			model.addAttribute("message", MessageUtils.getInfoValue("FAIL_MODIFY"));
		}
		return showList(pageIndex, model);
	}

}
