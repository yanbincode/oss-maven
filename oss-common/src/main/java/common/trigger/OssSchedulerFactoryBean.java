package common.trigger;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import common.trigger.annotation.TriggerMethod;
import common.trigger.annotation.TriggerType;

/**
 * 解析日终类
 * 
 * @author yanbin
 * 
 */
public class OssSchedulerFactoryBean extends SchedulerFactoryBean {

	/** 日志 */
	protected Log log = LogFactory.getLog(OssSchedulerFactoryBean.class.getName());

	/** Spring 上下文 */
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void registerJobsAndTriggers() throws SchedulerException {
		try {
			String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
			for (String beanName : beanNames) {
				Class<?> targetClass = applicationContext.getType(beanName);
				if (targetClass.isAnnotationPresent(TriggerType.class)) {
					Object targetObject = applicationContext.getBean(beanName);
					TriggerType triggerType = (TriggerType) targetClass.getAnnotation(TriggerType.class);
					String cronExpression = triggerType.cronExpression();
					String targetMethod = "";

					Method[] methods = targetClass.getDeclaredMethods();
					for (Method method : methods) {
						if (method.isAnnotationPresent(TriggerMethod.class)) {
							targetMethod = method.getName();
						}
					}
					registerJobs(targetObject, targetMethod, beanName, cronExpression);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注册定时器
	 * 
	 * @param targetObject
	 * @param targetMethod
	 * @param beanName
	 * @param cronExpression
	 * @throws Exception
	 */
	private void registerJobs(Object targetObject, String targetMethod, String beanName, String cronExpression)
			throws Exception {
		// 声明包装业务类
		MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
		jobDetailFactoryBean.setTargetObject(targetObject);
		jobDetailFactoryBean.setTargetMethod(targetMethod);
		jobDetailFactoryBean.setBeanName(beanName);
		jobDetailFactoryBean.afterPropertiesSet();

		// 获取JobDetail
		JobDetail jobDetail = jobDetailFactoryBean.getObject();

		// 声明定时器
		CronTriggerBean cronTriggerBean = new CronTriggerBean();
		cronTriggerBean.setJobDetail(jobDetail);
		cronTriggerBean.setCronExpression(cronExpression);
		cronTriggerBean.setBeanName(beanName + "CronBean");
		cronTriggerBean.afterPropertiesSet();

		// 将定时器注册到factroy
		List<Trigger> triggerList = new ArrayList<Trigger>();
		triggerList.add(cronTriggerBean);
		Trigger[] triggers = (Trigger[]) triggerList.toArray(new Trigger[triggerList.size()]);
		setTriggers(triggers);
		super.registerJobsAndTriggers();
	}

}
