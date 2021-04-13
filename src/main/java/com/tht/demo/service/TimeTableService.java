package com.tht.demo.service;

import com.tht.demo.model.Blog;
import com.tht.demo.model.TimeTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TimeTableService {
    Page<TimeTable> showAll(Pageable pageable);
    Optional<TimeTable> findById(Long id);
    TimeTable save(TimeTable timeTable);
    void delele(Long id);
}
