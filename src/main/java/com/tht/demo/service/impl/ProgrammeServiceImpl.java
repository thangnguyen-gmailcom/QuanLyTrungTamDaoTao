package com.tht.demo.service.impl;

import com.tht.demo.model.Programme;
import com.tht.demo.repository.LessonRepository;
import com.tht.demo.repository.ProgrammeRepository;
import com.tht.demo.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProgrammeServiceImpl implements ProgrammeService {
    @Autowired
    private ProgrammeRepository programmeRepository;
@Autowired
private LessonRepository lessonRepository;
    @Override
    public Page<Programme> showAll(Pageable pageable) {
        return programmeRepository.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public Optional<Programme> findById(Long id) {
        return programmeRepository.findById(id);
    }

    @Override
    public Programme save(Programme programme) {
        return programmeRepository.save(programme);
    }

    @Override
    @Transactional
    public void delele(Long id) {

        boolean result = programmeRepository.softDeleteProgramme(id) > 0;
        if (!result)
            throw new RuntimeException("course not found");
        lessonRepository.softDeleteLessonByProgrammeId(id);
    }
}
