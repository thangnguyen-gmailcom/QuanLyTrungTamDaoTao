package com.tht.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

    @GetMapping("")
    public String timeTableList(){return "manager-page/timetable-list";}

    @GetMapping("/view")
    public String view(){return "manager-page/timetable-view";}

    @GetMapping("/viewDetail")
    public String viewsDetail(){return "manager-page/timetable-viewDetail";}

    @GetMapping("/add")
    public String add(){return "manager-page/timetable-add";}

    @GetMapping("/edit")
    public String edit(){return "manager-page/timetable-edit";}
}