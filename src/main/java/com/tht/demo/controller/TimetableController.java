package com.tht.demo.controller;


import com.tht.demo.dto.MyUserDetails;
import com.tht.demo.model.TimeTable;
import com.tht.demo.model.User;
import com.tht.demo.service.TimeTableService;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

    @Autowired
    private TimeTableService timeTableService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String timeTableList(HttpSession session){
        Optional<User> user = userService.findByEmail(getPrincipal());
        session.setAttribute("user",user.get());
        return "manager-page/timetable-list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") long id,Model model, RedirectAttributes redirect){
        List<TimeTable> timeTables = timeTableService.findByClassRoomId(id);
        if(timeTables.isEmpty()){
            redirect.addFlashAttribute("error","Lớp không tồn tại");
            return "redirect:/timetable";
        }else {
            model.addAttribute("timeTables",timeTables);
            return "manager-page/timetable-view";
        }
    }

    @GetMapping("/viewDetail")
    public String viewsDetail(){
        return "manager-page/timetable-viewDetail";
    }

    @GetMapping("/add")
    public String add(){return "manager-page/timetable-add";}

    @GetMapping("/edit")
    public String edit(){return "manager-page/timetable-edit";}

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
