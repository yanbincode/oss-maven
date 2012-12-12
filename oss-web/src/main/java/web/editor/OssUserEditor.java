package web.editor;

import java.beans.PropertyEditorSupport;

import model.OssUser;

import org.apache.commons.lang.StringUtils;

import service.OssUserService;

/**
 * 人员类型转换器
 * 
 * @author yanbin
 * 
 */
public class OssUserEditor extends PropertyEditorSupport {

	/** ossUser的service接口 */
	private OssUserService ossUserService;

	/**
	 * 构造方法进行对 似有变量赋值
	 * 
	 * @param ossUserService
	 */
	public OssUserEditor(OssUserService ossUserService) {
		this.ossUserService = ossUserService;
	}

	@Override
	public String getAsText() {
		OssUser ossUser = (OssUser) getValue();
		if (null == ossUser) {
			return null;
		}
		return ossUser.getUserId().toString();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isEmpty(text)) {
			setValue(null);
			return;
		}
		OssUser ossUser = ossUserService.get(Long.valueOf(text));
		setValue(ossUser);
	}

}
