package kr.web.dev.common.properties;

import java.util.Set;

public interface IResourceBean {

	public Object getResource();
	public void setResource(Object resource);
	public String getName();
	public void setName(String name);
	public String getType();
	public void setType(String type);
	public String getPath();
	public void setPath(String path);
	public String getHashString();
	public void setHashString(String hashString);
	public boolean isSync();
	public void setSync(boolean isSync);
	public Set<?> getKeySet();
	public String getValue(String key);
	public String getValue(String key, String defaultValue);
	public String getValue(String key, String[] args);
	public String getValue(String key, String[] args, String defaultValue);
	public void setValue(String value);
	
}
