package com.tht.demo.repository;

import com.tht.demo.model.Course;
import com.tht.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course , Long> {
    Optional<Course> findByCourseNameAndDeletedIsFalse(String name);

    @Modifying
    @Query("update Course c set c.deleted = true where c.id = :id")
    Integer softDeleteCourse(@Param("id") Long id);

    Page<Course> findAllByDeletedIsFalse(Pageable pageable);

    List<Course> findAllByDeletedIsFalse();

    Page<Course> findAllByCourseNameContainingAndDeletedIsFalse(String name, Pageable pageable);
}
