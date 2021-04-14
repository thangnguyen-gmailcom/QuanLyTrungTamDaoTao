package com.tht.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lesson")
public class LessonController {

    @GetMapping("")
    public String lessonList(){return "manager-page/lesson-programme-list";}

    @GetMapping("/list")
    public String viewsList(){return "manager-page/lesson-list";}

    @GetMapping("/add")
    public String add(){return "manager-page/lesson-add";}

    @GetMapping("/editAll")
    public String editAll(){return "manager-page/lesson-edit-all";}

    @GetMapping("/edit")
    public String edit(){return "manager-page/lesson-edit";}
}
