package com.tht.demo.repository;

import com.tht.demo.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point,Long> {
}
