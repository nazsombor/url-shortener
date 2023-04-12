package hu.szurcs.urlshortener.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szurcs.urlshortener.entities.UrlShortener;
import hu.szurcs.urlshortener.repositories.UrlShortenerRepository;

@Service
public class ShortUrlResolverService {

	@Autowired
	UrlShortenerRepository urlShortenerRepository;
	
	public String resolveShortUrl(String shortUrl) throws Exception {
		UrlShortener urlShortenerEntity = urlShortenerRepository.findByShortUrl(shortUrl);
		if (urlShortenerEntity == null) {
			//TODO: throw proper not found exception to allow redirection to error page
			throw new Exception("Not found");
		}
		
		return urlShortenerEntity.getUrl();
		
	}
}
