package com.tht.demo.controller;


import com.tht.demo.model.Course;
import com.tht.demo.model.Lesson;
import com.tht.demo.model.Programme;
import com.tht.demo.repository.LessonRepository;
import com.tht.demo.repository.ProgrammeRepository;
import com.tht.demo.service.LessonService;
import com.tht.demo.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lesson")
public class LessonController {
@Autowired
private LessonService lessonService;
@Autowired
private LessonRepository lessonRepository;

@Autowired
private ProgrammeService programmeService;
    @GetMapping("")
    public String lessonList() {
        return "manager-page/lesson-programme-list";
    }

    @GetMapping("/list/{id}")
    public String viewsList(@PathVariable long id, Model model) {
        Optional<Programme> programme = programmeService.findById(id);
        if (programme != null) {
            model.addAttribute("lesson", new Lesson());
            model.addAttribute("programmes",programme.get());
            return "manager-page/lesson-list";
        }else {
            return "error";
        }
    }

    @GetMapping("/add")
    public String add(Model model, Pageable pageable) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("listProgramme", programmeService.findAllByDeletedIsFalse(pageable));
        return "manager-page/lesson-add";
    }

    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("lesson") Lesson lesson , BindingResult result,@RequestParam("programme.id") long id, RedirectAttributes attributes, Model model, Pageable pageable) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("listProgramme", programmeService.findAllByDeletedIsFalse(pageable));
                return "manager-page/lesson-add";
            }

            String name = lesson.getContent();
            Optional<Lesson> lesson1 = lessonService.findByContent(name);
            if (lesson1 == null || lesson1.isEmpty()) {
                lessonService.save(lesson);
                Optional<Programme> programme = programmeService.findById(id);
                int lessons = programme.get().getLessons();
                programme.get().setLessons(lessons+1);
                programmeService.save(programme.get());
                attributes.addFlashAttribute("mess", "Thêm mới thành công...!!!");
            } else {
                attributes.addFlashAttribute("error", "Nội dung đã tồn tại");
            }
            return "redirect:/lesson";
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "error");
            return "redirect:/lesson";
        }
    }




    @GetMapping("/editAll/{id}")
    public String editAll(@PathVariable long id, Model model) {
        Optional<Programme> programme = programmeService.findById(id);
        if (programme != null) {
            Optional<Lesson> lesson = lessonService.findById(id);
            model.addAttribute("lesson", lesson);
            model.addAttribute("programmes",programme.get());
            return "manager-page/lesson-edit-all";
        } else {
            return "error";
        }
    }



    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model, Pageable pageable) {


        Optional<Lesson> lesson = lessonService.findById(id);
        if (lesson != null) {
            model.addAttribute("lesson", lesson.get());
            model.addAttribute("listProgramme", programmeService.findAllByDeletedIsFalse(pageable));

            return "manager-page/lesson-edit";
        } else {
            return "error";
        }

    }

    @PostMapping("/edit")
    public String doEdit(@Valid @ModelAttribute("lesson") Lesson lesson, BindingResult result, RedirectAttributes attributes, HttpServletRequest request) {
        try {
            if (result.hasErrors()) {
                return "manager-page/lesson-edit";
            }
            List<Lesson> lessons = lessonRepository.findAll();
            for (Lesson lesson1 : lessons) {
                if ((lesson.getContent()).equals(lesson1.getContent()) && (lesson.getId()) != lesson1.getId()) {
                    attributes.addFlashAttribute("mess", "Nội dung đã tồn tại...!!!");
                    return "redirect:/lesson/edit";
                }
            }
            Optional<Lesson> lesson1 = lessonService.findById(lesson.getId());
            lesson.setProgramme(lesson1.get().getProgramme());
            lessonService.save(lesson);
            attributes.addFlashAttribute("mess", "Thay đổi thành công...!!!");
        } catch (Exception e) {
            e.getMessage();
            attributes.addFlashAttribute("mess", "Error");
        }
        long idProgramme = lesson.getProgramme().getId();
        System.out.println(idProgramme);
        return "redirect:/lesson/list/"+ idProgramme;
    }
}
