package com.tht.demo.service;

import com.tht.demo.model.Blog;
import com.tht.demo.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProvinceService {
    List<Province> showAll();
//    Optional<Province> findById(Long id);
    Province save(Province province);
//    void delele(Long id);
}
