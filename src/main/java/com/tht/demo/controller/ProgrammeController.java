package com.tht.demo.controller;

import com.tht.demo.model.Course;
import com.tht.demo.model.Lesson;
import com.tht.demo.model.Programme;
import com.tht.demo.repository.LessonRepository;
import com.tht.demo.repository.ProgrammeRepository;
import com.tht.demo.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/programme")
public class ProgrammeController {
    @Autowired
    private ProgrammeService programmeService;
    @Autowired
    private ProgrammeRepository programmeRepository;

    @Autowired
    private LessonRepository lessonRepository;


    @GetMapping("")
    public String programmeList() {
        return "manager-page/programme-list";
    }


    @GetMapping("/info/{id}")
    public String info(@PathVariable long id, Model model) {
        Optional<Programme> programme = programmeService.findById(id);
        if (programme != null) {
            model.addAttribute("programme", programme.get());
            return "manager-page/programme-info";
        } else {
            return "error";
        }

    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("programme", new Programme());
        return "manager-page/programme-add";
    }

    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("programme") Programme programme, BindingResult result, RedirectAttributes attributes, Model model) {

        try {

            if (result.hasErrors()) {
                return "manager-page/programme-add";
            }
            String name = programme.getProgrammeName();
            Optional<Programme> programme1 = programmeRepository.findByProgrammeName(name);
            if (programme1 == null || programme1.isEmpty()) {
                programmeService.save(programme);
                for (int i = 0; i < programme.getLessons(); i++) {
                    Lesson lesson = new Lesson();
                    lesson.setProgramme(programme);
                    lesson.setContent("Chưa có thông tin");
                    lesson.setLessonNumber("chưa có thông tin");
                    lessonRepository.save(lesson);
                }
                attributes.addFlashAttribute("mess", "Thêm mới thành công...!!!");
            } else {
                attributes.addFlashAttribute("mess", "Tên khóa đã tồn tại");
            }
            return "redirect:/programme";
        } catch (Exception e) {
            System.out.println(e);
            attributes.addFlashAttribute("error", "lỗi");
            return "redirect:/programme";
        }
    }


    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable long id, Model model) {
        Optional<Programme> programme = programmeService.findById(id);
        if (programme != null) {
            model.addAttribute("programme", programme);
            return "manager-page/programme-edit";

        } else {
            return "error";
        }
    }

    @PostMapping("/edit")
    public String doEdit(@Valid @ModelAttribute("programme") Programme programme, BindingResult result, RedirectAttributes attributes, Model model) {
        try {
            if (result.hasErrors()) {
                return "manager-page/programme-edit";
            }

            List<Programme> programmes = programmeRepository.findAll();
            for (Programme programme1 : programmes) {
                if ((programme.getProgrammeName()).equals(programme1.getProgrammeName()) && (programme.getId()) != programme1.getId()) {
                    attributes.addFlashAttribute("mess", "Tên đã tồn tại...!!!");
                    return "redirect:/manager-page/programme-edit";
                }
            }

            programmeService.save(programme);
            attributes.addFlashAttribute("mess", "Thay đổi thành công...!!!");
        } catch (Exception e) {
            e.getMessage();
            attributes.addFlashAttribute("mess", "Error");
        }
        return "redirect:/programme";
    }


}

