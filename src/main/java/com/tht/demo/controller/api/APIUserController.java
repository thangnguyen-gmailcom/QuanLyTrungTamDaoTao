package com.tht.demo.controller.api;

import com.tht.demo.model.User;
import com.tht.demo.repository.UserRepository;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/user")
public class APIUserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> showAll(@RequestParam(value = "page",required = false,defaultValue = "0") int page) {
        try {
            Page<User> userList = userService.showAllEmployee(PageRequest.of(page,8));
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try {
            userService.delele(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
