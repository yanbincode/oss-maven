package dao.impl;

import java.util.Date;
import java.util.List;

import model.OssUser;
import model.YearRecord;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import common.support.OssHibernateDaoSupport;
import common.utils.MessageUtils;

import dao.YearRecordDao;

/**
 * 年报dao实现类
 * 
 * @author yanbin
 * 
 */
@Repository
public class YearRecordDaoImpl extends OssHibernateDaoSupport<YearRecord> implements YearRecordDao<YearRecord> {

	public void statisticsYear(Date dateTime) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO YEAR_RECORD (RECORD_ID, YEAR_TIME, YEAR_EARN_COUNT, YEAR_PAY_COUNT, ");
		sql.append("YEAR_COUNT, REMARK, CREATOR_ID, CREATED_TIME, MODIFIER_ID, MODIFIED_TIME)");
		sql.append("VALUES ");
		sql.append("(S_YEAR_RECORD.NEXTVAL, TO_DATE(TO_CHAR(?, 'YYYY'), 'YYYY'), ");
		sql.append("(SELECT NVL(SUM(I.EARN_NUMBER), 0) FROM EARN_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY') = TO_CHAR(?, 'YYYY')), ");
		sql.append("(SELECT NVL(SUM(I.PAY_NUMBER), 0) FROM PAY_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY') = TO_CHAR(?, 'YYYY')), ");
		sql.append("((SELECT NVL(SUM(I.EARN_NUMBER), 0) FROM EARN_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY') = TO_CHAR(?, 'YYYY')) - ");
		sql.append("(SELECT NVL(SUM(I.PAY_NUMBER), 0) FROM PAY_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY') = TO_CHAR(?, 'YYYY'))), ");
		sql.append("'" + MessageUtils.getInfoValue("AUTO_CREATE") + "', ");
		sql.append(OssUser.SYSTEM_ADMIN_ID + ", SYSDATE, " + OssUser.SYSTEM_ADMIN_ID + ", SYSDATE) ");

		getCurrentSession().createSQLQuery(sql.toString()).setDate(0, dateTime).setDate(1, dateTime)
				.setDate(2, dateTime).setDate(3, dateTime).setDate(4, dateTime).executeUpdate();
	}

	public void reStatisticsYear(Date dateTime) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE YEAR_RECORD ");
		sql.append("SET YEAR_EARN_COUNT = ");
		sql.append("(SELECT NVL(SUM(I.EARN_NUMBER), 0) FROM EARN_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY') = TO_CHAR(?, 'YYYY')), ");
		sql.append("YEAR_PAY_COUNT = ");
		sql.append("(SELECT NVL(SUM(I.PAY_NUMBER), 0) FROM PAY_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY') = TO_CHAR(?, 'YYYY')), ");
		sql.append("YEAR_COUNT = ");
		sql.append("(SELECT (SELECT NVL(SUM(I.EARN_NUMBER), 0) FROM EARN_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY') = TO_CHAR(?, 'YYYY')) - ");
		sql.append("(SELECT NVL(SUM(I.PAY_NUMBER), 0) FROM PAY_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY') = TO_CHAR(?, 'YYYY')) FROM DUAL), ");
		sql.append("MODIFIER_ID = " + OssUser.SYSTEM_ADMIN_ID + ", ");
		sql.append("MODIFIED_TIME = TRUNC(SYSDATE) ");
		sql.append("WHERE TO_CHAR(YEAR_TIME, 'YYYY')  = TO_CHAR(?, 'YYYY') ");

		getCurrentSession().createSQLQuery(sql.toString()).setDate(0, dateTime).setDate(1, dateTime)
				.setDate(2, dateTime).setDate(3, dateTime).setDate(4, dateTime).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<YearRecord> selectYearRecords(Long startIndex, Long endIndex) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select tn.*, rownum as rn from (select * from year_record ");
		sql.append("order by year_time desc) tn where rownum <= ?) where rn > ? ");
		Query query = getCurrentSession().createSQLQuery(sql.toString()).addEntity(YearRecord.class)
				.setLong(0, endIndex).setLong(1, startIndex);
		return query.list();
	}

	public Long selectCount() {
		String sql = "select count(1) from year_record";
		Object object = getCurrentSession().createSQLQuery(sql).uniqueResult();
		if (null != object) {
			return Long.parseLong(object.toString());
		}
		return 0l;
	}

	public YearRecord selectByYearTime(Date dateTime) {
		String sql = "select * from year_record r where to_char(r.year_time,'yyyy') = to_char(?,'yyyy')";
		Object object = getCurrentSession().createSQLQuery(sql).addEntity(YearRecord.class).setDate(0, dateTime)
				.uniqueResult();
		return (YearRecord) object;
	}

}
