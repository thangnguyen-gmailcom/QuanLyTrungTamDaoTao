package com.tht.demo.service.impl;

import com.tht.demo.model.User;
import com.tht.demo.repository.UserRepository;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public Page<User> showAllEmployee(Pageable pageable) {
        return userRepository.findAllEmployeeOrderByIdDesc(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delele(Long id) {
        userRepository.deleteById(id);
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
}
