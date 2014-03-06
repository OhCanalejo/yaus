package com.yaus.occ;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
	@Autowired
	ServletContext servletContext;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
	 * Redirects to its related long URL.
	 * (If key is found in our registry)
	 * @param url
	 * @return checks if URL is registered. If yes then redirects user to the original URL
	 */
	@RequestMapping(value = "/{key}", method = RequestMethod.GET)
	public String testURL(Locale locale, @PathVariable(value="key") String key) {
		
		logger.info("Redirection Requested for long URL with key {}", key);
		
		YausURL yausURL = yausService.unveilURL(key);
		if (yausURL != null) {
			yausURL.incrementClickCount();
			return "redirect:" + yausURL.getLongURL();	
		}else {
			return "home";
		}
		
	}
	
	/**
	 * Shortens a given URL
	 * @param url
	 * @return the shortened URL
	 */
	@RequestMapping(value = "/shorten", method = RequestMethod.GET)
	public String shortenURL(Locale locale, Model model, 
							@RequestParam(value="url") String url) {
		
		logger.info("Shorten Requested for URL {}", url);
		try {
			YausURL yausURL = yausService.shortenURL(url);
			model.addAttribute("shortenedURL", yausURL.getKey());
		
		} catch (IllegalArgumentException ex) {
			logger.error("Error occurred. {}",ex);
			model.addAttribute("shorten_error", "Sorry. It doesn't seem a valid URL");
		}
		return "home";
	}

	/**
	 * Receives a shortened URL key and gets the original long one  
	 * @param url the key for a shortened URL
	 * @return the original, non shortened URL 
	 */
	@RequestMapping(value = "/enlarge", method = RequestMethod.GET)
	public String getLongURL(Locale locale, Model model, 
							@RequestParam(value="key") String key) {
		
		logger.info("Enlarge Requested for key {}", key);
		
		YausURL yausURL = yausService.unveilURL(key);
		if (yausURL != null){
			model.addAttribute("yausURL", yausURL);
		} else {
			model.addAttribute("enlarge_error", "Not Found");
		}
		return "home";
	}
	
}
