package kr.web.dev.common.util;

/**
 * Object 관련 유틸
 * @version 1.0
 * @author jrkang
 */
public class ObjectUtil {

    /**
     * obj가 null인지 체크
     * @param Object obj
     * @return boolean
     * @throws 
     * @author jrkang
     * @date 2014-07-17
     */
	public static boolean isNull(Object obj) {
		return obj == null ? true : false;
	}

    /**
     * obj가 null이면 replaceStr으로 대체
     * @param Object obj, String replaceStr
     * @return String
     * @throws 
     * @author jrkang
     * @date 2014-07-17
     */
	public static String isNull(Object obj, String replaceStr) {
		return obj == null ? replaceStr : obj.toString();
	}

    /**
     * obj가 ""인지 체크
     * @param Object obj
     * @return boolean
     * @throws 
     * @author jrkang
     * @date 2014-07-17
     */
	public static boolean isEmpty(Object obj) {
		return obj == "" ? true : false;
	}
	
}
