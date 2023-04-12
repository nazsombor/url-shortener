package hu.szurcs.urlshortener.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szurcs.urlshortener.entities.UrlShortener;
import hu.szurcs.urlshortener.repositories.UrlShortenerRepository;

@Service
public class UrlShortenerService {

	@Autowired
	UrlShortenerRepository urlShortenerRepository;
	
	public String shortenUrl(String url) {
		
		UrlShortener urlShortenerEntity = urlShortenerRepository.findByUrl(url);
		
		if (urlShortenerEntity == null) {
			
			String shortUrl = generateShortUrl(url);
			
			urlShortenerEntity = new UrlShortener();
			urlShortenerEntity.setUrl(url);
			urlShortenerEntity.setShortUrl(shortUrl);
			
			urlShortenerRepository.save(urlShortenerEntity);
		}
				
		return urlShortenerEntity.getShortUrl();
	}
	
	//TODO: assure uniqueness when possible throw error otherwise (when all combination is used)
	private String generateShortUrl(String url) {
		
		int absHashCode = Math.abs(url.hashCode());
		String longHashCode = Integer.toString(absHashCode);
		
		int length = longHashCode.length();
		
		if(length > 8) {
			length = 8;
		}
		
		String shortUrl = longHashCode.substring(0, length);
		
		return shortUrl;
	}
}
