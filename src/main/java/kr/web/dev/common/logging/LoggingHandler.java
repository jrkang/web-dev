package kr.web.dev.common.logging;

import kr.web.dev.model.vo.AuditLogVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingHandler {

	private static final Logger	logger	= LoggerFactory.getLogger(LoggingHandler.class);
	
	private ILogging logging;

	public void setLogging(ILogging logging) {
		this.logging = logging;
	}

	public LoggingHandler(){
		logger.debug("Constructor call");
	}
	
	public void callAddLog(AuditLogVO auditLogVO) {
		logging.addLog(auditLogVO);
	}
	
}
