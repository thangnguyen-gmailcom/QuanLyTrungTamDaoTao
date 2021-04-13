package com.tht.demo.service;

import com.tht.demo.model.ClassRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClassRoomService{
    Page<ClassRoom> showAll(Pageable pageable);
    Optional<ClassRoom> findById(Long id);
    ClassRoom save(ClassRoom classRoom);
    void delele(Long id);
}
