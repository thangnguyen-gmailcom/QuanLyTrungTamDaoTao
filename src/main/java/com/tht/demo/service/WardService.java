package com.tht.demo.service;

import com.tht.demo.model.Blog;
import com.tht.demo.model.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface WardService {
    Page<Ward> showAll(Pageable pageable);
    Optional<Ward> findById(Long id);
    Ward save(Ward ward);
    void delele(Long id);
}
