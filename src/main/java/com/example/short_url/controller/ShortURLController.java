package com.example.short_url.controller;

import com.example.short_url.service.ShortURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;


@RestController
public class ShortURLController {
    @Autowired
    private ShortURLService shortURLService;

    /*
    * API for creating short url
    * input - Map <sString, String> request
    * request -> key - "destination"
    * request -> key - <destination url>
    * out[ut - shortened url
    */
    @PutMapping("/shorten")
    public ResponseEntity< String> shortenUrl(@RequestBody Map<String, String> request) {
        String destination = request.get("destination");
            String shortcode = shortURLService.shortenUrl(destination);
            //String response = shortcode;
            return ResponseEntity.ok(shortcode);
    }


    /*
     * API for redirecting short url to destination url
     * input - PathVariable short url
     * output - loads original url
     */
    @GetMapping("/{shortcode}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortcode) {
        String originalUrl = shortURLService.getOriginalUrl(shortcode);
        if (originalUrl != null) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION, originalUrl)
                    .build();
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Shortcode not found"));
        }
    }

}
