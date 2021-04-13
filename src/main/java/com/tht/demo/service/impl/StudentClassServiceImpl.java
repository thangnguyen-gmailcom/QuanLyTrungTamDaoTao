package com.tht.demo.service.impl;

import com.tht.demo.model.StudentClass;
import com.tht.demo.repository.StudentClassRepository;
import com.tht.demo.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentClassServiceImpl implements StudentClassService {

    @Autowired
    private StudentClassRepository studentClassRepository;

    @Override
    public Page<StudentClass> showAll(Pageable pageable) {
        return studentClassRepository.findAll(pageable);
    }

    @Override
    public Optional<StudentClass> findById(Long id) {
        return studentClassRepository.findById(id);
    }

    @Override
    public StudentClass save(StudentClass studentClass) {
        return studentClassRepository.save(studentClass);
    }

    @Override
    public void delele(Long id) {
        studentClassRepository.deleteById(id);
    }
}
