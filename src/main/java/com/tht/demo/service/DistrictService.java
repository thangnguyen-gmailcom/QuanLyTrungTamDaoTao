package com.tht.demo.service;

import com.tht.demo.model.Blog;
import com.tht.demo.model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DistrictService {
    Page<District> showAll(Pageable pageable);
    Optional<District> findById(Long id);
    District save(District district);
    void delele(Long id);
}
