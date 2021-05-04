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

    @Override
    public Optional<Programme> findByProgrammeName(String name) {
        return programmeRepository.findByProgrammeNameAndDeletedIsFalse(name);
    }

    @Override
    public Page<Programme> findAllByDeletedIsFalse(Pageable pageable) {
        return programmeRepository.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public Page<Programme> findAllByProgrammeNameContainingAndDeletedIsFalse(String name, Pageable pageable) {
        return programmeRepository.findAllByProgrammeNameContainingAndDeletedIsFalse(name,pageable);
    }

    @Override
    public Page<Programme> findAllByDeletedIsTrue(Pageable pageable) {
        return programmeRepository.findAllByDeletedIsTrue(pageable);
    }

    @Override
    @Transactional
    public void restoreProgramme(Long id) {
        programmeRepository.restoreProgramme(id);
    }

    @Override
    public Page<Programme> findAllByProgrammeNameContainingAndDeletedIsTrue(String name, Pageable pageable) {
        return programmeRepository.findAllByProgrammeNameContainingAndDeletedIsTrue(name,pageable);
    }

}
