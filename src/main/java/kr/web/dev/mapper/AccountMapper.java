package kr.web.dev.mapper;

import java.util.List;

import kr.web.dev.model.vo.AccountVO;

public interface AccountMapper {
	
	AccountVO selectAccountByUsername(String userid);
	
	List<AccountVO> selectAccounts();
}
