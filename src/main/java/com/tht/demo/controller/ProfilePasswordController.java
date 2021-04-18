package com.tht.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profilePassword")
public class ProfilePasswordController {

    @GetMapping("")
    public String profilePassword(){return "manager-page/profile-password";}
}
