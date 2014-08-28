package kr.web.dev.controller.system;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.web.dev.model.vo.AccountVO;
import kr.web.dev.service.system.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/account")
public class AccountController {
	
	private static final Logger	logger	= LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	MessageSource				messageSource;
	@Autowired
	AccountService				accountService;
	
	@RequestMapping(value = "/selectAccounts", method = RequestMethod.POST)
	@ResponseBody
	public List<AccountVO> selectAccounts(HttpSession session) {
		logger.debug("AccountController selectAccounts method");
		List<AccountVO> accounts = accountService.selectAccounts();
		return accounts;
	}
	
	@RequestMapping(value = "/selectAccountByUsername", method = RequestMethod.POST)
	@ResponseBody
	public AccountVO selectAccountByUsername(@RequestBody AccountVO vo, BindingResult result,
			HttpSession session) {
		logger.debug("AccountController selectAccounts method");
		AccountVO account = accountService.selectAccountByUsername(vo.getUsername());
		return account;
	}
}
