package com.tht.demo.service.impl;

import com.tht.demo.model.Programme;
import com.tht.demo.repository.ProgrammeRepository;
import com.tht.demo.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProgrammeServiceImpl implements ProgrammeService {
    @Autowired
    private ProgrammeRepository programmeRepository;

    @Override
    public Page<Programme> showAll(Pageable pageable) {
        return programmeRepository.findAll(pageable);
    }

    @Override
    public Optional<Programme> findById(Long id) {
        return programmeRepository.findById(id);
    }

    @Override
    public Programme save(Programme programme) {
        return programmeRepository.save(programme);
    }

    @Override
    public void delele(Long id) {
        programmeRepository.deleteById(id);
    }
}
