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
import java.util.Optional;

@Controller
@RequestMapping("/profilePassword")
public class ProfilePasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String profilePassword(Model model) {
        try {
            Optional<User> user = userService.findByEmail(getPrincipal());
            model.addAttribute("user", user.get());
            return "manager-page/profile-password";
        } catch (Exception e) {
            return "/error";
        }
    }

    @PostMapping("")
    public String editPassword(@ModelAttribute("user") User user, @RequestParam("confirmPassword") String confirmPassword, RedirectAttributes redirect) {
        String pass = user.getPassword();
        String regexPass = "^[a-zA-Z0-9]*$";
        if (pass.length() < 8 || pass.length() > 16 || !pass.matches(regexPass)) {
            redirect.addFlashAttribute("error", "độ dài mật khẩu phải từ 8 đến 16 và không chứa ký tự đặc biệt");
            return "redirect:/profilePassword";
        } else if (!user.getPassword().equals(confirmPassword)) {
            redirect.addFlashAttribute("error", "mật khẩu mới phải trùng nhau");
            return "redirect:/profilePassword";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updatePassword(user.getPassword(), user.getId());
        return "redirect:/login/";
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
