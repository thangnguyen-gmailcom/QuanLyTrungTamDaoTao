package com.tht.demo.controller;


import com.tht.demo.dto.MyUserDetails;
import com.tht.demo.model.User;
import com.tht.demo.service.UserService;
import com.tht.demo.validate.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String studentList() {
        return "manager-page/student-list";
    }

    @GetMapping("/info")
    public String info() {
        return "manager-page/student-info";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<User> user = userService.findStudentById(id);
            if (user.isPresent()) {
                model.addAttribute("user", user.get());
                return "manager-page/student-edit";
            } else {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy học viên này");
                return "redirect:/student";
            }
        } catch (Exception e) {
            return "/error";
        }
    }

    @PostMapping("/edit")
    public String editStudent(@Valid @ModelAttribute User user,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        new UserValidator().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "manager-page/student-edit";
        } else {
            Optional<User> staff = userService.findByEmail(getPrincipal());
            user.setStaffEditedId(staff.get());
            user.setEditedDate(LocalDateTime.now());
            Optional<User> student = userService.findByPhoneNumber(user.getPhoneNumber());
            if(student.isPresent()){
                if(!student.get().getPhoneNumber().equals(user.getPhoneNumber())){
                    redirectAttributes.addFlashAttribute("error", "Số điện thoại đã tồn tại");
                    return "redirect:/student";
                }else {
                    redirectAttributes.addFlashAttribute("mess", "Chỉnh sửa thành công");
                    userService.save(user);
                    return "redirect:/student";
                }
            }
            redirectAttributes.addFlashAttribute("mess", "Chỉnh sửa thành công");
            userService.save(user);
            return "redirect:/student";
        }
    }



    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "manager-page/student-add";
    }

    @PostMapping("/add")
    public String addStudent(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirectAttributes) {
        new UserValidator().validate(user, result);
        Optional<User> user1 = userService.findByEmail(getPrincipal());
        user.setPassword(passwordEncoder.encode("123456789"));
        user.setStaffCreatedId(user1.get());
        if (result.hasFieldErrors()) {
            return "manager-page/student-add";
        } else {
            Optional<User> student1 = userService.findByEmail(user.getEmail());
            Optional<User> student2 = userService.findByPhoneNumber(user.getPhoneNumber());
            Optional<User> student3 = userService.findByIdCard(user.getIdCard());
            if (student1.isPresent()) {
                redirectAttributes.addFlashAttribute("error", "Email đã tồn tại");
                return "redirect:/student";
            } else if (student2.isPresent()) {
                redirectAttributes.addFlashAttribute("error", "Số điện thoại đã tồn tại");
                return "redirect:/student";
            } else if (student3.isPresent()) {
                redirectAttributes.addFlashAttribute("error", "Số chứng minh nhân dân đã tồn tại");
                return "redirect:/student";
            }
            if (user.getImage() == null){
                user.setImage("default-avatar.png");
            }
            redirectAttributes.addFlashAttribute("mess", "Thêm mới thành công");
            userService.save(user);
            return "redirect:/student";
        }
    }

    @GetMapping("/view/{id}")
    public String viewStaff(@PathVariable("id") long id,RedirectAttributes redirectAttributes,Model model){
        Optional<User> user = userService.findStudentById(id);
        if(!user.isPresent()){
            redirectAttributes.addFlashAttribute("error", "Học viên không tồn tại");
            return "redirect:/student";
        }
        model.addAttribute("user",user.get());
        return "manager-page/student-info";
    }

    private String getPrincipal() {
        String userName = null;
        Object printObject = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (printObject instanceof MyUserDetails) {
            userName = ((MyUserDetails) printObject).getUsername();
        } else {
            userName = printObject.toString();
        }
        return userName;
    }
}
