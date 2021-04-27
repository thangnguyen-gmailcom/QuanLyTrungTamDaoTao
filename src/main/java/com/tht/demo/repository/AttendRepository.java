package com.tht.demo.repository;

import com.tht.demo.model.Attend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendRepository extends JpaRepository<Attend , Long> {

    Attend findByTimeTableIdAndUserId(Long timeTableId,Long userId);
}
