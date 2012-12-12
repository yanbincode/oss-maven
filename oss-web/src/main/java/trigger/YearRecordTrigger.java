package trigger;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import service.YearRecordService;

import common.exception.OssRollbackCheckedException;
import common.support.AbstractTriggerSupport;
import common.trigger.annotation.TriggerMethod;
import common.trigger.annotation.TriggerType;

/**
 * 年报统计日终
 * 
 * @author yanbin
 * 
 */
@TriggerType(cronExpression = "0 0 21 1 * ?")
public class YearRecordTrigger extends AbstractTriggerSupport {

	@Autowired
	private YearRecordService yearRecordService;

	@TriggerMethod
	public void execute() {
		try {
			yearRecordService.add(new Date());
		} catch (OssRollbackCheckedException e) {
			log.error(e);
		}
	}

}
