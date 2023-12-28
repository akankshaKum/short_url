package com.example.short_url.service;

import com.example.short_url.entity.URLMapping;
import com.example.short_url.repository.URLMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Component
public class ShortURLServiceImpl implements ShortURLService {
    @Autowired
    private URLMappingRepository urlMappingRepository;

    public String shortenUrl(String destination) {
        URLMapping urlMapping = new URLMapping();
        urlMapping.setDestination(destination);

        // Generate a unique shortcode (you can implement your own logic)
        urlMapping.setShortcode(generateShortcode());

        urlMappingRepository.save(urlMapping);

        return urlMapping.getShortcode();
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

}
