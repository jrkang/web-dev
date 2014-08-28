package kr.web.dev.common.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import kr.web.dev.common.properties.ResourceControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

/**
 * Property 유틸리티
 * i18n : ResourceBundle로 가져옴(해당 클래스 패스에서 파일을 가져옴, cache되기 때문에 리로드 불가)
 * 기본 설정 파일 : Properties로 가져옴
 * @version 1.0
 * @author jrkang
 */
public class PropertyLoadUtil {

	private static final Logger logger = LoggerFactory.getLogger(PropertyLoadUtil.class);
	
    /**
     * 생성자
     * @param
     * @return
     * @throws 
     * @author jrkang
     * @date 2014-07-31
     */
	public PropertyLoadUtil() {}

    /**
     * 해당 파일로 메시지소스를 만들어 리턴한다.(테스트 미완료)
     * @param String propertyFile
     * @return MessageSource
     * @throws 
     * @author jrkang
     * @date 2014-07-31
     */
	public MessageSource getMessageSource(String propertyFile) {
		logger.debug("getMessageSource call propertyFile ["+propertyFile+"]");
		ReloadableResourceBundleMessageSource rrbms = new ReloadableResourceBundleMessageSource();
		ResourceLoader rl = new DefaultResourceLoader();
		rl.getResource(propertyFile);
		rrbms.setResourceLoader(rl);
		rrbms.setCacheSeconds(60);
		rrbms.setDefaultEncoding("UTF-8");
		return rrbms;
	}
	
    /**
     * 해당 파일로 리소드번들을 만들어 리턴한다.
     * @param String propertyFile
     * @return ResourceBundle
     * @throws 
     * @author jrkang
     * @date 2014-07-31
     */
	public ResourceBundle getResourceBundle(String propertyFile) {
		logger.debug("getResourceBundle call propertyFile ["+propertyFile+"]");
		ResourceBundle.clearCache(Thread.currentThread().getContextClassLoader());
		ResourceBundle resourceBundle = ResourceBundle.getBundle(propertyFile, LocaleContextHolder.getLocale());	//LocaleContextHolder.getLocale()
		return resourceBundle;
	}
	
    /**
     * 해당 파일로 리소드번들을 만들어 리턴한다.(no cache: 테스트 미완료)
     * @param String propertyFile
     * @return ResourceBundle
     * @throws 
     * @author jrkang
     * @date 2014-07-31
     */
	public ResourceBundle getResourceBundleNoCache(String propertyFile) {
		logger.debug("getResourceBundleNoCache call propertyFile ["+propertyFile+"]");
		ResourceControl uc = new ResourceControl();
		ResourceBundle resourceBundle = null;
		try {//LocaleContextHolder.getLocale()
			resourceBundle = uc.newBundle(propertyFile, LocaleContextHolder.getLocale(), null, Thread.currentThread().getContextClassLoader(), true);
			//logger.debug("getResourceBundle call getString ["+resourceBundle.getString("aaa.bbb.ccc")+"]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resourceBundle;
	}
	
    /**
     * 해당 파일로 Properties를 만들어 리턴한다.
     * @param String propertyFile
     * @return Properties
     * @throws 
     * @author jrkang
     * @date 2014-07-31
     */
	public Properties getProperties(String propertyFile) {
		logger.debug("getProperties call propertyFile ["+propertyFile+"]");
		InputStream in = null;
		Properties prop = new Properties();
		try {
			in = new FileInputStream(propertyFile);
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return prop;
	}
	
}
