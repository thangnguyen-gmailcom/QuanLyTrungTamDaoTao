package com.tht.demo.controller.api;

import com.tht.demo.model.ClassRoom;
import com.tht.demo.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/classRoom")
public class APIClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    @GetMapping("")
    public ResponseEntity<?> findAllClassRoom(@RequestParam(value = "page",required = false,defaultValue = "0") int page){
        try {
            Page<ClassRoom> classRooms = classRoomService.showAll(PageRequest.of(page,8));
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
}
