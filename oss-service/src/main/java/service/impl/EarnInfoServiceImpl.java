package service.impl;

import java.util.Date;
import java.util.List;

import model.EarnInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.EarnInfoService;
import service.OssUserService;

import common.exception.OssErrors;
import common.exception.OssRollbackCheckedException;
import common.support.AbstractServiceSupport;
import common.utils.Assert;
import common.utils.BeanUtils;
import common.utils.MessageUtils;
import common.validation.EntityAnnotationValidator;

import dao.EarnInfoDao;

/**
 * 收入信息接口实现类
 * 
 * @author yanbin
 * 
 */
@Service
@Transactional
public class EarnInfoServiceImpl extends AbstractServiceSupport implements EarnInfoService {

	@Autowired
	private EarnInfoDao<EarnInfo> earnInfoDao;
	@Autowired
	private OssUserService ossUserService;

	@Transactional(readOnly = true)
	public List<EarnInfo> getShowList(Long pageIndex) {
		initPage(pageIndex);
		List<EarnInfo> earnInfos = earnInfoDao.selectPayInfos(startIndex, endIndex);
		return earnInfos;
	}

	@Transactional(readOnly = true)
	public Long getCount() {
		return earnInfoDao.selectCount();
	}

	@Transactional(readOnly = true)
	public Long getLastPage(Long count) {
		return dealLastPage(count);
	}

	@Transactional(readOnly = true)
	public EarnInfo get(Long recordId) {
		if (null == recordId) {
			throw new IllegalArgumentException(MessageUtils.getExceptionValue("ID_NOT_EXISTS"));
		}
		EarnInfo earnInfo = earnInfoDao.select(recordId);
		if (null == earnInfo) {
			throw new IllegalArgumentException(MessageUtils.getExceptionValue("ID_NOT_EXISTS"));
		}
		return earnInfo;
	}

	@Override
	public void add(EarnInfo earnInfo) throws OssRollbackCheckedException {
		Assert.notNull(earnInfo);
		initAdd(earnInfo);
		initModify(earnInfo);
		earnInfoDao.insert(earnInfo);
	}

	public void modify(EarnInfo earnInfo) throws OssRollbackCheckedException {
		Assert.notNull(earnInfo);
		EarnInfo dbEarnInfo = get(earnInfo.getRecordId());
		BeanUtils.copyAllProperties(dbEarnInfo, earnInfo);
		initModify(dbEarnInfo);
		earnInfoDao.insertOrUpdate(dbEarnInfo);
	}

	public void delete(EarnInfo earnInfo) throws OssRollbackCheckedException {
		Assert.notNull(earnInfo);
		earnInfoDao.delete(earnInfo);
	}

	@Transactional(readOnly = true)
	public void validateNotNull(EarnInfo earnInfo) throws OssRollbackCheckedException {
		Assert.notNull(earnInfo);
		OssErrors errors = EntityAnnotationValidator.validate(earnInfo);
		if (null != errors && errors.hasErrors()) {
			throw new OssRollbackCheckedException(errors);
		}
	}

	/**
	 * 初始化添加人
	 * 
	 * @param earnInfo
	 * @throws OssRollbackCheckedException
	 */
	private void initAdd(EarnInfo earnInfo) throws OssRollbackCheckedException {
		Assert.notNull(earnInfo);
		earnInfo.setCreator(ossUserService.get(1l));
		earnInfo.setCreatedTime(new Date());
	}

	/**
	 * 初始化修改人
	 * 
	 * @param earnInfo
	 * @throws OssRollbackCheckedException
	 */
	private void initModify(EarnInfo earnInfo) throws OssRollbackCheckedException {
		Assert.notNull(earnInfo);
		earnInfo.setModifier(ossUserService.get(1l));
		earnInfo.setModifiedTime(new Date());
	}

}
