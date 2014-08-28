package kr.web.dev.common.aop;

import kr.web.dev.common.logging.LoggingHandler;
import kr.web.dev.model.vo.AuditLogVO;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 로깅 관련 Aspect
 * @version 1.0
 * @author jrkang
 */
@Aspect
public class LoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Autowired
	LoggingHandler loggingHandler;
	
    /**
     * Before
     * JoinPoint(메소드)에서 본문이 실행되기 전에 호출된다.   
     * @param JoinPoint jp
     * @return
     * @throws
     * @author jrkang
     * @date 2014-08-04
     */
	@Before("execution(public * kr.web.dev.service..*.*(..))")
	public void logWrite(JoinPoint jp) throws Throwable {
		logger.debug("logWrite called");
		String methodNm = jp.getSignature().getName();
		logger.debug("Target Method : "+methodNm);
		
		//HttpSession httpSession = (HttpSession)jp.getArgs()[1];
		
		AuditLogVO auditLogVO = new AuditLogVO();
		//auditLogVO.setActor(httpSession.getAttribute("Username").toString());
		auditLogVO.setActor("jrkang");
		auditLogVO.setExecuteMethod(methodNm);
		auditLogVO.setMessage("audit log test");
		loggingHandler.callAddLog(auditLogVO);
	}
    
    /**
     * Around
     * JoinPoint(메소드)에서 본문이 실행되기 전과 후에 호출된다.   
     * @param ProceedingJoinPoint joinPoint
     * @return Object
     * @throws
     * @author jrkang
     * @date 2014-08-04
     */
//	@Around("execution(public * kr.web.dev.service..*.*(..))")
//	public Object loggingAround(ProceedingJoinPoint joinPoint) throws Throwable {
//		logger.debug("loggingAround called...");
//		String methodName = joinPoint.getSignature().getName();
//		StopWatch sw = new StopWatch();
//		sw.start(methodName);
//		logger.debug("Target Method : "+methodName+" is calling.");
//		Object returnObject = joinPoint.proceed();
//		sw.stop();
//		logger.debug("Target Method : "+methodName+" is called.");
//		logger.debug("TIME   : "+sw.getTotalTimeMillis()/1000+" 초");
//
//		return returnObject;
//	}

    /**
     * AfterReturning
     * JoinPoint(메소드)에서 본문이 실행된 후에 호출된다.
     * result에 접근 가능
     * @param JoinPoint joinPoint
     * @param Object result
     * @return
     * @throws Throwable
     * @author jrkang
     * @date 2014-08-04
     */
//	@AfterReturning(pointcut = "execution(public * kr.web.dev.service..*.*(..))",  
//            returning= "result")
//	public void loggingAfterReturning(JoinPoint joinPoint, Object result) throws Throwable {
//		logger.debug("loggingAfterReturning called...");
//		String methodName = joinPoint.getSignature().getName();
//		logger.debug("Target Method : "+methodName+" is called.");
//		logger.debug("Object result : "+result);
//	}
	
}
