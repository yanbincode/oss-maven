package common.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * service Impl 层的支撑类
 * 
 * @author yanbin
 * 
 */
public abstract class AbstractServiceSupport {

	protected Log log = LogFactory.getLog(AbstractServiceSupport.class.getName());

	/** 分页起始 */
	protected Long startIndex;
	/** 分页结束 */
	protected Long endIndex;
	/** 每页条数 */
	protected Long pageSize = 15l;

	/**
	 * 计算分页
	 * 
	 * @param pageIndex
	 */
	protected void initPage(Long pageIndex) {
		// 获取开始条目
		startIndex = (pageIndex - 1) * pageSize;
		endIndex = startIndex + pageSize;
	}

	/**
	 * 处理尾页<br>
	 * 返回计算最后一页的页码
	 * 
	 * @param count
	 *            数据总条数
	 * 
	 * @return
	 */
	protected Long dealLastPage(Long count) {
		if (count % pageSize == 0) {
			return count / pageSize - 1;
		}
		return count / pageSize;
	}

}
