package com.example.short_url.repository;

import com.example.short_url.entity.URLMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface URLMappingRepository  extends JpaRepository<URLMapping, Long> {

    Optional<URLMapping> findByShortcode(String shortcode);
}
