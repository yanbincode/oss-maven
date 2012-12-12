package service.impl;

import java.util.Date;
import java.util.List;

import model.MonthRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import service.MonthRecordService;
import service.OssUserService;

import common.exception.OssErrors;
import common.exception.OssRollbackCheckedException;
import common.support.AbstractServiceSupport;
import common.utils.BeanUtils;
import common.utils.MessageUtils;
import common.validation.EntityAnnotationValidator;

import dao.MonthRecordDao;

/**
 * 月报service接口实现类
 * 
 * @author yanbin
 * 
 */
@Service
@Transactional
public class MonthRecordServiceImpl extends AbstractServiceSupport implements MonthRecordService {

	@Autowired
	private MonthRecordDao<MonthRecord> monthRecordDao;
	@Autowired
	private OssUserService ossUserService;

	@Transactional(readOnly = true)
	public List<MonthRecord> getShowList(Long pageIndex) {
		initPage(pageIndex);
		return monthRecordDao.selectMonthRecords(startIndex, endIndex);
	}

	@Transactional(readOnly = true)
	public Long getCount() {
		return monthRecordDao.selectCount();
	}

	@Transactional(readOnly = true)
	public Long getLastPage(Long count) {
		return dealLastPage(count);
	}

	public void add(Date dateTime) throws OssRollbackCheckedException {
		Assert.notNull(dateTime);
		monthRecordDao.statisticsMonth(dateTime);
	}

	public void reStatistics(Date dateTime) throws OssRollbackCheckedException {
		Assert.notNull(dateTime);
		monthRecordDao.reStatisticsMonth(dateTime);
	}

	@Transactional(readOnly = true)
	public MonthRecord get(Long recordId) {
		if (null == recordId) {
			throw new IllegalArgumentException(MessageUtils.getExceptionValue("ID_NOT_EXISTS"));
		}
		MonthRecord monthRecord = monthRecordDao.select(recordId);
		if (null == monthRecord) {
			throw new IllegalArgumentException(MessageUtils.getExceptionValue("ID_NOT_EXISTS"));
		}
		return monthRecord;
	}

	@Transactional(readOnly = true)
	public MonthRecord getByMonthTime(Date dateTime) {
		if (null == dateTime) {
			throw new IllegalArgumentException("arguments is null");
		}
		return monthRecordDao.selectByMonthTime(dateTime);
	}

	public void modify(MonthRecord monthRecord) throws OssRollbackCheckedException {
		Assert.notNull(monthRecord);
		MonthRecord dbMonthRecord = get(monthRecord.getRecordId());
		BeanUtils.copyAllProperties(dbMonthRecord, monthRecord);
		initModify(dbMonthRecord);
		monthRecordDao.insertOrUpdate(dbMonthRecord);
	}

	public void delete(MonthRecord monthRecord) throws OssRollbackCheckedException {
		Assert.notNull(monthRecord);
		monthRecordDao.delete(monthRecord);
	}

	@Transactional(readOnly = true)
	public void validateNotNull(MonthRecord monthRecord) throws OssRollbackCheckedException {
		Assert.notNull(monthRecord);
		OssErrors errors = EntityAnnotationValidator.validate(monthRecord);
		if (null != errors && errors.hasErrors()) {
			throw new OssRollbackCheckedException(errors);
		}
	}

	/**
	 * 初始化修改操作
	 * 
	 * @param monthRecord
	 */
	private void initModify(MonthRecord monthRecord) {
		Assert.notNull(monthRecord);
		monthRecord.setModifier(ossUserService.get(1l));
		monthRecord.setModifiedTime(new Date());
	}

}
