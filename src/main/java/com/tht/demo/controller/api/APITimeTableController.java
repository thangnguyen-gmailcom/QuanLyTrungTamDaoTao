package com.tht.demo.controller.api;

import com.tht.demo.dto.MyUserDetails;
import com.tht.demo.dto.TimeTableData;
import com.tht.demo.model.ClassRoom;
import com.tht.demo.model.TimeTable;
import com.tht.demo.model.User;
import com.tht.demo.service.AttendService;
import com.tht.demo.service.ClassRoomService;
import com.tht.demo.service.TimeTableService;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/timetable")
public class APITimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    @Autowired
    private ClassRoomService classRoomService;

    @Autowired
    private AttendService attendService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findTimeTableById(@PathVariable("id") long id){
        try{
            Optional<TimeTable> timeTable = timeTableService.findById(id);
            return new ResponseEntity<>(timeTable.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> showAll(@PathVariable("id") long id){
        try {
            List<TimeTable> timeTables = timeTableService.findByClassRoomId(id);
            return new ResponseEntity<>(timeTables,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTeacherNote(@PathVariable("id")long id,@RequestBody String teacherNote){
        try {
            timeTableService.updateTeacherNote(id,teacherNote);
            return new ResponseEntity<>(teacherNote, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> addTimeTable(@RequestBody TimeTableData timeTableData) {
        ClassRoom classRoom = classRoomService.findById(timeTableData.getClassId()).orElse(null);
        List<TimeTable> timeTables = timeTableService.findByClassRoomId(classRoom.getId());
        if(timeTables.isEmpty()){
            User teacher = classRoom.getTeacher();
            User userCreated = userService.findByEmail(getPrincipal()).orElse(null);
            int lessons = classRoom.getCourse().getProgramme().getLessons();
            LocalDate startDate = classRoom.getStartDate();
            List<Integer> daysOfWeek = timeTableData.getDaysOfWeek();
            List<LocalDate> dateTimes = addDate(lessons, startDate, daysOfWeek);
            int count = 0;
            for (LocalDate localDate : dateTimes) {
                TimeTable timeTable = new TimeTable();
                timeTable.setClassRoom(classRoom);
                timeTable.setDate(localDate);
                timeTable.setLessonTitle(classRoom.getCourse().getProgramme().getLessonList().get(count).getLessonNumber());
                timeTable.setLessonContent(classRoom.getCourse().getProgramme().getLessonList().get(count).getContent());
                timeTable.setTeacher(teacher);
                timeTable.setUser(userCreated);
                timeTableService.save(timeTable);
                count++;
            }
            classRoom.setStatusTimeTable(true);
            classRoomService.save(classRoom);
            return new ResponseEntity<>(timeTableData, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private List<LocalDate> addDate(int lessons, LocalDate startDate, List<Integer> daysOfWeek) {
        int dayStart = startDate.getDayOfWeek().getValue();
        int dayStartNew = 0;
        boolean check = false;
        for (int i = 0; i < daysOfWeek.size(); i++) {
            if (dayStart < daysOfWeek.get(i)) {
                dayStartNew = daysOfWeek.get(i);
                check = true;
                break;
            }
        }
        if (!check) {
            startDate = startDate.plusDays(7 - dayStart + daysOfWeek.get(0));
        } else {
            startDate = startDate.plusDays(dayStartNew - dayStart);
        }
        List<LocalDate> localDates = new ArrayList<>();
        int count = 0;
        while (localDates.size() < lessons) {
            if (daysOfWeek.contains(startDate.plusDays(count).getDayOfWeek().getValue())) {
                localDates.add(startDate.plusDays(count));
            }
            count++;
        }
        return localDates;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTimeTable(@PathVariable("id") long id){
        try{
            ClassRoom classRoom = classRoomService.findById(id).orElse(null);
            List<TimeTable> timeTables = timeTableService.findByClassRoomId(id);
            for(TimeTable timeTable : timeTables){
                attendService.deleteByTimeTableId(timeTable.getId());
            }
            timeTableService.deleteTimeTableByClassRoomId(id);
            classRoom.setStatusTimeTable(false);
            classRoomService.save(classRoom);
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
