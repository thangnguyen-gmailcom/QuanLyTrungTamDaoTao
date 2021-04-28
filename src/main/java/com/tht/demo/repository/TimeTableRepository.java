package com.tht.demo.repository;

import com.tht.demo.model.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TimeTableRepository extends JpaRepository<TimeTable,Long> {

    List<TimeTable> findByClassRoomId(Long classroomId);

    void deleteTimeTableByClassRoomId(Long classroomId);


    @Modifying
    @Query(value = "UPDATE time_table SET teacher_note = :teacherNote WHERE id = :id",nativeQuery = true)
    void updateTeacherNote(@Param("id")Long id, @Param("teacherNote")String teacherNote);
}
