package com.tht.demo.service.impl;

import com.tht.demo.model.Course;
import com.tht.demo.repository.CourseRepository;
import com.tht.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Page<Course> showAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void delele(Long id) {
        courseRepository.deleteById(id);
    }
}
