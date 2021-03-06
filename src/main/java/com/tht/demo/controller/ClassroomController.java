package com.tht.demo.controller;


import com.tht.demo.dto.MyUserDetails;
import com.tht.demo.model.ClassRoom;
import com.tht.demo.model.User;
import com.tht.demo.service.ClassRoomService;
import com.tht.demo.service.CourseService;
import com.tht.demo.service.ProgrammeService;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/class")
public class ClassroomController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClassRoomService classRoomService;

    @GetMapping("")
    public String classList(){
        return "manager-page/class-list";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("classRoom", new ClassRoom());
        model.addAttribute("users",userService.findAllTeacher());
        model.addAttribute("courses", courseService.findAll());
        return "manager-page/class-add";
    }

    @PostMapping("/add")
    public String addClassRoom(@Valid @ModelAttribute("classRoom")ClassRoom classRoom, BindingResult result,@RequestParam String dateStart, RedirectAttributes redirect,Model model){
        if(result.hasFieldErrors()){
            model.addAttribute("users",userService.findAllTeacher());
            model.addAttribute("courses", courseService.findAll());
            return "manager-page/class-add";
        }else {
            Optional<User> staffCreated = userService.findByEmail(getPrincipal());
            classRoom.setUserCreated(staffCreated.get());
            classRoom.setCreatedDate(LocalDateTime.now());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            classRoom.setStartDate(LocalDate.parse(dateStart,formatter));
            Optional<ClassRoom> classRoom1 = classRoomService.findByClassNameAndDeletedIsFalse(classRoom.getClassName());
            if(classRoom1.isPresent()){
                redirect.addFlashAttribute("error","t??n l???p ???? t???n t???i");
                return "redirect:/class";
            }
            classRoomService.save(classRoom);
            redirect.addFlashAttribute("mess", "T???o l???p th??nh c??ng");
            return "redirect:/class";
        }
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id,Model model){
        Optional<ClassRoom> classRoom = classRoomService.findById(id);
        model.addAttribute("classRoom", classRoom.get());
        model.addAttribute("users",userService.findAllTeacher());
        model.addAttribute("courses", courseService.findAll());
        return "manager-page/class-edit";
    }

    @PostMapping("/edit")
    public String editClass(@Valid @ModelAttribute("classRoom")ClassRoom classRoom, BindingResult result, RedirectAttributes redirect){
        if(result.hasFieldErrors()){
            return "manager-page/class-edit";
        }else {
            Optional<ClassRoom> classRoom1 = classRoomService.findByClassNameAndDeletedIsFalse(classRoom.getClassName());
            if(classRoom1.isPresent()){
                if(!classRoom1.get().getClassName().equals(classRoom.getClassName())){
                    redirect.addFlashAttribute("error","t??n l???p ???? t???n t???i");
                    return "redirect:/class";
                }else {
                    classRoomService.save(classRoom);
                    redirect.addFlashAttribute("mess","Ch???nh s???a th??nh c??ng");
                    return "redirect:/class";
                }
            }
            classRoomService.save(classRoom);
            redirect.addFlashAttribute("mess","Ch???nh s???a th??nh c??ng");
            return "redirect:/class";
        }
    }

    @GetMapping("/view")
    public String view(@RequestParam("id") long id,Model model, RedirectAttributes redirect){
        Optional<ClassRoom> classRoom = classRoomService.findById(id);
        if(classRoom.isPresent()){
            model.addAttribute("classRoom",classRoom.get());
            return "manager-page/class-view";
        }else {
            redirect.addFlashAttribute("error","l???p kh??ng t???n t???i");
            return "redirect:/class";
        }
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
