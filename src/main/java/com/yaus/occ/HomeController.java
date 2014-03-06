package com.yaus.occ;

import java.net.URI;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yaus.occ.model.YausURL;
import com.yaus.occ.service.YausService;

/**
 * Main Controller
 * @author oscar.canalejo
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	YausService yausService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final String BASE_URL = "http://127.0.0.1:8080";
	
	
	/**
	 * Home/Main view
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("there's no place like home");
		return "home";
	}
	
	/**
	 * Receives a short URL and 
	 * Redirects user to its related long URL.
	 * (If key is found in our registry)
	 * @param url
	 * @return Navigation redirection to the original long URL (if it's registered) 
	 */
	@RequestMapping(value = "/{key}", method = RequestMethod.GET)
	public String testURL(Locale locale, Model model, @PathVariable(value="key") String key) {
		
		logger.info("Redirection Requested for long URL with key {}", key);
		
		YausURL yausURL = yausService.unveilURL(key);
		if (yausURL != null) {
			yausURL.incrementClickCount();
			return "redirect:" + yausURL.getLongURL();	
		}else {
			model.addAttribute("enlarge_error", "Sorry, No URL found for the key " + key);
			return "home";
		}
		
	}
	
	/**
	 * Shortens a given URL
	 * @param url the long url
	 * @return the shortened URL
	 */
	@RequestMapping(value = "/shorten", method = RequestMethod.GET)
	public String shortenURL(Locale locale, Model model, HttpServletRequest request,
							@RequestParam(value="url") String url) {
		
		logger.info("Shorten Requested for URL {}", url);
		try {
			YausURL yausURL = yausService.shortenURL(url);
			model.addAttribute("shortenedURL", BASE_URL +  
					request.getContextPath() + "/" + yausURL.getKey());
		
		} catch (IllegalArgumentException ex) {
			logger.error("Error occurred. {}",ex);
			model.addAttribute("shorten_error", "Sorry. It doesn't seem a valid URL");
		}
		return "home";
	}

	/**
	 * Receives a short URL and gets the original long one  
	 * @param url the short URL
	 * @return the original, non shortened URL 
	 */
	@RequestMapping(value = "/getLongURL", method = RequestMethod.GET)
	public String getLongURL(Locale locale, Model model, HttpServletRequest request,
							@RequestParam(value="short_url") String url) {
		
		logger.info("Long URL requested for {}", url);
		String key = this.extractKey(request.getContextPath(), url);
		YausURL yausURL = yausService.unveilURL(key);
		if (yausURL != null){
			model.addAttribute("yausURL", yausURL);
		} else {
			model.addAttribute("enlarge_error", "Not Found");
		}
		return "home";
	}
	
	/**
	 * Extract the 'key' part from a URL
	 * @param url
	 * @return the key part, or null if something went wrong
	 */
	private String extractKey(String contextPath, String url) {
		try {
			URI uri = new URI(url);
			String key = uri.getPath().substring(contextPath.length()+1);
			return key;
		} catch (Exception ex) {
			logger.error("Error extracting key from url {} ",url,ex);
			return null;
		}
		
	}
	
}
