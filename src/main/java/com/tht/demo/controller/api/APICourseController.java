package com.tht.demo.controller.api;

import com.tht.demo.model.Course;
import com.tht.demo.model.User;
import com.tht.demo.service.CourseService;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/courses")
public class APICourseController {
    @Autowired
    private CourseService courseService;


    @GetMapping("")
    public ResponseEntity<?> showAll(Pageable pageable) {
        try {
            Page<Course> courses = courseService.showAll(pageable);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try {
            courseService.delele(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
