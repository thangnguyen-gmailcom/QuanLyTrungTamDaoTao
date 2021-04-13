package com.tht.demo.repository;

import com.tht.demo.model.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamTypeRepository extends JpaRepository<ExamType , Long> {
}
