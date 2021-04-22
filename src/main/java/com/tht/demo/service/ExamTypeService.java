package com.tht.demo.service;

import com.tht.demo.model.Blog;
import com.tht.demo.model.ExamType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ExamTypeService {
    Page<ExamType> showAll(Pageable pageable);
    Optional<ExamType> findById(Long id);
    ExamType save(ExamType examType);
    void delete(Long id);
    Optional<ExamType> findByTitle(String name);

    Page<ExamType> findAllByTitleContainingAndDeletedIsFalse(String name, Pageable pageable);

}
