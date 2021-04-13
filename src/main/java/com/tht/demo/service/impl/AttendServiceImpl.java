package com.tht.demo.service.impl;

import com.tht.demo.model.Attend;
import com.tht.demo.repository.AttendRepository;
import com.tht.demo.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AttendServiceImpl implements AttendService {
    @Autowired
    private AttendRepository attendRepository;

    @Override
    public Page<Attend> showAll(Pageable pageable) {
        return attendRepository.findAll(pageable);
    }

    @Override
    public Optional<Attend> findById(Long id) {
        return attendRepository.findById(id);
    }

    @Override
    public Attend save(Attend attend) {
        return attendRepository.save(attend);
    }

    @Override
    public void delele(Long id) {
          attendRepository.deleteById(id);
    }
}
