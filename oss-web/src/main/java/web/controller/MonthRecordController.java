package web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.MonthRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.MonthRecordService;

import common.exception.OssRollbackCheckedException;
import common.support.AbstractControllerSupport;
import common.utils.BeanUtils;
import common.utils.MessageUtils;
import common.validation.ValidatorUtils;

/**
 * 月统计控制层
 * 
 * @author yanbin
 * 
 */
@Controller
@RequestMapping("/monthRecord.do")
public class MonthRecordController extends AbstractControllerSupport {

	@Autowired
	private MonthRecordService monthRecordService;

	/**
	 * 显示月统计列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "method=show", method = RequestMethod.GET)
	public String showList(Long pageIndex, ModelMap model) {
		List<MonthRecord> monthRecords = monthRecordService.getShowList(pageIndex);
		Long count = monthRecordService.getCount();
		model.addAttribute("monthRecords", monthRecords);
		model.addAttribute("count", count);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("lastPageIndex", monthRecordService.getLastPage(count));
		return "month_record/list";
	}

	/**
	 * 指定时间进行统计
	 * 
	 * @param dateTime
	 * @param pageIndex
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=add", method = RequestMethod.GET)
	public String add(Date dateTime, Long pageIndex, HttpServletRequest request, ModelMap model) {
		MonthRecord monthRecord = monthRecordService.getByMonthTime(dateTime);
		if (null != monthRecord) {
			model.addAttribute("message", MessageUtils.getExceptionValue("IS_EXISITS"));
			return showList(pageIndex, model);
		}
		try {
			monthRecordService.add(dateTime);
			model.addAttribute("message", MessageUtils.getInfoValue("SUCC_OPER"));
		} catch (OssRollbackCheckedException e) {
			log.equals(e);
			model.addAttribute("message", MessageUtils.getInfoValue("FAIL_OPER"));
		}
		return showList(pageIndex, model);
	}

	/**
	 * 重新统计
	 * 
	 * @param recordId
	 * @param pageIndex
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=reCount", method = RequestMethod.GET)
	public String reCount(Long recordId, Long pageIndex, ModelMap model) {
		MonthRecord monthRecord = monthRecordService.get(recordId);
		try {
			monthRecordService.reStatistics(monthRecord.getMonthTime());
			model.addAttribute("message", MessageUtils.getInfoValue("SUCC_MODIFY"));
		} catch (OssRollbackCheckedException e) {
			log.error(e);
			model.addAttribute("message", MessageUtils.getInfoValue("FAIL_MODIFY"));
		}
		return showList(pageIndex, model);
	}

	/**
	 * 进入修改页面
	 * 
	 * @param pageIndex
	 * @param recordId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=modify", method = RequestMethod.GET)
	public String modify(Long pageIndex, Long recordId, HttpServletRequest request, ModelMap model) {
		MonthRecord monthRecord = monthRecordService.get(recordId);
		model.addAttribute("monthRecord", monthRecord);
		model.addAttribute("backUrl", getBackUrl(null, request));
		model.addAttribute("pageIndex", pageIndex);
		return "month_record/modify";
	}

	/**
	 * 修改操作
	 * 
	 * @param pageIndex
	 * @param backUrl
	 * @param monthRecord
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=modify", method = RequestMethod.POST)
	public String modify(Long pageIndex, String backUrl, MonthRecord monthRecord, BindingResult result, ModelMap model) {
		MonthRecord dbMonthRecord = monthRecordService.get(monthRecord.getRecordId());
		BeanUtils.copySpecialProperties(monthRecord, dbMonthRecord, "creator,createdTime,modifier,modifiedTime");
		model.addAttribute("monthRecord", monthRecord);
		try {
			monthRecordService.validateNotNull(monthRecord);
			monthRecordService.modify(monthRecord);
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
			return "month_record/modify";
		}
		return showList(pageIndex, model);
	}

	/**
	 * 删除
	 * 
	 * @param pageIndex
	 * @param record
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=delete", method = RequestMethod.GET)
	public String delete(Long pageIndex, Long recordId, ModelMap model) {
		MonthRecord monthRecord = monthRecordService.get(recordId);
		try {
			monthRecordService.delete(monthRecord);
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
		MonthRecord monthRecord = monthRecordService.get(recordId);
		model.addAttribute("monthRecord", monthRecord);
		model.addAttribute("backUrl", getBackUrl(backUrl, request));
		return "month_record/details";
	}

}
