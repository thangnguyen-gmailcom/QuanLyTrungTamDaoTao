package com.tht.demo.repository;

import com.tht.demo.model.ClassRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassRoomRepository extends JpaRepository<ClassRoom , Long> {
    @Modifying
    @Query("update ClassRoom c set c.deleted = true where c.course.id = :courseId")
    Integer softDeleteClassRoomByCourseId(@Param("courseId") long courseId);

    @Modifying
    @Query("update ClassRoom c set c.deleted = true where c.id = :id")
    Integer softDeleteClassRoom(@Param("id") Long id);

    Page<ClassRoom> findAllByDeletedIsFalse(Pageable pageable);



}
