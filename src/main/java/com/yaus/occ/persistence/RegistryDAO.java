package com.yaus.occ.persistence;

import com.yaus.occ.model.YausURL;

/**
 * Interface for Persistence
 * Depending on the chosen strategy, 
 * possible implementations for this interface could be:
 * - In-memory
 * - Cache 
 * - DataBase
 * - ...
 * 
 * @author oscar.canalejo
 *
 */
public interface RegistryDAO {

	
	/**
	 * Saves/Registers a new {@link YausURL}
	 * @param yausURL
	 */
	public void registerURL(YausURL yausURL);
	
	
	/**
	 * Retrieves the {@link YausURL} related to the given key
	 * @param key
	 * @return the related {@link YausURL}, or null if not found
	 */
	public YausURL getURL(String key);
	
}
