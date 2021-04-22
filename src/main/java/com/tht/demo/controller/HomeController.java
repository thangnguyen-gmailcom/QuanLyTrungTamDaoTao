package com.tht.demo.controller;

import com.tht.demo.dto.MyUserDetails;
import com.tht.demo.model.ClassRoom;
import com.tht.demo.model.Programme;
import com.tht.demo.model.User;
import com.tht.demo.service.ClassRoomService;
import com.tht.demo.service.ProgrammeService;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

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
    public String index(Model model, Pageable pageable, HttpSession session){
        Page<Programme> programmes = programmeService.findAllByDeletedIsFalse(pageable);
        model.addAttribute("program",programmes);
        Page<User> users = userService.showAllStudent(pageable) ;
         model.addAttribute("student",users);
         Page<User> staff = userService.showAllEmployee(pageable);
         model.addAttribute("staff" , staff);
         Page<ClassRoom> classRooms = classRoomService.showAll(pageable);
         model.addAttribute("class",classRooms);
         Optional<User> user = userService.findByEmail(getPrincipal());
         session.setAttribute("user",user.get());
        return "manager-page/index";
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
