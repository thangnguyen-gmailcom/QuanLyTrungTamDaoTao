package com.tht.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogManager")
public class BlogManagerController {


    @GetMapping("")
    public String blogManagerList(){return "manager-page/blog-manager-list";}

    @GetMapping("/add")
    public String add(){return "manager-page/blog-manager-add";}

    @GetMapping("/edit")
    public String edit(){return "manager-page/blog-manager-edit";}
}
