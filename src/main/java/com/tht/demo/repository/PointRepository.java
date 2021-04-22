package com.tht.demo.repository;

import com.tht.demo.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<Point,Long> {

    Optional<Point> findByStudentClassIdAndExamTypeId(Long studentClassId,Long examTypeId);
}
