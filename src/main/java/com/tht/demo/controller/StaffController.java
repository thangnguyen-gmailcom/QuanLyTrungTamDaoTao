package com.tht.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("")
    public String staffList(){
        return "manager-page/staff-list";
    }

    @GetMapping("/info")
    public String info(){
        return "manager-page/staff-info";
    }

    @GetMapping("/add")
    public String add(){return "manager-page/staff-add";}

    @GetMapping("/edit")
    public String edit(){return "manager-page/staff-edit";}
}
