package kr.web.dev.common.properties;

import java.util.HashMap;
import java.util.Map;

import kr.web.dev.common.util.ObjectUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 리소스 빈 또는 프로퍼티 빈들을 저장해 놓는다.
 * 리소스 빈 : ResourceBundle로 가져온 파일
 * 프로퍼티 빈 : Properties로 가져온 파일
 * @version 1.0
 * @author jrkang
 */
public class PropertyObject {

	private static final Logger logger = LoggerFactory.getLogger(PropertyObject.class);
	
	private static Map<String, IResourceBean> propertyStore;
	
	static {
		Object obj = new Object();
		synchronized (obj) {
			if (propertyStore == null) propertyStore = new HashMap<String, IResourceBean>();
		}
		
		// 최초 한번만 프로퍼티 파일 등록
		//PropertyManager pm = new PropertyManager();
		//pm.setPropertyStore();
		
	}
	
    /**
     * PropertyStore를 초기화 한다.
     * @param
     * @return
     * @throws 
     * @author jrkang
     * @date 2014-07-30
     */
	public static void initPropertyStore() {
		propertyStore.clear();
		propertyStore = null;
		propertyStore = new HashMap<String, IResourceBean>(); 
	}
	
	public static Integer getPropertyStoreSize() {
		return propertyStore.size();
	}
	
    /**
     * propertyId에 해당하는 프로퍼티빈을 얻는다.
     * @param String propertyId
     * @return IResourceBean
     * @throws 
     * @author jrkang
     * @date 2014-07-30
     */
	public static IResourceBean getProperty(String propertyId) {
		IResourceBean property = null;
		try {
			property = propertyStore.get(propertyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return property;
	}
	
    /**
     * propertyId에 해당하는 property를 삭제한다.
     * @param String propertyId
     * @return 
     * @throws 
     * @author jrkang
     * @date 2014-07-30
     */
	public static void removeProperty(String propertyId) {
		try {
			if(ObjectUtil.isNull(propertyStore.get(propertyId)))
				propertyStore.remove(propertyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     * propertyId로 프로퍼티빈을 저장한다.
     * @param String propertyId, ResourceBean property
     * @return
     * @throws 
     * @author jrkang
     * @date 2014-07-30
     */
	public static void setProperty(String propertyId, IResourceBean property) {
		try {
			PropertyObject.removeProperty(propertyId);
			propertyStore.put(propertyId, property);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     * propertyStore를 리턴한다.
     * @param 
     * @return Map<String, ResourceBean>
     * @throws 
     * @author jrkang
     * @date 2014-07-30
     */
	public static Map<String, IResourceBean> getPropertyStore() {
		return propertyStore;
	}
	
}
