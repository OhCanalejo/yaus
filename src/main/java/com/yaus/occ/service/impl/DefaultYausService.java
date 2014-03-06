package com.yaus.occ.service.impl;

import java.util.UUID;

import org.apache.commons.validator.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yaus.occ.HomeController;
import com.yaus.occ.model.YausURL;
import com.yaus.occ.persistence.RegistryDAO;
import com.yaus.occ.service.YausService;

public class DefaultYausService implements YausService {

	@Autowired
	RegistryDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Override
	public YausURL shortenURL(String url) {
		
		if (!this.validURL(url)) {
			throw new IllegalArgumentException();
		}
		String key = this.generateKey();
		logger.info("Result Base36 key: {}",key);

		YausURL yausURL = new YausURL(key, url);
		dao.registerURL(yausURL);
		return yausURL;
	}

	@Override
	public YausURL unveilURL(String key) {

		YausURL yausURL = dao.getURL(key);
		if (yausURL != null)
			logger.info("URL registered for key {} is: {}",key,yausURL.getLongURL());
		
		return yausURL;
	}

	
	/**
	 * Generates a new key from a random UUID and a Base36 encoding 
	 * @return the new key generated
	 */
	private String generateKey() {
		UUID uuid = UUID.randomUUID(); //new UUID(new Date().hashCode(),url.hashCode());
		logger.info("Random UUID generated for the key: {}", uuid.toString());
		return Long.toString(uuid.getMostSignificantBits(), 36);
	}
	
	/**
	 * Validates a URL 
	 * @param url
	 * @return true if the URL is valid. False otherwise
	 */
	private boolean validURL(String url) {
		UrlValidator urlValidator = new UrlValidator();
		return urlValidator.isValid(url);
	}
	
 
}
