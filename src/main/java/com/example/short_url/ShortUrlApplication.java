package com.example.short_url;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShortUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortUrlApplication.class, args);
	}

}
