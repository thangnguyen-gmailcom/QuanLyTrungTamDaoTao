package com.tht.demo.controller.api;

import com.tht.demo.model.Course;
import com.tht.demo.model.User;
import com.tht.demo.service.CourseService;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/courses")
public class APICourseController {
    @Autowired
    private CourseService courseService;


    @GetMapping("")
    public ResponseEntity<?> showAll(@RequestParam(value = "page",required = false,defaultValue = "0") int page
                                  ) {
        try {
            Page<Course> courses = courseService.showAll(PageRequest.of(page,8,Sort.by("id").descending()));
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String name,@PageableDefault(size = 8) Pageable pageable
    ){
        try {
            return new ResponseEntity<>(courseService.findAllByCourseNameContainingAndDeletedIsFalse(name,pageable), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
