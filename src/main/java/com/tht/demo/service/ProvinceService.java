package com.tht.demo.service;

import com.tht.demo.model.Blog;
import com.tht.demo.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProvinceService {
    Page<Province> showAll(Pageable pageable);
    Optional<Province> findById(Long id);
    Province save(Province province);
    void delele(Long id);
}
