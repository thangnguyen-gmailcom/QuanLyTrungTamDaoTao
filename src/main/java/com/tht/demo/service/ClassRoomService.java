package com.tht.demo.service;

import com.tht.demo.model.ClassRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClassRoomService{
    Page<ClassRoom> showAll(Pageable pageable);
    Page<ClassRoom> findByTeacherId(Long id,Pageable pageable);
    Page<ClassRoom> findByStudentClassesUserId(Long id,Pageable pageable);
    Page<ClassRoom> findAllByStatusTimeTableIsTrue(Pageable pageable);
    Page<ClassRoom> findAllByStatusTimeTableIsTrueAndTeacherId(Long id,Pageable pageable);
    Page<ClassRoom> findAllByStatusTimeTableIsTrueAndStudentClassesUserId(Long id,Pageable pageable);
    List<ClassRoom> findAll();
    Optional<ClassRoom> findById(Long id);
    Optional<ClassRoom> findByName(String className);
    ClassRoom save(ClassRoom classRoom);
    void delele(Long id);

    Page<ClassRoom> findAllByClassNameContainingAndDeletedIsFalse(String name, Pageable pageable);
}
