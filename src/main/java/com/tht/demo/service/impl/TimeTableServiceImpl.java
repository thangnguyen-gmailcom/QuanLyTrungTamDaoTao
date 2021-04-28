package com.tht.demo.service.impl;

import com.tht.demo.model.TimeTable;
import com.tht.demo.repository.TimeTableRepository;
import com.tht.demo.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class TimeTableServiceImpl implements TimeTableService {
    @Autowired
    private TimeTableRepository timeTableRepository;
    @Override
    public Page<TimeTable> showAll(Pageable pageable) {
        return timeTableRepository.findAll(pageable);
    }

    @Override
    public List<TimeTable> findByClassRoomId(Long classroomId) {
        return timeTableRepository.findByClassRoomId(classroomId);
    }

    @Override
    public void updateTeacherNote(Long id, String teacherNote) {
        timeTableRepository.updateTeacherNote(id,teacherNote);
    }

    @Override
    public void deleteTimeTableByClassRoomId(Long classroomId) {
        timeTableRepository.deleteTimeTableByClassRoomId(classroomId);
    }

    @Override
    public Optional<TimeTable> findById(Long id) {
        return timeTableRepository.findById(id);
    }

    @Override
    public TimeTable save(TimeTable timeTable) {
        return timeTableRepository.save(timeTable);
    }

    @Override
    public void delele(Long id) {
timeTableRepository.deleteById(id);
    }
}
