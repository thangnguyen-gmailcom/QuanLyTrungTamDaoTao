package com.tht.demo.controller;


import com.tht.demo.model.Course;
import com.tht.demo.model.User;
import com.tht.demo.repository.CourseRepository;
import com.tht.demo.repository.ProgrammeRepository;
import com.tht.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private ProgrammeRepository programmeRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;


    @GetMapping("")
    public String coursesList() {
        return "manager-page/courses-list";
    }

    @GetMapping("/add")
    public String add(Model model, Pageable pageable) {

        model.addAttribute("course", new Course());
        model.addAttribute("listProgramme", programmeRepository.findAllByDeletedIsFalse(pageable));
        return "manager-page/courses-add";
    }


    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("course") Course course, BindingResult result, RedirectAttributes attributes, Model model, Pageable pageable) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("listProgramme", programmeRepository.findAllByDeletedIsFalse(pageable));
                return "manager-page/courses-add";
            }

            String name = course.getCourseName();
            Optional<Course> course1 = courseRepository.findByCourseNameAndDeletedIsFalse(name);
            if (course1 == null || course1.isEmpty()) {
                courseService.save(course);
                attributes.addFlashAttribute("mess", "Th??m m???i th??nh c??ng...!!!");
            } else {
                attributes.addFlashAttribute("error", "T??n kh??a ???? t???n t???i");
            }
            return "redirect:/courses";
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "error");
            return "redirect:/courses";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable long id, Model model, Pageable pageable) {
        Optional<Course> course = courseService.findById(id);
        if (course != null) {
            model.addAttribute("course", course);
            model.addAttribute("listProgramme", programmeRepository.findAllByDeletedIsFalse(pageable));
            return "manager-page/courses-edit";
        } else {
            return "error";
        }

    }

    @PostMapping("/edit")
    public String doEdit(@Valid @ModelAttribute("course") Course course, BindingResult result, RedirectAttributes attributes, Model model,Pageable pageable) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("listProgramme", programmeRepository.findAllByDeletedIsFalse(pageable));
                return "manager-page/courses-edit";
            }

            List<Course> courses = courseRepository.findAll();
            for (Course course1 : courses) {
                if ((course.getCourseName()).equals(course1.getCourseName()) && (course.getId()) != course1.getId()) {
                    attributes.addFlashAttribute("mess", "T??n ???? t???n t???i...!!!");
                    return "redirect:/manager-page/courses-edit";
                }
            }
            courseService.save(course);
            attributes.addFlashAttribute("mess", "Thay ?????i th??nh c??ng...!!!");
        } catch (Exception e) {
            e.getMessage();
            attributes.addFlashAttribute("mess", "Error");
        }
        return "redirect:/courses";
    }
}
