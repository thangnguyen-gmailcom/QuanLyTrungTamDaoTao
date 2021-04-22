package com.tht.demo.service;

import com.tht.demo.model.Blog;
import com.tht.demo.model.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PointService {
    Page<Point> showAll(Pageable pageable);
    Optional<Point> findById(Long id);
    Optional<Point> findByStudentClassIdAndExamTypeId(Long studentClassId,Long examTypeId);
    Point save(Point blog);
    void delele(Long id);
}
