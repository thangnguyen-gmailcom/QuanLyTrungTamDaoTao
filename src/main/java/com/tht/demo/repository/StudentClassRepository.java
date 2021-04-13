package com.tht.demo.repository;

import com.tht.demo.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentClassRepository extends JpaRepository<StudentClass,Long> {
}
