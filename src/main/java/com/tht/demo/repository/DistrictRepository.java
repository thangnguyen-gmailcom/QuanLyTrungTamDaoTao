package com.tht.demo.repository;

import com.tht.demo.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District,Long> {

    @Query(value = "SELECT d FROM District d WHERE d.province.provinceCode = :provinceCode")
    List<District> findAllByProvinceProvinceCode(@Param("provinceCode") String provinceCode);
}
