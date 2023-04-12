package hu.szurcs.urlshortener.repositories;

import org.springframework.data.repository.CrudRepository;

import hu.szurcs.urlshortener.entities.UrlShortener;

public interface UrlShortenerRepository extends CrudRepository<UrlShortener, Integer>{

	UrlShortener findByUrl(String url);
	UrlShortener findByShortUrl(String shortUrl);
	
}
