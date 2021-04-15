package com.tht.demo.controller;


import com.tht.demo.model.Roles;
import com.tht.demo.model.User;
import com.tht.demo.service.RolesService;
import com.tht.demo.service.UserService;
import com.tht.demo.validate.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String staffList() {
        return "manager-page/staff-list";
    }

    @GetMapping("/info")
    public String info() {
        return "manager-page/staff-info";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "manager-page/staff-add";
    }

    @PostMapping("/add")
    public String addStaff(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes redirectAttributes) {
        new UserValidator().validate(user, result);
        user.setPassword(passwordEncoder.encode("123456789"));
        if (result.hasFieldErrors()) {
            return "manager-page/staff-add";
        } else {
            Optional<User> staff3 = userService.findByEmail(user.getEmail());
            Optional<User> staff1 = userService.findByPhoneNumber(user.getPhoneNumber());
            Optional<User> staff2 = userService.findByIdCard(user.getIdCard());
            if (staff3.isPresent() || staff1.isPresent() || staff2.isPresent()) {
                redirectAttributes.addFlashAttribute("messError", "Email, số điện thoại hoặc chứng minh nhân dân đã tồn tại");
                return "redirect:/staff";
            }
            redirectAttributes.addFlashAttribute("mess", "Thêm mới thành công");
            userService.save(user);
            return "redirect:/staff";
        }
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<User> user = userService.findById(id);
            if (user.isPresent()) {
                model.addAttribute("user", user.get());
                return "manager-page/staff-edit";
            } else {
                redirectAttributes.addFlashAttribute("messError", "Không tìm thấy user");
                return "redirect:/staff";
            }
        } catch (Exception e) {
            return "/error";
        }
    }

    @PostMapping("/edit")
    public String editStaff(@Valid @ModelAttribute User user,BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model){
        new UserValidator().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "manager-page/staff-edit";
        } else {
            Optional<User> staff3 = userService.findByEmail(user.getEmail());
            Optional<User> staff1 = userService.findByPhoneNumber(user.getPhoneNumber());
            Optional<User> staff2 = userService.findByIdCard(user.getIdCard());
            if (staff3.isPresent() || staff1.isPresent() || staff2.isPresent()) {
                redirectAttributes.addFlashAttribute("messError", "Email, số điện thoại hoặc chứng minh nhân dân đã tồn tại");
                return "redirect:/staff";
            }
            redirectAttributes.addFlashAttribute("mess", "Thêm mới thành công");
            userService.save(user);
            return "redirect:/staff";
        }
    }
}
