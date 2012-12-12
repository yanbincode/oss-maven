package web.editor;

import java.beans.PropertyEditorSupport;

import model.AccountType;
import service.AccountTypeService;

import common.utils.StringUtils;

/**
 * 记录类型对象类型转换器
 * 
 * @author yanbin
 * 
 */
public class AccountTypeEditor extends PropertyEditorSupport {

	/** 调用对象业务接口 */
	private AccountTypeService accountTypeService;

	public AccountTypeEditor(AccountTypeService accountTypeService) {
		this.accountTypeService = accountTypeService;
	}

	@Override
	public String getAsText() {
		AccountType accountType = (AccountType) getValue();
		if (null == accountType) {
			return null;
		}
		return accountType.getRecordId().toString();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isEmpty(text)) {
			setValue(null);
			return;
		}
		AccountType accountType = accountTypeService.get(Long.valueOf(text));
		setValue(accountType);
	}

}
