package com.tht.demo.controller.api;

import com.tht.demo.model.StudentClass;
import com.tht.demo.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/studentClass")
public class APIStudentClassController {

    @Autowired
    StudentClassService studentClassService;

    @PostMapping(value = "")
    public ResponseEntity<?> saveStudentClass(@RequestBody StudentClass studentClass) {
        try {
            Optional<StudentClass> studentClass1 = studentClassService.findByUserIdAndClassRoomIdAndDeletedIsFalse(studentClass.getUser().getId(),studentClass.getClassRoom().getId());
            if(studentClass1.isPresent()) {
                StudentClass studentClass2 = studentClassService.save(studentClass);
                return new ResponseEntity<>(studentClass2, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAllStudentByClass(@PathVariable("id") long id,@RequestParam(value = "page",required = false,defaultValue = "0") int page){
        try{
            Page<StudentClass> studentClasses = studentClassService.findAllByClassRoomIdAndUserIsDeletedIsFalse(id, PageRequest.of(page,8));
            return new ResponseEntity<>(studentClasses,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> deleteStudentClass(@PathVariable("id") long id){
        try {
            studentClassService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
