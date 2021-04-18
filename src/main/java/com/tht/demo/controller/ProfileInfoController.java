package com.tht.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profileInfo")
public class ProfileInfoController {

    @GetMapping("")
    public String info(){return "manager-page/profile-info";}
}
