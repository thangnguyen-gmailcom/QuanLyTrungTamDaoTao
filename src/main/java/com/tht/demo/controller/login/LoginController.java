package com.tht.demo.controller.login;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    public String login(){
        return "login/login";
    }

    @RequestMapping("/error")
    public String errorLogin(@RequestParam String message, @RequestParam String username, Model model){
        model.addAttribute("message",message);
        model.addAttribute("username",username);
        return "login/login";
    }
}
