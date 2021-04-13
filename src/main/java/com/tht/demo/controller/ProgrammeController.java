package com.tht.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/programme")
public class ProgrammeController {


    @GetMapping("")
    public String programmeList(){
        return "manager-page/programme-list";
    }



    @GetMapping("/info")
    public String info(){
        return "manager-page/programme-info";
    }

    @GetMapping("/add")
    public String add(){return "manager-page/programme-add";}

    @GetMapping("/edit")
    public String showEdit(){return "manager-page/programme-edit";}

    @GetMapping("/delete")
    public String delete(){return "";}
}

