package com.yaus.occ.persistence;

import com.yaus.occ.model.YausURL;

public interface RegistryDAO {

	
	public void registerURL(YausURL yausURL);
	
	public YausURL getURL(String key);
	
}
