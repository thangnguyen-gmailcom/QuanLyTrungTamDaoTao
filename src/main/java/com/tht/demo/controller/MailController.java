package com.tht.demo.controller;


import com.tht.demo.model.ClassRoom;
import com.tht.demo.model.StudentClass;
import com.tht.demo.model.User;
import com.tht.demo.repository.ClassRoomRepository;
import com.tht.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mail")
public class MailController {
    @Autowired
    public JavaMailSender javaMailSender;
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ClassRoomRepository classRoomRepository;


    @GetMapping("/staff")
    public String mailStaff() {
        return "manager-page/mail-staff";
    }

    @PostMapping("/staff")
    public String sendEmail(@RequestParam("content") String content, @RequestParam("subject") String subject, Pageable pageable, RedirectAttributes attributes) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        for (User user : userRepository.findAllEmployeeOrderByIdDesc(pageable)) {
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(content);
            this.javaMailSender.send(simpleMailMessage);
        }
        attributes.addFlashAttribute("mess", "gửi mail hoàn tất");
        return "manager-page/mail-staff";
    }


    @GetMapping("/student")
    public String mailStudent(Model model, Pageable pageable) {
        model.addAttribute("class", classRoomRepository.findAllByDeletedIsFalse(pageable));
        return "manager-page/mail-student";
    }

    @PostMapping("/student")
    public String sendEmailStudent(@RequestParam("to") String to, @RequestParam("content") String content, @RequestParam("subject") String subject, Pageable pageable, RedirectAttributes attributes) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        ClassRoom classRoom = classRoomRepository.findByClassName(to).orElse(null);

        List<StudentClass> studentClass = classRoom.getStudentClasses();
        List<User> users = new ArrayList<>();
        for (StudentClass st : studentClass) {
            users.add(st.getUser());
        }
        for (User user : users) {
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(content);
            this.javaMailSender.send(simpleMailMessage);
        }
        attributes.addFlashAttribute("mess", "gửi mail hoàn tất");
        return "manager-page/mail-student";
    }

    @GetMapping("/user")
    public String mailUser() {

        return "manager-page/mail-user";
    }

    @PostMapping("/user")
    public String sendEmails(@RequestParam("to") String to,
                             @RequestParam("to1") String to1, @RequestParam("to2") String to2,
                             @RequestParam("to3") String to3,
                             @RequestParam("to4") String to4,
                             @RequestParam("content") String content, @RequestParam("subject") String subject, Pageable pageable, RedirectAttributes attributes) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        try {

            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(content);
            this.javaMailSender.send(simpleMailMessage);
            simpleMailMessage.setTo(to1);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(content);
            this.javaMailSender.send(simpleMailMessage);

            simpleMailMessage.setTo(to2);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(content);
            this.javaMailSender.send(simpleMailMessage);

            simpleMailMessage.setTo(to3);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(content);
            this.javaMailSender.send(simpleMailMessage);

            simpleMailMessage.setTo(to4);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(content);
            this.javaMailSender.send(simpleMailMessage);

        } catch (Exception e) {
            attributes.addFlashAttribute("mess", "gửi mail hoàn tất, tuy nhiên một số email không tồn tại sẽ không gửi đucợ");
            return "manager-page/mail-user";
        }

        return "manager-page/mail-user";
    }
}
