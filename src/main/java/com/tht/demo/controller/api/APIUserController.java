package com.tht.demo.controller.api;

import com.tht.demo.dto.MyUserDetails;
import com.tht.demo.model.StudentClass;
import com.tht.demo.model.User;
import com.tht.demo.repository.UserRepository;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping(value = "api/user")
public class APIUserController {
    @Autowired
    private UserService userService;

    private static String UPLOAD_DIR = "/";

    @GetMapping("")
    public ResponseEntity<?> showAll(@RequestParam(value = "page",required = false,defaultValue = "0") int page) {
        try {
            Page<User> userList = userService.showAllEmployee(PageRequest.of(page,8));
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> findBySearch(@RequestParam("search")String email, @RequestParam(value = "page",required = false,defaultValue = "0") int page){
        try{
            Page<User> users = userService.findAllByEmail(email,PageRequest.of(page,8));
            return new ResponseEntity<>(users,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email")String email){
        try{
            Optional<User> user = userService.findByEmail(email);
            return new ResponseEntity<>(user.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String name,@PageableDefault(size = 8) Pageable pageable
    ){
        try {

            Page<User> users = userService.searchStaffByName(name,pageable);
            return new ResponseEntity<>(users,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable long id){
        try{
            Optional<User> user = userService.findStudentById(id);
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
