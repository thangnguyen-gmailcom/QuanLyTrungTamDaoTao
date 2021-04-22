package com.tht.demo.service.impl;

import com.tht.demo.model.Point;
import com.tht.demo.repository.PointRepository;
import com.tht.demo.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PointServiceImpl implements PointService {
    @Autowired
    private PointRepository pointRepository;


    @Override
    public Page<Point> showAll(Pageable pageable) {
        return pointRepository.findAll(pageable);
    }

    @Override
    public Optional<Point> findById(Long id) {
        return pointRepository.findById(id);
    }

    @Override
    public Optional<Point> findByStudentClassIdAndExamTypeId(Long studentClassId, Long examTypeId) {
        return pointRepository.findByStudentClassIdAndExamTypeId(studentClassId,examTypeId);
    }

    @Override
    public Point save(Point blog) {
        return pointRepository.save(blog);
    }

    @Override
    public void delele(Long id) {
        pointRepository.deleteById(id);
    }
}
