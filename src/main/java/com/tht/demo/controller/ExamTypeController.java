package com.tht.demo.controller;


import com.tht.demo.model.Course;
import com.tht.demo.model.ExamType;
import com.tht.demo.repository.ExamTypeRepository;
import com.tht.demo.service.ExamTypeService;
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
@RequestMapping("/exampleType")
public class ExamTypeController {
@Autowired
private ExamTypeService examTypeService;
@Autowired
private ExamTypeRepository examTypeRepository;

    @GetMapping("")
    public String exampleTypeList() {
        return "manager-page/example-type-list";
    }

    @GetMapping("/add")
    public String add(Model model, Pageable pageable) {
        model.addAttribute("exam", new ExamType());
        return "manager-page/example-type-add";
    }
    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("exam") ExamType examType, BindingResult result, RedirectAttributes attributes, Model model, Pageable pageable) {
        try {
            if (result.hasErrors()) {
                return "manager-page/example-type-add";
            }

            String name = examType.getTitle();
            Optional<ExamType> examType1 = examTypeService.findByTitle(name);
            if (examType1 == null || examType1.isEmpty()) {
                examTypeService.save(examType);
                attributes.addFlashAttribute("mess", "Thêm mới thành công...!!!");
            } else {
                attributes.addFlashAttribute("error", "Tên điểm đã tồn tại");
            }
            return "redirect:/exampleType";
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "error");
            return "redirect:/exampleType";
        }
    }




    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model, Pageable pageable) {
        Optional<ExamType> examType = examTypeService.findById(id);

        if (examType != null) {
            model.addAttribute("exam", examType);
            return "manager-page/example-type-edit";
        } else {
            return "error";
        }

    }

    @PostMapping("/edit")
    public String doEdit(@Valid @ModelAttribute("exam") ExamType examType, BindingResult result, RedirectAttributes attributes, Model model,Pageable pageable) {
        try {
            if (result.hasErrors()) {
                return "manager-page/example-type-edit";
            }

            List<ExamType> examTypes = examTypeRepository.findAll();
            for (ExamType examType1 : examTypes) {
                if ((examType.getTitle()).equals(examType1.getTitle()) && (examType.getId()) != examType1.getId()) {
                    attributes.addFlashAttribute("mess", "Tên điểm đã tồn tại...!!!");
                    return "/manager-page/example-type-edit";
                }
            }
            examTypeService.save(examType);
            attributes.addFlashAttribute("mess", "Thay đổi thành công...!!!");
        } catch (Exception e) {
            e.getMessage();
            attributes.addFlashAttribute("mess", "Error");
        }
        return "redirect:/exampleType";
    }
}
