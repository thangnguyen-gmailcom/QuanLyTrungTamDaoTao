package com.tht.demo.controller;

import com.tht.demo.model.ClassRoom;
import com.tht.demo.model.Programme;
import com.tht.demo.model.User;
import com.tht.demo.service.ClassRoomService;
import com.tht.demo.service.ProgrammeService;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class HomeController {
@Autowired
private ProgrammeService programmeService;
@Autowired
private UserService userService;
@Autowired
private ClassRoomService classRoomService;

    @GetMapping("")
    public String index(Model model, Pageable pageable){
        Page<Programme> programmes = programmeService.findAllByDeletedIsFalse(pageable);
        model.addAttribute("program",programmes);
        Page<User> users = userService.showAllStudent(pageable) ;
         model.addAttribute("student",users);
         Page<User> staff = userService.showAllEmployee(pageable);
         model.addAttribute("staff" , staff);
         Page<ClassRoom> classRooms = classRoomService.showAll(pageable);
         model.addAttribute("class",classRooms);
        return "manager-page/index";
    }
}
