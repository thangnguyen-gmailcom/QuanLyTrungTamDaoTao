//package com.tht.demo.controller;
//
//import com.tht.demo.model.User;
//import com.tht.demo.repository.UserRepository;
//import com.tht.demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping(value = "/user")
//public class UserController {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserService userService;
//    @GetMapping("/")
//    public String show() {
//        return "";
//    }
//
//    @GetMapping("/add")
//    public String showAddForm(Model model) {
//        model.addAttribute("list", new User());
//        return "";
//    }
//
//    @PostMapping("/add")
//    public String doAdd(@ModelAttribute("user") User user, RedirectAttributes attributes) {
//        try {
//            String email = user.getEmail();
//            Optional<User> userList = userRepository.findByEmail(email);
//            String phone = user.getPhoneNumber();
//            Optional<User> user1 = userRepository.findByPhoneNumber(phone);
//            String card = user.getIdCard();
//            Optional<User> user2 = userRepository.findByIdCard(card);
//            if (userList == null || userList.isEmpty() && user1 == null || user1.isEmpty() && user2 == null || user2.isEmpty()) {
//                userService.save(user);
//                attributes.addFlashAttribute("mess", "Thêm mới thành công...!!!");
//            } else {
//                attributes.addFlashAttribute("mess", "Tên email, số điện thoại hoặc số thẻ căn cước đã tồn tại");
//            }
//            return "redirect:/....";
//        } catch (Exception e) {
//            attributes.addFlashAttribute("mess", "error");
//            return "redirect:/.....";
//        }
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        Optional<User> user = userService.findById(id);
//        if (user != null) {
//            model.addAttribute("user", user);
//            return "";
//        } else {
//            return "error";
//        }
//    }
//
//    @PostMapping("/edit")
//    public String doEdit(@ModelAttribute("user") User user, RedirectAttributes attributes) {
//        try {
//
//            List<User> categories = userRepository.findAll();
//            for (User user1 : categories) {
//                if ((user.getEmail()).equals(user1.getEmail()) && (user.getId()) != user1.getId()) {
//                    attributes.addFlashAttribute("mess", "Tên đã tồn tại...!!!");
//                    return "redirect:/.....";
//                }
//            }
//            userService.save(user);
//            attributes.addFlashAttribute("mess", "Thay đổi thành công...!!!");
//        } catch (Exception e) {
//            e.getMessage();
//            attributes.addFlashAttribute("mess", "Error");
//        }
//        return "redirect:/";
//    }
//
//
//}
