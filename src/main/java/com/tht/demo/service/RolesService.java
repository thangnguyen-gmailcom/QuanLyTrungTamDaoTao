package com.tht.demo.service;


import com.tht.demo.model.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RolesService {
    List<Roles> showAll();
    Optional<Roles> findById(Long id);
    Roles save(Roles roles);
    void delele(Long id);
}
