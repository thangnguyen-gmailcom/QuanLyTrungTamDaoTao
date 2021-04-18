package com.tht.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profileEdit")
public class ProfileEditController {


    @GetMapping("")
    public String profileEdit(){return "manager-page/profile-edit";}
}
