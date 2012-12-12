package web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.YearRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.YearRecordService;

import common.exception.OssRollbackCheckedException;
import common.support.AbstractControllerSupport;
import common.utils.BeanUtils;
import common.utils.MessageUtils;
import common.validation.ValidatorUtils;

/**
 * 年报信息控制器
 * 
 * @author yanbin
 * 
 */
@Controller
@RequestMapping("/yearRecord.do")
public class YearRecordController extends AbstractControllerSupport {

	@Autowired
	private YearRecordService yearRecordService;

	/**
	 * 显示列表
	 * 
	 * @param pageIndex
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=show", method = RequestMethod.GET)
	public String showList(Long pageIndex, ModelMap model) {
		List<YearRecord> yearRecords = yearRecordService.getShowList(pageIndex);
		Long count = yearRecordService.getCount();
		Long lastPageIndex = yearRecordService.getLastPage(count);
		model.addAttribute("yearRecords", yearRecords);
		model.addAttribute("count", count);
		model.addAttribute("lastPageIndex", lastPageIndex);
		model.addAttribute("pageIndex", pageIndex);
		return "year_record/list";
	}

	/**
	 * 添加操作
	 * 
	 * @param dateTime
	 * @param pageIndex
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=add", method = RequestMethod.GET)
	public String add(Date dateTime, Long pageIndex, HttpServletRequest request, ModelMap model) {
		YearRecord yearRecord = yearRecordService.getByYearTime(dateTime);
		if (null != yearRecord) {
			model.addAttribute("message", MessageUtils.getExceptionValue("IS_EXISITS"));
			return showList(pageIndex, model);
		}
		try {
			yearRecordService.add(dateTime);
			model.addAttribute("message", MessageUtils.getInfoValue("SUCC_OPER"));
		} catch (OssRollbackCheckedException e) {
			log.equals(e);
			model.addAttribute("message", MessageUtils.getInfoValue("FAIL_OPER"));
		}
		return showList(pageIndex, model);
	}

	/**
	 * 重新计算
	 * 
	 * @param recordId
	 * @param pageIndex
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=reCount", method = RequestMethod.GET)
	public String reCount(Long recordId, Long pageIndex, ModelMap model) {
		YearRecord yearRecord = yearRecordService.get(recordId);
		try {
			yearRecordService.reStatistics(yearRecord.getYearTime());
			model.addAttribute("message", MessageUtils.getInfoValue("SUCC_MODIFY"));
		} catch (OssRollbackCheckedException e) {
			log.error(e);
			model.addAttribute("message", MessageUtils.getInfoValue("FAIL_MODIFY"));
		}
		return showList(pageIndex, model);
	}

	/**
	 * 进入修改操作
	 * 
	 * @param recordId
	 * @param pageIndex
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=modify", method = RequestMethod.GET)
	public String modify(Long recordId, Long pageIndex, ModelMap model, HttpServletRequest request) {
		YearRecord yearRecord = yearRecordService.get(recordId);
		model.addAttribute("yearRecord", yearRecord);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("backUrl", getBackUrl(null, request));
		return "year_record/modify";
	}

	/**
	 * 修改操作
	 * 
	 * @param pageIndex
	 * @param backUrl
	 * @param yearRecord
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=modify", method = RequestMethod.POST)
	public String modify(Long pageIndex, String backUrl, YearRecord yearRecord, BindingResult result, ModelMap model) {
		YearRecord dbYearRecord = yearRecordService.get(yearRecord.getRecordId());
		BeanUtils.copySpecialProperties(yearRecord, dbYearRecord, "creator,createdTime,modifier,modifiedTime");
		model.addAttribute("yearRecord", yearRecord);
		try {
			yearRecordService.validateNotNull(yearRecord);
			yearRecordService.modify(yearRecord);
		} catch (OssRollbackCheckedException e) {
			log.error(e);
			if (null == e.getErrors() && e.getErrors().hasErrors()) {
				ValidatorUtils.convert2SpringError(e.getErrors(), result);
				model.addAttribute("message", MessageUtils.getInfoValue("FAIL_MODIFY"));
			} else {
				model.addAttribute("message", MessageUtils.getInfoValue("FAIL_MODIFY") + "<br>" + e.getMessage());
			}
			model.addAttribute("backUrl", backUrl);
			model.addAttribute("pageIndex", pageIndex);
			return "year_record/modify";
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
		YearRecord yearRecord = yearRecordService.get(recordId);
		try {
			yearRecordService.delete(yearRecord);
			model.addAttribute("message", MessageUtils.getInfoValue("SUCC_DELETE"));
		} catch (OssRollbackCheckedException e) {
			log.error(e);
			model.addAttribute("message", MessageUtils.getInfoValue("FAIL_MODIFY"));
		}
		return showList(pageIndex, model);
	}

	/**
	 * 查看
	 * 
	 * @param recordId
	 * @param backUrl
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=details", method = RequestMethod.GET)
	public String details(Long recordId, String backUrl, HttpServletRequest request, ModelMap model) {
		YearRecord yearRecord = yearRecordService.get(recordId);
		model.addAttribute("yearRecord", yearRecord);
		model.addAttribute("backUrl", getBackUrl(backUrl, request));
		return "year_record/details";
	}

}
