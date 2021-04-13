package com.tht.demo.service;


import com.tht.demo.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CourseService {
    Page<Course> showAll(Pageable pageable);
    Optional<Course> findById(Long id);
    Course save(Course course);
    void delele(Long id);
}
