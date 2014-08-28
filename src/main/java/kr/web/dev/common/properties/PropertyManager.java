package kr.web.dev.common.properties;

import java.util.Locale;

import kr.web.dev.common.constant.Constants;
import kr.web.dev.common.util.FileUtil;
import kr.web.dev.common.util.PropertyLoadUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * properties 파일들을 관리한다.
 * @version 1.0
 * @author jrkang
 */
public class PropertyManager {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertyManager.class);
	
	public PropertyManager() {
		logger.debug("constructor call");
		init();
		//setPropertyStore(); // 최초 한번만 프로퍼티 파일 등록
	}
	
	private void init() {
		logger.debug("init call");
	}
	
    /**
     * 진행
     * @param
     * @return
     * @throws
     * @author jrkang
     * @date 2014-07-31
     */
	public void proceed() {
		logger.debug("proceed call");
		logger.debug("PropertyObject Size["+PropertyObject.getPropertyStoreSize()+"]");
		if(PropertyObject.getPropertyStoreSize() > 0) {
			syncHashCheck();
		} else {
			setPropertyStore();
		}
	}
	
    /**
     * 기존에 있던 properties빈의 hash값과 실제 properties파일의 hash값을 
     * 비교해서 다르면 다시 가져온다.
     * @param
     * @return
     * @throws
     * @author jrkang
     * @date 2014-07-31
     */
	private void syncHashCheck() {
		logger.debug("syncHashCheck call");
		for(String str : Constants.Properties_File_Array) {
			String[] strArr = str.split("\\|");
			String name = strArr[0];
			if(PropertyObject.getPropertyStore().containsKey(name)) {
				logger.debug("containsKey ["+name+"]");
				IResourceBean rb = PropertyObject.getProperty(name);
				logger.debug("file path ["+rb.getPath() + rb.getName()+"]");
				String hStr = null;
				String filePath = makeFilePath(rb);
				hStr = FileUtil.extractHashSHA256FromInputStream(FileUtil.getFileInputStream(filePath));
				logger.debug("hStr ["+hStr+"] rb.getHashString()["+rb.getHashString()+"]");
				if(hStr.equals(rb.getHashString())) {
					logger.debug("equal [true]");
					continue;
				}else{
					logger.debug("equal [false]");
					setPropertyStore(rb);
				}
			}
		}
	}
	
    /**
     * 해당 타입에 맞는 파일 경로를 만들어서 리턴한다.
     * @param IResourceBean resourceBean
     * @return String
     * @throws
     * @author jrkang
     * @date 2014-07-31
     */
	private String makeFilePath(IResourceBean resourceBean) {
		Locale locale = LocaleContextHolder.getLocale().toString().toLowerCase().indexOf("ko") > -1 ? Locale.KOREAN : LocaleContextHolder.getLocale();
		return resourceBean.getPath() + resourceBean.getName() 
				+ ("R".equals(resourceBean.getType())?(Constants.File_Name_Underline + locale):"")
				+ Constants.Properties_File_Extension;
	}
	
    /**
     * 해당 타입에 맞게 빈을 만든다.
     * @param IResourceBean resourceBean
     * @return
     * @throws
     * @author jrkang
     * @date 2014-07-31
     */
	private void setPropertyStore(IResourceBean resourceBean) {
		logger.debug("setPropertyStore(ResourceBean resourceBean) call");
		PropertyObject.removeProperty(resourceBean.getName());
		if("R".equals(resourceBean.getType())) {
			setResourceBeanData(resourceBean.getName());
		}else if("P".equals(resourceBean.getType())) {
			setPropertyBeanData(resourceBean.getName());
		}
	}
	
    /**
     * 해당 타입에 맞게 빈을 만든다.
     * @param
     * @return
     * @throws
     * @author jrkang
     * @date 2014-07-31
     */
	public void setPropertyStore() {
		logger.debug("setPropertyStore() call");
		for(String str : Constants.Properties_File_Array) {
			logger.debug("str["+str+"]");
			String[] strArr = str.split("\\|");
			String name = strArr[0];
			String type = strArr[1];
			logger.debug("name["+name+"] "+"type["+type+"]");
			if("R".equals(type)) {
				setResourceBeanData(name);
			}else if("P".equals(type)) {
				setPropertyBeanData(name);
			}
		}
	}
	
    /**
     * 리소스빈을 만들어서 프로퍼티 오브젝트에 저장한다.
     * @param String name
     * @return
     * @throws
     * @author jrkang
     * @date 2014-07-31
     */
	private void setResourceBeanData(String name) {
		logger.debug("setResourceBeanData() call");
		IResourceBean rb = new ResourceBundleBean();
		rb.setName(name);
		rb.setType("R");
		rb.setPath(Constants.Resource_File_Root_Path);
		rb.setHashString(
				FileUtil.extractHashSHA256FromInputStream(FileUtil.getFileInputStream(makeFilePath(rb)))
				);
		PropertyLoadUtil pu = new PropertyLoadUtil();
		rb.setResource(null);
		rb.setResource(pu.getResourceBundle(Constants.ResourceBundle_File_Root_Path + rb.getName()));
		rb.setSync(true);
		PropertyObject.setProperty(name, rb);
	}
	
    /**
     * 프로퍼티빈을 만들어서 프로퍼티 오브젝트에 저장한다.
     * @param String name
     * @return
     * @throws
     * @author jrkang
     * @date 2014-07-31
     */
	private void setPropertyBeanData(String name) {
		logger.debug("setPropertyBeanData() call");
		IResourceBean rb = new PropertyBean();
		rb.setName(name);
		rb.setType("P");
		rb.setPath(Constants.Resource_File_Root_Path);
		rb.setHashString(
				FileUtil.extractHashSHA256FromInputStream(FileUtil.getFileInputStream(makeFilePath(rb)))
				);
		PropertyLoadUtil pu = new PropertyLoadUtil();
		rb.setResource(pu.getProperties(rb.getPath() + rb.getName() + Constants.Properties_File_Extension));
		rb.setSync(true);
		PropertyObject.setProperty(name, rb);
	}
	
	
	
}
