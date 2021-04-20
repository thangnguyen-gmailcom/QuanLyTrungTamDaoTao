package com.tht.demo.controller.blogPage;


import com.tht.demo.model.Blog;
import com.tht.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/blogPage")
public class BlogPageController {

    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public String index(){return "blog-page/index";}

    @GetMapping("/blog")
    public String blog(){
        return "blog-page/blog-page";
    }

    @GetMapping("/blogSingle/{id}")
    public String blogSingle(@PathVariable long id,Model model){
    model.addAttribute("post",blogService.findById(id).get());
    return "blog-page/blog-single";
    }
}
