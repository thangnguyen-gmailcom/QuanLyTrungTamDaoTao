package com.tht.demo.service.impl;

import com.tht.demo.model.Lesson;
import com.tht.demo.repository.LessonRepository;
import com.tht.demo.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public Page<Lesson> showAll(Pageable pageable) {
        return lessonRepository.findAll(pageable);
    }

    @Override
    public Optional<Lesson> findById(Long id) {
        return lessonRepository.findById(id);
    }

    @Override
    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public void delele(Long id) {
        lessonRepository.deleteById(id);
    }
}
