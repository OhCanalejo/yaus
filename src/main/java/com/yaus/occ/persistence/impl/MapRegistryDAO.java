package com.yaus.occ.persistence.impl;

import java.util.HashMap;
import java.util.Map;

import com.yaus.occ.persistence.RegistryDAO;

/**
 * An in-memory implementation of the URL Registry DAO
 * 
 * @author oscar.canalejo
 *
 */
public class MapRegistryDAO implements RegistryDAO {

	private static Map<String, String> urlRegistry = null;
	
	/* (non-Javadoc)
	 * @see com.yaus.occ.persistence.URLRegistryDAO#registerURL(java.lang.String, java.lang.String)
	 */
	@Override
	public void registerURL(String key, String url) {
		if (urlRegistry == null) {
			urlRegistry = new HashMap<String, String>();
		}
		urlRegistry.put(key, url);
	}

	/* (non-Javadoc)
	 * @see com.yaus.occ.persistence.URLRegistryDAO#getURL(java.lang.String)
	 */
	@Override
	public String getURL(String key) {
		if (urlRegistry == null) {
			return null;
		}
		return urlRegistry.get(key);
	}

}
