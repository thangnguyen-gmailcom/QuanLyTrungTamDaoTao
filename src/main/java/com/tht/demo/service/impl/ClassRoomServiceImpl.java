package com.tht.demo.service.impl;

import com.tht.demo.model.ClassRoom;
import com.tht.demo.repository.ClassRoomRepository;
import com.tht.demo.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClassRoomServiceImpl implements ClassRoomService {
    @Autowired
    private ClassRoomRepository classRoomRepository;


    @Override
    public Page<ClassRoom> showAll(Pageable pageable) {
        return classRoomRepository.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public List<ClassRoom> findAll() {
        return classRoomRepository.findAllByDeletedIsFalse();
    }

    @Override
    public Optional<ClassRoom> findById(Long id) {
        return classRoomRepository.findById(id);
    }

    @Override
    public Optional<ClassRoom> findByName(String className) {
        return classRoomRepository.findByClassName(className);
    }

    @Override
    public ClassRoom save(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    @Override
    @Transactional
    public void delele(Long id) {
        classRoomRepository.softDeleteClassRoom(id);
    }

    @Override
    public Page<ClassRoom> findAllByClassNameContainingAndDeletedIsFalse(String name, Pageable pageable) {
        return classRoomRepository.findAllByClassNameContainingAndDeletedIsFalse(name,pageable);
    }
}
