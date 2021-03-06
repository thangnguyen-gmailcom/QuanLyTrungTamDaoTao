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
import java.time.LocalDateTime;
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
        Optional<User> user1 = userService.findByEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("123456789"));
        user.setStaffCreatedId(user1.get());
        if (result.hasFieldErrors()) {
            return "manager-page/staff-add";
        } else {
            Optional<User> staff3 = userService.findByEmail(user.getEmail());
            Optional<User> staff1 = userService.findByPhoneNumber(user.getPhoneNumber());
            Optional<User> staff2 = userService.findByIdCard(user.getIdCard());
            if (staff3.isPresent() ) {
                redirectAttributes.addFlashAttribute("error", "Email ???? t???n t???i");
                return "redirect:/staff";
            }else if(staff1.isPresent()){
                redirectAttributes.addFlashAttribute("error", "S??? ??i???n tho???i ???? t???n t???i");
                return "redirect:/staff";
            }else if(staff2.isPresent()){
                redirectAttributes.addFlashAttribute("error", "S??? ch???ng minh nh??n d??n ???? t???n t???i");
                return "redirect:/staff";
            }
            try {
                if (user.getImage() == null){
                    user.setImage("default-avatar.png");
                }
            }catch (Exception e){
                System.out.println(e);
            }
            redirectAttributes.addFlashAttribute("mess", "Th??m m???i th??nh c??ng");
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
                redirectAttributes.addFlashAttribute("error", "Kh??ng t??m th???y user");
                return "redirect:/staff";
            }
        } catch (Exception e) {
            return "/error";
        }
    }

    @PostMapping("/edit")
    public String editStaff(@Valid @ModelAttribute User user,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        new UserValidator().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "manager-page/staff-edit";
        } else {
            Optional<User> staff1 = userService.findByPhoneNumber(user.getPhoneNumber());
            Optional<User> admin = userService.findByEmail("admin@gmail.com");
            user.setStaffEditedId(admin.get());
            user.setEditedDate(LocalDateTime.now());
            if(staff1.isPresent()){
                if(!staff1.get().getPhoneNumber().equals(user.getPhoneNumber())){
                    redirectAttributes.addFlashAttribute("error", "S??? ??i???n tho???i ???? t???n t???i");
                    return "redirect:/staff";
                }else {
                    redirectAttributes.addFlashAttribute("mess", "Ch???nh s???a th??nh c??ng");
                    userService.save(user);
                    return "redirect:/staff";
                }
            }
            redirectAttributes.addFlashAttribute("mess", "Th??m m???i th??nh c??ng");
            userService.save(user);
            return "redirect:/staff";
        }
    }

    @GetMapping("/view/{id}")
    public String viewStaff(@PathVariable("id") long id,RedirectAttributes redirectAttributes,Model model){
        Optional<User> user = userService.findById(id);
        if(!user.isPresent()){
            redirectAttributes.addFlashAttribute("error", "Nh??n vi??n kh??ng t???n t???i");
           return "redirect:/staff";
        }
        model.addAttribute("user",user.get());
        return "manager-page/staff-info";
    }
}
