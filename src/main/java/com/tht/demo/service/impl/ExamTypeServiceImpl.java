package com.tht.demo.service.impl;

import com.tht.demo.model.ExamType;
import com.tht.demo.repository.ExamTypeRepository;
import com.tht.demo.service.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ExamTypeServiceImpl implements ExamTypeService {
    @Autowired
    private ExamTypeRepository examTypeRepository;


    @Override
    public Page<ExamType> showAll(Pageable pageable) {
        return examTypeRepository.findAllByDeletedIsFalse(pageable);
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
    public Optional<ExamType> findByTitle(String name) {
        return examTypeRepository.findByTitle(name);
    }

    @Override
    public Page<ExamType> findAllByTitleContainingAndDeletedIsFalse(String name, Pageable pageable) {
        return examTypeRepository.findAllByTitleContainingAndDeletedIsFalse(name,pageable);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        examTypeRepository.softDeleteExamType(id);
    }
}
