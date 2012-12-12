package web.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 时间类型属性编辑器
 * 
 * @author yanbin
 * 
 */
public class DateEditor extends PropertyEditorSupport {

	/** 转换时间类型的格式，可以抽取出来 */
	private String formart = "yyyy-MM-dd";

	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		if (null == value) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(formart);
		return dateFormat.format(value);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isEmpty(text)) {
			setValue(null);
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formart);
		try {
			Date date = sdf.parse(text);
			setValue(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
