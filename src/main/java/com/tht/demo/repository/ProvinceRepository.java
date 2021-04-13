package com.tht.demo.repository;

import com.tht.demo.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province,Long> {
}
