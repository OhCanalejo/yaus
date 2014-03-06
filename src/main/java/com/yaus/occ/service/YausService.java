package com.yaus.occ.service;

import com.yaus.occ.model.YausURL;

public interface YausService {

	
	/**
	 * Creates a shorten version of the given URL
	 * and registers it (saves it with the chosen DAO strategy)
	 * @param url
	 * @return a new {@link YausURL} instance 
	 * @throws IllegalArgumentException if the given url is not valid 
	 */
	public YausURL shortenURL(String url);
	
	/**
	 * Uses the given key to recover its related long URL
	 * @param key
	 * @return the corresponding {@link YausURL}, or null if key is not found
	 */
	public YausURL unveilURL(String url);
	

}
