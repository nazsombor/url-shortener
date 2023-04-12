package hu.szurcs.urlshortener.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.szurcs.urlshortener.services.UrlShortenerService;

@RestController
@RequestMapping(path="/shorten")
public class UrlShortenerController {
	
	//https://stackoverflow.com/questions/14617181/java-spring-how-to-use-value-annotation-to-inject-an-environment-property
	@Value("#{environment.BASE_PATH}")
	String basePath;
	
	@Autowired
	UrlShortenerService urlShortenerService;
	
	//https://stackoverflow.com/questions/19981012/spring-request-mapping-wildcard-exceptions
	@GetMapping("{url:.*}")
	String shortenUrl(@PathVariable String url) {
		
		return basePath + "/" + urlShortenerService.shortenUrl(url);
	}
	
}
