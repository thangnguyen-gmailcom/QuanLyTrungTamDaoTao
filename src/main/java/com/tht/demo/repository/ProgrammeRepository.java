package com.tht.demo.repository;

import com.tht.demo.model.Blog;
import com.tht.demo.model.Course;
import com.tht.demo.model.Lesson;
import com.tht.demo.model.Programme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProgrammeRepository extends JpaRepository<Programme,Long> {
    Optional<Programme> findByProgrammeNameAndDeletedIsFalse(String name);

    @Modifying
    @Query("update Programme p set p.deleted = true where p.id = :id")
    Integer softDeleteProgramme(@Param("id") Long id);

    Page<Programme> findAllByDeletedIsFalse(Pageable pageable);

    Page<Programme> findAllByProgrammeNameContainingAndDeletedIsFalse(String name, Pageable pageable);

    Page<Programme> findAllByDeletedIsTrue(Pageable pageable);

    Page<Programme> findAllByProgrammeNameContainingAndDeletedIsTrue(String name, Pageable pageable);

    @Modifying
    @Query("update Programme p set p.deleted = false where p.id = :id")
    Integer restoreProgramme(@Param("id") Long id);



}
