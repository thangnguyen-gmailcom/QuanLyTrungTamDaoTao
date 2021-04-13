package com.tht.demo.repository;

import com.tht.demo.model.Programme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammeRepository extends JpaRepository<Programme,Long> {
}
