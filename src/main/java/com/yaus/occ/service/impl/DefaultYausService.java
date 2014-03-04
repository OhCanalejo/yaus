package com.yaus.occ.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaus.occ.HomeController;
import com.yaus.occ.persistence.RegistryDAO;
import com.yaus.occ.service.YausService;

public class DefaultYausService implements YausService {

	@Autowired
	RegistryDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Override
	public String shortenURL(String url) {
		String key = this.generateKey();
		logger.info("Result Base36 key: {}",key);
		/*
		 * TODO: Register URL with key (save it with corresponding DAO strategy)
		 */
		dao.registerURL(key, url);
		return key;
	}

	@Override
	public String unveilURL(String key) {
		/*
		 * TODO Recover URL registered with key (if exists)
		 * (Retrieve it from corresponding DAO strategy)
		 */
		String url = dao.getURL(key); 
		logger.info("URL registered for key {} is: {}",key,url);		
		return url;
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
	
//	private String Base36Encode(final long value) {
//	    return Long.toString(value, 36);
//	}
//	
//	private long Base36Decode(final String value) {
//		return Long.parseLong(value, 36);
//	}
 

  
}
