package com.tht.demo.controller;


import com.tht.demo.model.Attend;
import com.tht.demo.model.TimeTable;
import com.tht.demo.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/attend")
public class AttendController {

    @Autowired
    private TimeTableService timeTableService;

    @GetMapping("/{id}")
    public String attend(@PathVariable("id") long id, RedirectAttributes redirect, HttpServletRequest request, Model model){
        Optional<TimeTable> timeTable = timeTableService.findById(id);
        if(timeTable.isEmpty()){
            redirect.addFlashAttribute("error","Buổi học không tồn tại");
            return "redirect:" +request.getHeader("referer");
        }else {
            model.addAttribute("timeTable",timeTable.get());
            List<Attend> attendList = timeTable.get().getAttends();
            Map<Long, Attend> attendMap = new HashMap<>();
            for(Attend attend : attendList){
                attendMap.put(attend.getUser().getId(),attend);
            }
            model.addAttribute("attends",attendMap);

            return "manager-page/attend";
        }

    }
}
