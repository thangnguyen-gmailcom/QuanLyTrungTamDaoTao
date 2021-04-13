package com.tht.demo.service;

import com.tht.demo.model.Blog;
import com.tht.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Page<User> showAll(Pageable pageable);
    Optional<User> findById(Long id);
    User save(User user);
    void delele(Long id);
}
