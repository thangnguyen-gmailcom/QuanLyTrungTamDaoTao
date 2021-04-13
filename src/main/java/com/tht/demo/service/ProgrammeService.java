package com.tht.demo.service;


import com.tht.demo.model.Programme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProgrammeService {
    Page<Programme> showAll(Pageable pageable);
    Optional<Programme> findById(Long id);
    Programme save(Programme programme);
    void delele(Long id);
}
