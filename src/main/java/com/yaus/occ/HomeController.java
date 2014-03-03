package com.yaus.occ;

import java.security.Provider.Service;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yaus.occ.service.YausService;

/**
 * @author oscar.canalejo
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	YausService yausService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Home view
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("at home!");
		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		String formattedDate = dateFormat.format(date);
//		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	/**
	 * Shortens a given URL
	 * @param url
	 * @return the shortened URL
	 */
	@RequestMapping(value = "/short", method = RequestMethod.GET)
	public String shortenURL(Locale locale, Model model, 
							@RequestParam(value="url") String url) {
		
		String shortURL = yausService.shortenURL(url);
		
		model.addAttribute("shortenedURL", shortURL);
		return "home";
	}

	/**
	 * Receives a shortened URL and gets the unveiled original one  
	 * @param url the shortened URL
	 * @return the original, non shortened URL 
	 */
	@RequestMapping(value = "/unveil", method = RequestMethod.GET)
	public String unveilURL(Locale locale, Model model, 
							@RequestParam(value="url") String url) {
		
		String unveiledURL = yausService.unveilURL(url);
		
		model.addAttribute("unveiledURL", unveiledURL);
		return "home";
	}
	
}
