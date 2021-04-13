package com.tht.demo.service.impl;

import com.tht.demo.model.ClassRoom;
import com.tht.demo.repository.ClassRoomRepository;
import com.tht.demo.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {
    @Autowired
    private ClassRoomRepository classRoomRepository;


    @Override
    public Page<ClassRoom> showAll(Pageable pageable) {
        return classRoomRepository.findAll(pageable);
    }

    @Override
    public Optional<ClassRoom> findById(Long id) {
        return classRoomRepository.findById(id);
    }

    @Override
    public ClassRoom save(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    @Override
    public void delele(Long id) {
        classRoomRepository.deleteById(id);
    }
}
