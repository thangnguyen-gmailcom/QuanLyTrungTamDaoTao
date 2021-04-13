package com.tht.demo.repository;

import com.tht.demo.model.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableRepository extends JpaRepository<TimeTable,Long> {
}
