package com.tht.demo.service;

import com.tht.demo.model.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BannerService {

    Page<Banner> showAll(Pageable pageable);

    Optional<Banner> findById(Long id);

    Banner save(Banner banner);

    void delete(Long id);

    Page<Banner> search(String description, Pageable pageable);

    Page<Banner> findLimit(Pageable pageable);
}
