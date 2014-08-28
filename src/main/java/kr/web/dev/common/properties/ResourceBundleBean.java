package kr.web.dev.common.properties;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import kr.web.dev.common.util.ObjectUtil;

public class ResourceBundleBean implements IResourceBean {

	private ResourceBundle resourceBundle;
	private String name;
	private String type;
	private String path;
	private String hashString;
	private boolean isSync;
	private Set<String> keySet;
	private String value;
	
	@Override
	public Object getResource() {
		return resourceBundle;
	}
	@Override
	public void setResource(Object resource) {
		if(this.resourceBundle != null)
			this.resourceBundle = null;
		this.resourceBundle = (ResourceBundle)resource;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getType() {
		return type;
	}
	@Override
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String getPath() {
		return path;
	}
	@Override
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String getHashString() {
		return hashString;
	}
	@Override
	public void setHashString(String hashString) {
		this.hashString = hashString;
	}
	@Override
	public boolean isSync() {
		return isSync;
	}
	@Override
	public void setSync(boolean isSync) {
		this.isSync = isSync;
	}
	@Override
	public Set<?> getKeySet() {
		this.keySet = resourceBundle.keySet();
		return keySet;
	}
	@Override
	public String getValue(String key) {
		value = resourceBundle.getString(key);
		return value;
	}
	@Override
	public String getValue(String key, String defaultValue) {
		
		try {
			value = resourceBundle.getString(key);
		}catch(MissingResourceException mre) {
			value = defaultValue;
		}catch(Exception mre) {
			mre.printStackTrace();
		}
		return value;
		
	}
	@Override
	public String getValue(String key, String[] args) {
		value = resourceBundle.getString(key);
		if(!ObjectUtil.isNull(args) && args.length > 0) {
			for(Integer i = 0; i < args.length; i++) {
				value = value.replace("{"+i+"}", args[i]);
			}
		}
		return value;
	}
	@Override
	public String getValue(String key, String[] args, String defaultValue) {
		try {
			value = resourceBundle.getString(key);
		}catch(MissingResourceException mre) {
			value = defaultValue;
		}catch(Exception mre) {
			mre.printStackTrace();
		}
		
		if(!ObjectUtil.isNull(args) && args.length > 0) {
			for(Integer i = 0; i < args.length; i++) {
				value = value.replace("{"+i+"}", args[i]);
			}
		}
		return value;
	}
	@Override
	public void setValue(String value) {
		this.value = value;
	}
	
}
