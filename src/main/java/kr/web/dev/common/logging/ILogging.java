package kr.web.dev.common.logging;

import kr.web.dev.model.vo.AuditLogVO;

public interface ILogging {

	public void addLog(AuditLogVO vo);
	
}
