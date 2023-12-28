package com.example.short_url.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public interface ShortURLService {
    String shortenUrl(String destination);
    String getOriginalUrl(String shortcode);

}
