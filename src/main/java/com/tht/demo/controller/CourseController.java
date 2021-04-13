package com.tht.demo.controller;


import com.tht.demo.model.Course;
import com.tht.demo.model.User;
import com.tht.demo.repository.CourseRepository;
import com.tht.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;

    //
//    @GetMapping("")
//    public String coursesList() {
//        return "manager-page/courses-list";
//    }
    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("list", new Course());
        return "manager-page/courses-add";
    }


    @PostMapping("/add")
    public String doAdd(@ModelAttribute("course") Course course, RedirectAttributes attributes) {
        try {
            String name = course.getCourseName();
            Optional<Course> course1 = courseRepository.findByCourseName(name);
            if (course1 == null || course1.isEmpty()) {
                courseService.save(course);
                attributes.addFlashAttribute("mess", "Thêm mới thành công...!!!");
            } else {
                attributes.addFlashAttribute("mess", "Tên khóa đã tồn tại");
            }
            return "redirect:/manager-page/courses-add";
        } catch (Exception e) {
            attributes.addFlashAttribute("mess", "error");
            return "redirect:/.....";
        }
    }

    @GetMapping("/edit{id}")
    public String showEdit(@PathVariable long id, Model model) {
        Optional<Course> course = courseService.findById(id);
        if (course != null) {
            model.addAttribute("list", course);
            return "manager-page/courses-edit";
        } else {
            return "error";
        }

    }
        @PostMapping("/edit")
    public String doEdit(@ModelAttribute("list") Course course, RedirectAttributes attributes) {
        try {

            List<Course> courses = courseRepository.findAll();
            for (Course course1 : courses) {
                if ((course.getCourseName()).equals(course1.getCourseName()) && (course.getId()) != course1.getId()) {
                    attributes.addFlashAttribute("mess", "Tên đã tồn tại...!!!");
                    return "redirect:/manager-page/courses-edit";
                }
            }
            courseService.save(course);
            attributes.addFlashAttribute("mess", "Thay đổi thành công...!!!");
        } catch (Exception e) {
            e.getMessage();
            attributes.addFlashAttribute("mess", "Error");
        }
        return "redirect:/";
}
}
