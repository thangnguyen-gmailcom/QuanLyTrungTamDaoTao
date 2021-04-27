package com.tht.demo.service;

import com.tht.demo.model.Attend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AttendService {
    Page<Attend> showAll(Pageable pageable);
    Attend findByTimeTableIdAndUserId(Long timeTableId,Long userId);
    Optional<Attend> findById(Long id);
    Attend save(Attend attend);
    void delele(Long id);

}
