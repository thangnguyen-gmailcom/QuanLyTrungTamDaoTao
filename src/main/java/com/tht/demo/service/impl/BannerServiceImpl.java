package com.tht.demo.service.impl;


import com.tht.demo.model.Banner;
import com.tht.demo.repository.BannerRepository;
import com.tht.demo.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Page<Banner> showAll(Pageable pageable) {
        return bannerRepository.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public Optional<Banner> findById(Long id) {
        return  bannerRepository.findById(id);
    }

    @Override
    public Banner save(Banner banner) {
        return bannerRepository.save(banner);
    }

    @Override
    public void delete(Long id) {
        bannerRepository.softDelete(id);
    }

    @Override
    public Page<Banner> search(String description, Pageable pageable) {
        return bannerRepository.findAllByDescriptionContainingAndDeletedIsFalse(description,pageable);
    }

    @Override
    @Transactional
    public Page<Banner> findLimit(Pageable pageable) {
        return bannerRepository.findTop3ByDeletedIsFalseOrderByIdDesc(pageable);
    }
}
