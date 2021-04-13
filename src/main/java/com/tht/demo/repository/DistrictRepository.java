package com.tht.demo.repository;

import com.tht.demo.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District,Long> {
}
