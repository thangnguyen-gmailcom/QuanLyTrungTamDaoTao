package com.tht.demo.service.impl;

import com.tht.demo.model.Ward;
import com.tht.demo.repository.WardRepository;
import com.tht.demo.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WardServiceImpl implements WardService {
    @Autowired
    private WardRepository wardRepository;

    @Override
    public List<Ward> showAll(String districtCode) {
        return wardRepository.findAllByDistrictDistrictCode(districtCode);
    }

    @Override
    public Optional<Ward> findByWardCode(String wardCode) {
        return wardRepository.findByWardCode(wardCode);
    }

    @Override
    public Ward save(Ward ward) {
        return wardRepository.save(ward);
    }

//    @Override
//    public void delele(Long id) {
//        wardRepository.deleteById(id);
//    }
}
