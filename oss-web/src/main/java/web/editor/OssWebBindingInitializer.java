package web.editor;

import java.util.Date;

import model.AccountType;
import model.OssUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import service.AccountTypeService;
import service.OssUserService;

public class OssWebBindingInitializer implements WebBindingInitializer {

	@Autowired
	private OssUserService ossUserService;
	@Autowired
	private AccountTypeService accountTypeService;

	/**
	 * 注入属性编辑器
	 */
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(OssUser.class, new OssUserEditor(ossUserService));
		binder.registerCustomEditor(AccountType.class, new AccountTypeEditor(accountTypeService));
	}

}
