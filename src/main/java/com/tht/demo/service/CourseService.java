package com.tht.demo.service;


import com.tht.demo.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Page<Course> showAll(Pageable pageable);
    List<Course> findAll();
    Optional<Course> findById(Long id);
    Course save(Course course);
    void delele(Long id);

    Page<Course> findAllByCourseNameContainingAndDeletedIsFalse(String name, Pageable pageable);
}
