package com.tht.demo.controller.api;

import com.tht.demo.model.Course;
import com.tht.demo.model.Programme;
import com.tht.demo.repository.ProgrammeRepository;
import com.tht.demo.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/programmes")
public class APIProgrammeController {
    @Autowired
    private ProgrammeRepository programmeRepository;
@Autowired
private ProgrammeService programmeService;

    @GetMapping("")
    public ResponseEntity<?> showAll(@RequestParam(value = "page",required = false,defaultValue = "0") int page
    ) {
        try {
            Page<Programme> programmes = programmeService.showAll(PageRequest.of(page,8, Sort.by("id").descending()));
            return new ResponseEntity<>(programmes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") long id){
        Optional<Programme> programme=programmeService.findById(id);
        if(programme.isPresent()){
            return new ResponseEntity<>(programme,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try {
            programmeService.delele(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
