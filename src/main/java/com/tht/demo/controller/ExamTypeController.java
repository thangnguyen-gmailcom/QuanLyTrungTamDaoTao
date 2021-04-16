package com.tht.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exampleType")
public class ExamTypeController {

    @GetMapping("")
    public String exampleTypeList(){return "manager-page/example-type-list";}

    @GetMapping("/add")
    public String add(){return "manager-page/example-type-add";}

    @GetMapping("/edit")
    public String edit(){return "manager-page/example-type-edit";}
}
