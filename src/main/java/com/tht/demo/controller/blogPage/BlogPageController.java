package com.tht.demo.controller.blogPage;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogPage")
public class BlogPageController {

    @GetMapping("")
    public String index(){return "blog-page/index";}
}
