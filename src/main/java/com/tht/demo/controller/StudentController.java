package com.tht.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("")
    public String studentList(){return "manager-page/student-list";}

    @GetMapping("/info")
    public String info(){return "manager-page/student-info";}

    @GetMapping("/edit")
    public String edit(){return "manager-page/student-edit";}

    @GetMapping("/add")
    public String add(){return "manager-page/student-add";}

}
