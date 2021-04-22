package com.tht.demo.controller.api;

import com.tht.demo.model.ExamType;
import com.tht.demo.model.Point;
import com.tht.demo.model.StudentClass;
import com.tht.demo.dto.SavePoint;
import com.tht.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="api/point")
public class APIPointController {

    @Autowired
    private PointService pointService;

    @Autowired
    private StudentClassService studentClassService;

    @Autowired
    private ExamTypeService examTypeService;


    @PostMapping("/")
    public ResponseEntity<?> savePoint(@RequestBody SavePoint savePoint){
        StudentClass studentClass = studentClassService.findById(savePoint.getIdStudentClass()).orElse(null);
        List<Float> points = savePoint.getPoints();
        float avg = 0;
        for (int i = 0; i < points.size() ; i++) {
            if (points.get(i)> 10||points.get(i)<0){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Point point = new Point();
            ExamType examType = examTypeService.findById(savePoint.getExamTypes().get(i)).orElse(null);
            point.setPoint(points.get(i));
            point.setStudentClass(studentClass);
            point.setExamType(examType);
            avg+= points.get(i);
            Optional<Point> pointCheck = pointService.findByStudentClassIdAndExamTypeId(studentClass.getId(), examType.getId());
            if(pointCheck.isPresent()){
                pointCheck.get().setPoint(points.get(i));
                pointService.save(pointCheck.get());
            }else {
                pointService.save(point);
            }
        }
        studentClass.setAvgPoints(avg/points.size());
        studentClassService.save(studentClass);
        return new ResponseEntity<>(savePoint, HttpStatus.OK);
    }
}
