package com.tht.demo.service;

import com.tht.demo.model.Blog;
import com.tht.demo.model.TimeTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TimeTableService {
    Page<TimeTable> showAll(Pageable pageable);
    List<TimeTable> findByClassRoomId(Long classroomId);
    void updateTeacherNote(Long id,String teacherNote);
    Optional<TimeTable> findById(Long id);
    TimeTable save(TimeTable timeTable);
    void delele(Long id);
}
