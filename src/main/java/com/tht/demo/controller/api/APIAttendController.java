package com.tht.demo.controller.api;

import com.tht.demo.model.Attend;
import com.tht.demo.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/attend")
public class APIAttendController {

    @Autowired
    private AttendService attendService;

    @PostMapping("/")
    public ResponseEntity<?> saveAttend(@RequestBody Attend attend){
        try{
            Attend attendSave = null;
            long timeTableId = attend.getTimeTable().getId();
            long userId = attend.getUser().getId();
            Attend checkedAttend = attendService.findByTimeTableIdAndUserId(timeTableId,userId);
            if(checkedAttend != null){
                checkedAttend.setStatus(attend.getStatus());
                checkedAttend.setContent(attend.getContent());
                attendSave = attendService.save(checkedAttend);
                return new ResponseEntity<>(attendSave,HttpStatus.OK);
            }else {
                attendSave = attendService.save(attend);
                return new ResponseEntity<>(attendSave,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
