package test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import service.MonthRecordService;
import base.AbstractOssServiceTest;

import common.exception.OssRollbackCheckedException;

@ContextConfiguration(locations = "classpath:test_context/month_record_service_test_context.xml")
public class MonthRecordServiceTest extends AbstractOssServiceTest {

	@Autowired
	private MonthRecordService monthRecordService;

	@Before
	public void initData() {

	}

	@Test
	public void testAdd() {
		Date dateTime = new Date();
		try {
			monthRecordService.add(dateTime);
		} catch (OssRollbackCheckedException e) {
			e.printStackTrace();
		}
	}

}
