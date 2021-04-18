package com.tht.demo.repository;

import com.tht.demo.model.District;
import com.tht.demo.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WardRepository extends JpaRepository<Ward,String> {

    @Query(value = "SELECT w FROM Ward w WHERE w.district.districtCode = :districtCode")
    List<Ward> findAllByDistrictDistrictCode(@Param("districtCode") String districtCode);

    Optional<Ward> findByWardCode(String wardCode);
}
