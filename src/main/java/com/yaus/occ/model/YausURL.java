package com.yaus.occ.model;

import java.util.Date;

/**
 * Our main entity
 * @author oscar.canalejo
 *
 */
public class YausURL {

	private String key;
	private String longURL;
	private long clicsCount;
	private Date createdAt;
	
	
	public YausURL(String key, String url) {
		this.key = key;
		this.longURL = url;
		this.createdAt = new Date();
	}
	
	/**
	 * Increments the number of clics this URL has 
	 * @return updated count of clics
	 */
	public long incrementClickCount() {
		return this.clicsCount++;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLongURL() {
		return longURL;
	}
	public void setLongURL(String longURL) {
		this.longURL = longURL;
	}
	public long getClicsCount() {
		return clicsCount;
	}
	public void setClicsCount(long clicsCount) {
		this.clicsCount = clicsCount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	
}
