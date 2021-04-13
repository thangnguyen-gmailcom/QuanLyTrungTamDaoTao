package com.tht.demo.service;


import com.tht.demo.model.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LessonService {
    Page<Lesson> showAll(Pageable pageable);
    Optional< Lesson> findById(Long id);
    Lesson save( Lesson lesson);
    void delele(Long id);
}
