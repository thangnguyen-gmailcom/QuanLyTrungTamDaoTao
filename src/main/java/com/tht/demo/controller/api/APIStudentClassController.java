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
            long userId = studentClass.getUser().getId();
            long classId = studentClass.getClassRoom().getId();
            StudentClass studentClass1 = null;
            Optional<StudentClass> optionalStudentClass = studentClassService.findByUserIdAndClassRoomId(userId,classId);
            if(optionalStudentClass.isEmpty()){
                studentClass1 = studentClassService.save(studentClass);
                return new ResponseEntity<>(studentClass1, HttpStatus.OK);
            }else {
                Optional<StudentClass> studentClassOptional = studentClassService.findByUserIdAndClassRoomIdAndDeletedIsTrue(userId,classId);
                if(studentClassOptional.isPresent()) {
                    studentClassOptional.get().setDeleted(false);
                    studentClass1 = studentClassService.save(studentClassOptional.get());
                    return new ResponseEntity<>(studentClass1, HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
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

    @GetMapping("")
    public ResponseEntity<?> findStudentClassById(@RequestParam("id")long id){
        try{
            Optional<StudentClass> studentClass = studentClassService.findById(id);
            return new ResponseEntity<>(studentClass.get(), HttpStatus.OK);
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
