package com.tht.demo.controller.blogPage;


import com.tht.demo.model.Banner;
import com.tht.demo.model.Programme;
import com.tht.demo.model.User;
import com.tht.demo.service.BannerService;
import com.tht.demo.service.BlogService;
import com.tht.demo.service.ProgrammeService;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/blogPage")
public class BlogPageController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private ProgrammeService programmeService;
    @GetMapping("")
    public String index(Model model, Pageable pageable, HttpSession session){
        Page<User> teacherList = userService.showAllTeacher(pageable);
        Page<Programme> programmeList = programmeService.showAll(pageable);
        Page<Banner> bannerList = bannerService.findLimit(pageable);
        System.out.println(bannerList);
        model.addAttribute("bannerList",bannerList);
        model.addAttribute("programmeList",programmeList);
        model.addAttribute("teacherList",teacherList);
        return "index";

    }

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
