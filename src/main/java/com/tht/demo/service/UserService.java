package com.tht.demo.service;

import com.tht.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Page<User> showAllEmployee(Pageable pageable);
    Page<User> showAllStudent(Pageable pageable);
    Optional<User> findById(Long id);
    Optional<User> findStudentById(Long id);
    User save(User user);
    void delete(Long id);
    Optional<User> findByEmail(String userEmail);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByIdCard(String idCard);
    void updatePassword(String password, Long id);
}
