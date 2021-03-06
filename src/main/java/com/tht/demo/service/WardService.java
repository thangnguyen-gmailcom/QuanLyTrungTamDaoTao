package com.tht.demo.service;

import com.tht.demo.model.Blog;
import com.tht.demo.model.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface WardService {
    List<Ward> showAll(String districtCode);
    Optional<Ward> findByWardCode(String wardCode);
    Ward save(Ward ward);
//    void delele(Long id);
}
