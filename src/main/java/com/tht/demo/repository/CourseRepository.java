package com.tht.demo.repository;

import com.tht.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course , Long> {
}
