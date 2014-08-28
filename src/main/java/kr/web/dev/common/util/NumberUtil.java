package kr.web.dev.common.util;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Number 관련 유틸
 * @version 1.0
 * @author jrkang
 */
public class NumberUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(NumberUtil.class);
	
    /**
     * 난수값을 얻는다.
     * @param
     * @return String
     * @throws 
     * @author jrkang
     * @date 2014-07-17
     */
	public static String getRandomValue() {
		Random rand = null;
		String random = null;
		
		try {
			rand = new Random(System.currentTimeMillis());
			random = Integer.valueOf(Math.abs(rand.nextInt())).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return random;
	}
}
