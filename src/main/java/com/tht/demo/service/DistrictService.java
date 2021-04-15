package com.tht.demo.service;

import com.tht.demo.model.Blog;
import com.tht.demo.model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DistrictService {
    List<District> showAll(String provinceCode);
    Optional<District> findById(Long id);
    District save(District district);
    void delele(Long id);
}
