package com.tht.demo.service.impl;

import com.tht.demo.model.District;
import com.tht.demo.repository.DistrictRepository;
import com.tht.demo.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;


    @Override
    public Page<District> showAll(Pageable pageable) {
        return districtRepository.findAll(pageable);
    }

    @Override
    public Optional<District> findById(Long id) {
        return districtRepository.findById(id);
    }

    @Override
    public District save(District district) {
        return districtRepository.save(district);
    }

    @Override
    public void delele(Long id) {
        districtRepository.deleteById(id);
    }
}
