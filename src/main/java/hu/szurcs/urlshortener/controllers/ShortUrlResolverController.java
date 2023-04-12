package hu.szurcs.urlshortener.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import hu.szurcs.urlshortener.services.ShortUrlResolverService;

@Controller
@RequestMapping("#{environment.BASE_PATH}")
public class ShortUrlResolverController {

	@Autowired
	ShortUrlResolverService shortUrlResolverService;
	
	//https://www.baeldung.com/spring-redirect-and-forward
	//https://stackoverflow.com/questions/16232833/how-to-respond-with-an-http-400-error-in-a-spring-mvc-responsebody-method-retur
	//TODO: resolve redirection to "{url}" and not to "baseUrl/{url}"
	@GetMapping("{shortUrl:.*}")
	public ResponseEntity<RedirectView> resolveShortUrl(@PathVariable String shortUrl) {
		
		String url;
		try {
			url = shortUrlResolverService.resolveShortUrl(shortUrl);
			return ResponseEntity.ok(new RedirectView(url));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		}
		
	}
}
