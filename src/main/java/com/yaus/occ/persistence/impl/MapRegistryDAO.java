package com.yaus.occ.persistence.impl;

import java.util.HashMap;
import java.util.Map;

import com.yaus.occ.model.YausURL;
import com.yaus.occ.persistence.RegistryDAO;

/**
 * An in-memory implementation of the URL Registry DAO
 * 
 * @author oscar.canalejo
 *
 */
public class MapRegistryDAO implements RegistryDAO {

	private static Map<String, YausURL> urlRegistry = null;
	
	@Override
	public void registerURL(YausURL yausURL) {
		if (urlRegistry == null) {
			urlRegistry = new HashMap<String, YausURL>();
		}
		urlRegistry.put(yausURL.getKey(), yausURL);
	}

	@Override
	public YausURL getURL(String key) {
		if (urlRegistry == null) {
			return null;
		}
		return urlRegistry.get(key);
	}

}
