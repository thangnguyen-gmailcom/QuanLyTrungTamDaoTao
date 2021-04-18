package com.tht.demo.controller.api;

import com.tht.demo.model.User;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/students")
public class APIStudentController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> showAll(@RequestParam(value = "page",required = false,defaultValue = "0") int page) {
        try {
            Page<User> userList = userService.showAllStudent(PageRequest.of(page,8));
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable long id){
        try{
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
