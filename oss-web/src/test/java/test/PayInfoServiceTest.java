package test;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Assert;
import model.PayInfo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import service.PayInfoService;
import base.AbstractOssServiceTest;

import common.exception.OssRollbackCheckedException;

@ContextConfiguration(locations = "classpath:test_context/pay_info_service_test_context.xml")
public class PayInfoServiceTest extends AbstractOssServiceTest {

	@Autowired
	private PayInfoService payInfoService;

	@Before
	public void initData() {

	}

	public void testAdd() {
		try {
			payInfoService.add(null);
			Assert.fail("添加为空没有抛出异常");
		} catch (OssRollbackCheckedException e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testList() {

		PayInfo payInfo = new PayInfo();
		payInfo.setRecordId(2000l);
		payInfo.setPayContent("yanbinceshi");
		payInfo.setPayNumber(new BigDecimal(199));
		payInfo.setDayTime(new Date());
		payInfo.setCreator(null);

		try {
			payInfoService.modify(payInfo);
		} catch (OssRollbackCheckedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getShowList() {
		payInfoService.getShowList(1l);
	}

}
