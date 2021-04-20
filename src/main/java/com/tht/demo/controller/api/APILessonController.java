package com.tht.demo.controller.api;

import com.tht.demo.model.Course;
import com.tht.demo.model.Lesson;
import com.tht.demo.model.Programme;
import com.tht.demo.repository.LessonRepository;
import com.tht.demo.service.LessonService;
import com.tht.demo.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/lessons")
public class APILessonController {
@Autowired
private ProgrammeService programmeService;
    @Autowired
    private LessonService lessonService;




    @GetMapping("")
    public ResponseEntity<?> findById(@RequestParam("program") long id,@RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        Page<Lesson> lesson = lessonService.findAllByProgrammeIdAndDeletedIsFalse(id,PageRequest.of(page, 8));
        if (!lesson.isEmpty()) {
            return new ResponseEntity<>(lesson, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete( @PathVariable long id) {
        try {
            Optional<Lesson> lesson = lessonService.findById(id);
            Programme programme =lesson.get().getProgramme();
            programme.setLessons(programme.getLessons()-1);
            programmeService.save(programme);
            lessonService.delete(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> edit(@RequestBody Lesson lesson) {
        Lesson lesson1 = lessonService.save(lesson);
        if (lesson1 == null) {
            return new ResponseEntity<>(lesson1, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lesson1,HttpStatus.OK);
    }

}
