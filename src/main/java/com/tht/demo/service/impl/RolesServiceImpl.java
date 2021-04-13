package com.tht.demo.service.impl;

import com.tht.demo.model.Roles;
import com.tht.demo.repository.RolesRepository;
import com.tht.demo.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public Page<Roles> showAll(Pageable pageable) {
        return rolesRepository.findAll(pageable);
    }

    @Override
    public Optional<Roles> findById(Long id) {
        return rolesRepository.findById(id);
    }

    @Override
    public Roles save(Roles roles) {
        return rolesRepository.save(roles);
    }

    @Override
    public void delele(Long id) {
        rolesRepository.deleteById(id);
    }
}
