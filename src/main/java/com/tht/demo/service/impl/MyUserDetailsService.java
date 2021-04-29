package com.tht.demo.service.impl;

import com.tht.demo.dto.MyUserDetails;
import com.tht.demo.model.User;
import com.tht.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findByEmailAndIsDeletedIsFalse(email);
        return user.map(MyUserDetails::new).get();
    }
}
