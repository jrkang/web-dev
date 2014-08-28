package kr.web.dev.common.util;

import kr.web.dev.common.exception.BaseException;

/**
 * Exception 관련 유틸
 * @version 1.0
 * @author jrkang
 */
public class ExceptionUtil {

    /**
     * 최초 예외를 찾아서 리턴한다.
     * @param Throwable thrown
     * @return Throwable
     * @throws
     * @author jrkang
     * @date 2014-08-01
     */
    public static Throwable findCauseBaseException(Throwable thrown) {
    	Throwable findT = (thrown instanceof BaseException ? thrown : null);
    	
    	Throwable causeT;
    	for(Throwable thisT = thrown; null != (causeT = thisT.getCause()); thisT = causeT) {
    		if(causeT instanceof BaseException) {
    			findT = causeT;
    		}
    	}
    	return findT;
    }
    
    /**
     * 최초 예외를 찾아서 예외가 발생된 클래스 이름과 메서드 이름을 리턴한다.
     * @param Throwable thrown, boolean isIncludeMethodName
     * @return String
     * @throws
     * @author jrkang
     * @date 2014-08-01
     */
    public static String getExceptionOccuredClazzName(Throwable thrown, boolean isIncludeMethodName) {
        String clazzName = "";
        
    	Throwable t = findCauseBaseException(thrown);
        if(t == null) {
        	clazzName = "";
        }
        else {
	        StackTraceElement[] s = t.getStackTrace();
	        int index = (t instanceof BaseException ? 0 : 1);
	        clazzName= s[index].getClassName() + "." + s[index].getMethodName() + "()";
        }
        return clazzName;
    }
	
    /**
     * 최초 예외를 찾아서 예외가 발생된 줄 번호를 리턴한다.
     * @param Throwable thrown
     * @return String
     * @throws
     * @author jrkang
     * @date 2014-08-01
     */
    public static String getExceptionOccuredLineNumber(Throwable thrown) {
        String lineNumber = "";
        
    	Throwable t = findCauseBaseException(thrown);
        if(t == null) {
        	lineNumber = "";
        }
        else {
	        StackTraceElement[] s = t.getStackTrace();
	        int index = (t instanceof BaseException ? 0 : 1);
	        lineNumber= Integer.toString(s[index].getLineNumber());
        }
        return lineNumber;
    }
    
}
