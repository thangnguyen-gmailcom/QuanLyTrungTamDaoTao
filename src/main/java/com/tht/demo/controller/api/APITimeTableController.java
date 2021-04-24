package com.tht.demo.controller.api;

import com.tht.demo.dto.TimeTableData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/timetable")
public class APITimeTableController {

    @PostMapping("")
    public ResponseEntity<?> addTimeTable(@RequestBody TimeTableData timeTableData){
        return null;
    }
}
