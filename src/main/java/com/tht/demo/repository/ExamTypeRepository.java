package com.tht.demo.repository;

import com.tht.demo.model.Course;
import com.tht.demo.model.ExamType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExamTypeRepository extends JpaRepository<ExamType , Long> {
    Optional<ExamType> findByTitleAndDeletedIsFalse(String name);

    @Modifying
    @Query("update ExamType e set e.deleted = true where e.id = :id")
    void softDeleteExamType(@Param("id") Long id);

    Page<ExamType> findAllByDeletedIsFalse(Pageable pageable);

    Page<ExamType> findAllByTitleContainingAndDeletedIsFalse(String name, Pageable pageable);
}
