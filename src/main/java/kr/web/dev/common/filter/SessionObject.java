package kr.web.dev.common.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 사용자 세션 관련
 * 
 * @version 1.0
 * @author jrkang
 */
public class SessionObject {
	
	private static final Logger				logger		= LoggerFactory.getLogger(SessionObject.class);
	
	private static Map<String, HttpSession>	sesssionMap	= new HashMap<String, HttpSession>();
	
	public static void removeSession(String id) {
		
		try {
			if (sesssionMap.containsKey(id)) {
				sesssionMap.remove(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("removeSession id[" + id + "]");
		
	}
	
	public static void addSession(String id, HttpSession httpSession) {
		sesssionMap.put(id, httpSession);
		logger.debug("addSession id[" + id + "]");
	}
	
	public static HttpSession getSession(String id) {
		logger.debug("getSession id[" + id + "]");
		return sesssionMap.get(id);
	}
	
	public static Integer getSessionCount() {
		logger.debug("getSessionCount[" + sesssionMap.size() + "]");
		return sesssionMap.size();
	}
	
}
