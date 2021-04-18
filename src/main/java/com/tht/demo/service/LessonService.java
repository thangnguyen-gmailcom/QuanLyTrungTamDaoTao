package com.tht.demo.service;


import com.tht.demo.model.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LessonService {
    Page<Lesson> showAll(Pageable pageable);
    Optional< Lesson> findById(Long id);
    Lesson save( Lesson lesson);
    void delete(Long id);
    Optional<Lesson> findByContent(String content);
    Page<Lesson> findAllByDeletedIsFalse(Pageable pageable);

    Page<Lesson> findAllByProgrammeIdAndDeletedIsFalse(long id,Pageable pageable);
}
