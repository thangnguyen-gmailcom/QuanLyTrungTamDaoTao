package com.tht.demo.repository;

import com.tht.demo.model.Course;
import com.tht.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course , Long> {

    Optional<Course> findByCourseName(String name);
}
