package com.yaus.occ.persistence;

public interface RegistryDAO {

	
	public void registerURL(String key, String url);
	
	public String getURL(String key);
	
}
