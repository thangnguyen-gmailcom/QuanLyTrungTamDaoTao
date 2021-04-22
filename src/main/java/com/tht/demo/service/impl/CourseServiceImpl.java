package com.tht.demo.service.impl;

import com.tht.demo.model.Course;
import com.tht.demo.repository.ClassRoomRepository;
import com.tht.demo.repository.CourseRepository;
import com.tht.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private ClassRoomRepository classRoomRepository;


    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Page<Course> showAll(Pageable pageable) {
        return courseRepository.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAllByDeletedIsFalse();
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
    @Transactional
    public void delele(Long id) {

        boolean result = courseRepository.softDeleteCourse(id) > 0;
        if (!result)
            throw new RuntimeException("course not found");
        classRoomRepository.softDeleteClassRoomByCourseId(id);

//        courseRepository.deleteById(id
//        );
    }

    @Override
    public Page<Course> findAllByCourseNameContainingAndDeletedIsFalse(String name, Pageable pageable) {
        return courseRepository.findAllByCourseNameContainingAndDeletedIsFalse(name,pageable);
    }
}
