package com.example.short_url.service;

import com.example.short_url.entity.URLMapping;
import com.example.short_url.repository.URLMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.UUID;

@Service
@Component
public class ShortURLServiceImpl implements ShortURLService {
    @Autowired
    private URLMappingRepository urlMappingRepository;

    public String shortenUrl(String destination) {
        URLMapping urlMapping = new URLMapping();
        if (isValidURL(destination)) {
            urlMapping.setDestination(destination);

            // Generate a unique shortcode (you can implement your own logic)
            urlMapping.setShortcode(generateShortcode());

            urlMappingRepository.save(urlMapping);

            return urlMapping.getShortcode();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public String getOriginalUrl(String shortcode) {
        return urlMappingRepository.findByShortcode(shortcode)
                .map(URLMapping::getDestination)
                .orElse(null);
    }

    //  logic to generate a short url
    private String generateShortcode() {
        return UUID.randomUUID().toString().substring(0, 8); // Adjust the length as needed
    }

    private boolean isValidURL(String destination) {
        UrlValidator validator = new UrlValidator();
        return validator.isValid(destination);
    }

}
