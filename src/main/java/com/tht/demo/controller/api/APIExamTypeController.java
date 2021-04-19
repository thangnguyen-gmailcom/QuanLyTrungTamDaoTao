package com.tht.demo.controller.api;

import com.tht.demo.model.Course;
import com.tht.demo.model.ExamType;
import com.tht.demo.service.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/examTypes")
public class APIExamTypeController {
    @Autowired
    private ExamTypeService examTypeService;


    @GetMapping("")
    public ResponseEntity<?> showAll(@RequestParam(value = "page",required = false,defaultValue = "0") int page
    ) {
        try {
            Page<ExamType> examTypes = examTypeService.showAll(PageRequest.of(page,8));
            return new ResponseEntity<>(examTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try {
            examTypeService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
