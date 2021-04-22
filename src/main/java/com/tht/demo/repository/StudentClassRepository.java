package com.tht.demo.repository;


import com.tht.demo.model.StudentClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface StudentClassRepository extends JpaRepository<StudentClass,Long> {

    Page<StudentClass> findAllByClassRoomIdAndUserIsDeletedIsFalseAndDeletedIsFalse(Long classroomId, Pageable pageable);

    Optional<StudentClass> findByUserIdAndClassRoomIdAndDeletedIsFalse(Long userId,Long classRoomId);

    @Modifying
    @Query(value = "UPDATE StudentClass sc SET sc.deleted = true WHERE sc.user.id = :id")
    void deletedStudentClass(@Param("id") Long id);
}
