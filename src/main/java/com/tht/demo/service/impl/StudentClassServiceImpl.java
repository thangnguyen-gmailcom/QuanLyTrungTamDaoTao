package com.tht.demo.service.impl;

import com.tht.demo.model.StudentClass;
import com.tht.demo.repository.StudentClassRepository;
import com.tht.demo.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class StudentClassServiceImpl implements StudentClassService {

    @Autowired
    private StudentClassRepository studentClassRepository;

    @Override
    public Page<StudentClass> showAll(Pageable pageable) {
        return studentClassRepository.findAll(pageable);
    }

    @Override
    public Page<StudentClass> findAllByClassRoomIdAndUserIsDeletedIsFalse(Long id, Pageable pageable) {
        return studentClassRepository.findAllByClassRoomIdAndUserIsDeletedIsFalseAndDeletedIsFalse(id, pageable);
    }

    @Override
    public Optional<StudentClass> findByUserIdAndClassRoomIdAndDeletedIsTrue(Long userId, Long classRoomId) {
        return studentClassRepository.findByUserIdAndClassRoomIdAndDeletedIsTrue(userId,classRoomId);
    }

    @Override
    public Optional<StudentClass> findByUserIdAndClassRoomId(Long userId, Long classroomId) {
        return studentClassRepository.findByUserIdAndClassRoomId(userId,classroomId);
    }

    @Override
    public Optional<StudentClass> findById(Long id) {
        return studentClassRepository.findById(id);
    }

    @Override
    public StudentClass save(StudentClass studentClass) {
        return studentClassRepository.save(studentClass);
    }

    @Override
    public void delete(Long id) {
        studentClassRepository.deletedStudentClass(id);
    }
}
