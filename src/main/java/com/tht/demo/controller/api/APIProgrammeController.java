package com.tht.demo.controller.api;

import com.tht.demo.model.Course;
import com.tht.demo.model.Programme;
import com.tht.demo.repository.ProgrammeRepository;
import com.tht.demo.service.ProgrammeService;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String name,@PageableDefault(size = 8) Pageable pageable
    ){
        try {
            return new ResponseEntity<>(programmeService.findAllByProgrammeNameContainingAndDeletedIsFalse(name,pageable), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/close")
    public ResponseEntity<?> programmeClose(@RequestParam(value = "page",required = false,defaultValue = "0") int page
    ) {
        try {
            Page<Programme> programmes = programmeService.findAllByDeletedIsTrue(PageRequest.of(page,8, Sort.by("id").descending()));
            return new ResponseEntity<>(programmes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restores(@PathVariable long id){
        try {
            programmeService.restoreProgramme(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
