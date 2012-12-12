package trigger;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import service.MonthRecordService;

import common.exception.OssRollbackCheckedException;
import common.support.AbstractTriggerSupport;
import common.trigger.annotation.TriggerMethod;
import common.trigger.annotation.TriggerType;

/**
 * 计算月记录的触发器
 * 
 * @author yanbin
 * 
 */
@TriggerType(cronExpression = "0 0 21 1 * ?")
public class MonthRecordTrigger extends AbstractTriggerSupport {

	@Autowired
	private MonthRecordService monthRecordService;

	@TriggerMethod
	public void execute() {
		try {
			monthRecordService.add(new Date());
		} catch (OssRollbackCheckedException e) {
			log.error(e);
		}
	}
	// 可以做后续处理

}
