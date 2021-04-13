package com.tht.demo.service.impl;

import com.tht.demo.model.ExamType;
import com.tht.demo.repository.ExamTypeRepository;
import com.tht.demo.service.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamTypeServiceImpl implements ExamTypeService {
    @Autowired
    private ExamTypeRepository examTypeRepository;


    @Override
    public Page<ExamType> showAll(Pageable pageable) {
        return examTypeRepository.findAll(pageable);
    }

    @Override
    public Optional<ExamType> findById(Long id) {
        return examTypeRepository.findById(id);
    }

    @Override
    public ExamType save(ExamType examType) {
        return examTypeRepository.save(examType);
    }

    @Override
    public void delele(Long id) {
        examTypeRepository.deleteById(id);
    }
}
