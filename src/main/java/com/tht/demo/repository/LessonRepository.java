package com.tht.demo.repository;

import com.tht.demo.model.ClassRoom;
import com.tht.demo.model.Course;
import com.tht.demo.model.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson,Long> {
   Optional<Lesson> findByContent(String content);
    @Modifying
    @Query("update Lesson l set l.deleted = true where l.programme.id = :programmeId")
    Integer softDeleteLessonByProgrammeId(@Param("programmeId") long programmeId);

    @Modifying
    @Query("update Lesson l set l.deleted = true where l.id = :id")
    void softDeleteLesson(@Param("id") Long id);


    Page<Lesson> findAllByDeletedIsFalse(Pageable pageable);

    Page<Lesson> findAllByProgrammeIdAndDeletedIsFalse(long id,Pageable pageable);

}
