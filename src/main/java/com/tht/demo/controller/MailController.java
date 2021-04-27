    package com.tht.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail")
public class MailController {

    @GetMapping("/staff")
    public String mailStaff(){return "manager-page/mail-staff";}

    @GetMapping("/student")
    public String mailStudent(){return "manager-page/mail-student";}

    @GetMapping("/user")
    public String mailUser(){return "manager-page/mail-user";}
}
