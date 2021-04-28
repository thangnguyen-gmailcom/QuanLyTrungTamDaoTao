package com.tht.demo.controller.api;

import com.tht.demo.dto.MyUserDetails;
import com.tht.demo.model.ClassRoom;
import com.tht.demo.model.User;
import com.tht.demo.service.ClassRoomService;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/classRoom")
public class APIClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> findAllClassRoom(@RequestParam(value = "page",required = false,defaultValue = "0") int page){
        try {
            Page<ClassRoom> classRooms = null;
            Optional<User> user = userService.findByEmail(getPrincipal());
            if(user.get().getRoles().getName().equals("ROLE_MINISTRY") ||user.get().getRoles().getName().equals("ROLE_ADMIN")){
                classRooms =  classRoomService.showAll(PageRequest.of(page,8, Sort.by("id").descending()));
            }else if(user.get().getRoles().getName().equals("ROLE_TEACHER")){
                classRooms = classRoomService.findByTeacherId(user.get().getId(),PageRequest.of(page,8, Sort.by("id").descending()));
            }
            return new ResponseEntity<>(classRooms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/timeTable")
    public ResponseEntity<?> findAllClassRoomByStatusTimeTable(@RequestParam(value = "page",required = false,defaultValue = "0") int page, HttpSession session){
        try {
            Page<ClassRoom> classRooms = null;
            Optional<User> user = userService.findByEmail(getPrincipal());
            if(user.get().getRoles().getName().equals("ROLE_MINISTRY") ||user.get().getRoles().getName().equals("ROLE_ADMIN")){
                classRooms =  classRoomService.findAllByStatusTimeTableIsTrue(PageRequest.of(page,8, Sort.by("id").descending()));
            }else if(user.get().getRoles().getName().equals("ROLE_TEACHER")){
                classRooms = classRoomService.findAllByStatusTimeTableIsTrueAndTeacherId(user.get().getId(),PageRequest.of(page,8, Sort.by("id").descending()));
            }else if(user.get().getRoles().getName().equals("ROLE_STUDENT")) {
                classRooms = classRoomService.findAllByStatusTimeTableIsTrueAndStudentClassesUserId(user.get().getId(),PageRequest.of(page,8, Sort.by("id").descending()));
            }
            return new ResponseEntity<>(classRooms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll(){
        try{
            List<ClassRoom> classRooms = classRoomService.findAll();
            return new ResponseEntity<>(classRooms,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findClassRoomById(@PathVariable("id") long id){
        try{
            Optional<ClassRoom> classRoom = classRoomService.findById(id);
            return new ResponseEntity<>(classRoom.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String name,@PageableDefault(size = 8) Pageable pageable
    ){
        try {
            Page<ClassRoom> classRooms = classRoomService.findAllByClassNameContainingAndDeletedIsFalse(name,pageable);
            return new ResponseEntity<>(classRooms, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try {
            classRoomService.delele(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
