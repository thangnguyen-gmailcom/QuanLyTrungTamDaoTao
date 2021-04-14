package com.tht.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/class")
public class ClassroomController {

    @GetMapping("")
    public String classList(){
        return "manager-page/class-list";
    }

    @GetMapping("/add")
    public String add(){return "manager-page/class-add";}

    @GetMapping("/edit")
    public String edit(){return "manager-page/class-edit";}

    @GetMapping("/view")
    public String view(){return "manager-page/class-view";}
}
