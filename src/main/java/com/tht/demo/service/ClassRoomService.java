package com.tht.demo.service;

import com.tht.demo.model.ClassRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClassRoomService{
    Page<ClassRoom> showAll(Pageable pageable);
    List<ClassRoom> findAll();
    Optional<ClassRoom> findById(Long id);
    Optional<ClassRoom> findByName(String className);
    ClassRoom save(ClassRoom classRoom);
    void delele(Long id);
}
