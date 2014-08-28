package kr.web.dev.common.properties;

import java.util.Properties;
import java.util.Set;

import kr.web.dev.common.util.ObjectUtil;

public class PropertyBean implements IResourceBean {

	private Properties properties;
	private String name;
	private String type;
	private String path;
	private String hashString;
	private boolean isSync;
	private Set<Object> keySet;
	private String value;
	
	@Override
	public Object getResource() {
		return properties;
	}
	@Override
	public void setResource(Object resource) {
		this.properties = (Properties)resource;
		
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
		keySet = properties.keySet();
		return keySet;
	}
	@Override
	public String getValue(String key) {
		value = properties.getProperty(key);
		return value;
	}
	@Override
	public String getValue(String key, String defaultValue) {
		value = properties.getProperty(key);
		if(ObjectUtil.isNull(value) || ObjectUtil.isNull(value)) {
			value = defaultValue;
		}
		return value;
	}
	@Override
	public String getValue(String key, String[] args) {
		value = properties.getProperty(key);
		if(!ObjectUtil.isNull(args) && args.length > 0) {
			for(Integer i = 0; i < args.length; i++) {
				value = value.replace("{"+i+"}", args[i]);
			}
		}
		return value;
	}
	@Override
	public String getValue(String key, String[] args, String defaultValue) {
		value = properties.getProperty(key);
		if(ObjectUtil.isNull(value) || ObjectUtil.isNull(value)) {
			value = defaultValue;
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
