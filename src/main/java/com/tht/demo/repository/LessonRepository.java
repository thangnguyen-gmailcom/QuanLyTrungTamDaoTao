package com.tht.demo.repository;

import com.tht.demo.model.ClassRoom;
import com.tht.demo.model.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LessonRepository extends JpaRepository<Lesson,Long> {

    @Modifying
    @Query("update Lesson l set l.deleted = true where l.programme.id = :programmeId")
    Integer softDeleteLessonByProgrammeId(@Param("programmeId") long programmeId);

    @Modifying
    @Query("update Lesson l set l.deleted = true where l.id = :id")
    Integer softDeleteLesson(@Param("id") Long id);

    Page<Lesson> findAllByDeletedIsFalse(Pageable pageable);
}
