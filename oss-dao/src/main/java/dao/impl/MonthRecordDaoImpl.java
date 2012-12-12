package dao.impl;

import java.util.Date;
import java.util.List;

import model.MonthRecord;
import model.OssUser;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import common.support.OssHibernateDaoSupport;
import common.utils.MessageUtils;

import dao.MonthRecordDao;

/**
 * 月报记录dao实现类
 * 
 * @author yanbin
 * 
 */
@Repository
public class MonthRecordDaoImpl extends OssHibernateDaoSupport<MonthRecord> implements MonthRecordDao<MonthRecord> {

	public void statisticsMonth(Date dateTime) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO MONTH_RECORD (RECORD_ID, MONTH_TIME, MONTH_EARN_COUNT, ");
		sql.append("MONTH_PAY_COUNT, MONTH_COUNT, REMARK, CREATOR_ID, CREATED_TIME, MODIFIER_ID, MODIFIED_TIME) ");
		sql.append("VALUES ");
		sql.append("(S_MONTH_RECORD.NEXTVAL, TO_DATE(TO_CHAR(?, 'YYYY-MM'), 'YYYY-MM'), ");
		sql.append("(SELECT NVL(SUM(I.EARN_NUMBER),0) FROM EARN_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY-MM') = TO_CHAR(?, 'YYYY-MM')), ");
		sql.append("(SELECT NVL(SUM(I.PAY_NUMBER),0) FROM PAY_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY-MM') = TO_CHAR(?, 'YYYY-MM')), ");
		sql.append("((SELECT NVL(SUM(I.EARN_NUMBER),0) FROM EARN_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY-MM') = TO_CHAR(?, 'YYYY-MM')) - ");
		sql.append("(SELECT NVL(SUM(I.PAY_NUMBER),0) FROM PAY_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY-MM') = TO_CHAR(?, 'YYYY-MM'))), ");
		sql.append("'" + MessageUtils.getInfoValue("AUTO_CREATE") + "', ");
		sql.append(OssUser.SYSTEM_ADMIN_ID + ", SYSDATE, " + OssUser.SYSTEM_ADMIN_ID + ", SYSDATE) ");

		getCurrentSession().createSQLQuery(sql.toString()).setDate(0, dateTime).setDate(1, dateTime)
				.setDate(2, dateTime).setDate(3, dateTime).setDate(4, dateTime).executeUpdate();
	}

	public void reStatisticsMonth(Date dateTime) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE MONTH_RECORD ");
		sql.append("SET MONTH_EARN_COUNT = ");
		sql.append("(SELECT NVL(SUM(I.EARN_NUMBER), 0) FROM EARN_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY-MM') = TO_CHAR(?, 'YYYY-MM')), ");
		sql.append("MONTH_PAY_COUNT = ");
		sql.append("(SELECT NVL(SUM(I.PAY_NUMBER), 0) FROM PAY_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY-MM') = TO_CHAR(?, 'YYYY-MM')), ");
		sql.append("MONTH_COUNT = ");
		sql.append("(SELECT (SELECT NVL(SUM(I.EARN_NUMBER), 0) FROM EARN_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY-MM') = TO_CHAR(?, 'YYYY-MM')) - ");
		sql.append("(SELECT NVL(SUM(I.PAY_NUMBER), 0) FROM PAY_INFO I ");
		sql.append("WHERE TO_CHAR(I.DAY_TIME, 'YYYY-MM') = TO_CHAR(?, 'YYYY-MM')) FROM DUAL), ");
		sql.append("MODIFIER_ID = " + OssUser.SYSTEM_ADMIN_ID + ", ");
		sql.append("MODIFIED_TIME = TRUNC(SYSDATE) ");
		sql.append("WHERE TO_CHAR(MONTH_TIME, 'YYYY-MM')  = TO_CHAR(?, 'YYYY-MM') ");

		getCurrentSession().createSQLQuery(sql.toString()).setDate(0, dateTime).setDate(1, dateTime)
				.setDate(2, dateTime).setDate(3, dateTime).setDate(4, dateTime).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<MonthRecord> selectMonthRecords(Long startIndex, Long endIndex) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select tn.*, rownum as rn from (select * from month_record ");
		sql.append("order by month_time desc) tn where rownum <= ?) where rn > ? ");
		Query query = getCurrentSession().createSQLQuery(sql.toString()).addEntity(MonthRecord.class)
				.setLong(0, endIndex).setLong(1, startIndex);
		return query.list();
	}

	public Long selectCount() {
		String sql = "select count(1) from month_record";
		Object object = getCurrentSession().createSQLQuery(sql).uniqueResult();
		if (null != object) {
			return Long.parseLong(object.toString());
		}
		return 0l;
	}

	public MonthRecord selectByMonthTime(Date dateTime) {
		String sql = "select * from month_record r where to_char(r.month_time,'yyyy-mm') = to_char(?,'yyyy-mm')";
		Object object = getCurrentSession().createSQLQuery(sql).addEntity(MonthRecord.class).setDate(0, dateTime)
				.uniqueResult();
		return (MonthRecord) object;
	}

}
