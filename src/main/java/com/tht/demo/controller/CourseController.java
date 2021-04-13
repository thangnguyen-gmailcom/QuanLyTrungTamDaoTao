package com.tht.demo.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {

    @GetMapping("")
    public String coursesList(){
        return "manager-page/courses-list";
    }

    @GetMapping("/add")
    public String add(){return "manager-page/courses-add";}

    @GetMapping("/edit")
    public String showEdit(){return "manager-page/courses-edit";}
}
