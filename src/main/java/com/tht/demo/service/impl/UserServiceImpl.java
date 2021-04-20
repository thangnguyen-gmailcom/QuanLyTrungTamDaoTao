package com.tht.demo.service.impl;

import com.tht.demo.model.User;
import com.tht.demo.repository.UserRepository;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public Page<User> showAllEmployee(Pageable pageable) {
        return userRepository.findAllEmployeeOrderByIdDesc(pageable);
    }

    @Override
    public List<User> findAllTeacher() {
        return userRepository.findAllTeacherOrderByIdDesc();
    }

    @Override
    public Page<User> showAllStudent(Pageable pageable) {
        return userRepository.findAllStudentOrderByIdDesc(pageable);
    }

    @Override
    public Page<User> findAllByEmail(String email, Pageable pageable) {
        return userRepository.findAllByEmail(email,pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findStaffById(id);
    }

    @Override
    public Optional<User> findStudentById(Long id) {
        return userRepository.findStudentById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deletedUser(id);
    }


    @Override
    public Optional<User> findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<User> findByIdCard(String idCard) {
        return userRepository.findByIdCard(idCard);
    }

    @Override
    public void updatePassword(String password, Long id) {
        userRepository.updatePassword(password,id);
    }
}
