package com.tht.demo.service;


import com.tht.demo.model.StudentClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudentClassService {
    Page<StudentClass> showAll(Pageable pageable);
    Optional<StudentClass> findById(Long id);
    StudentClass save(StudentClass studentClass);
    void delele(Long id);
}
