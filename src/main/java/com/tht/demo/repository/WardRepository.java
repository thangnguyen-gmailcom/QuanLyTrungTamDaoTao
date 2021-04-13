package com.tht.demo.repository;

import com.tht.demo.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward,Long> {
}
