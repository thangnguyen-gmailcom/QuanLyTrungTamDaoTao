package com.tht.demo.service.impl;

import com.tht.demo.model.Province;
import com.tht.demo.repository.ProvinceRepository;
import com.tht.demo.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Province> showAll() {
        return provinceRepository.findAll();
    }

//    @Override
//    public Optional<Province> findById(Long id) {
//        return provinceRepository.findById(id);
//    }

    @Override
    public Province save(Province province) {
        return provinceRepository.save(province);
    }

//    @Override
//    public void delele(Long id) {
//        provinceRepository.deleteById(id);
//    }
}
