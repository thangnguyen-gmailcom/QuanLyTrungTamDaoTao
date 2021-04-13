package com.tht.demo.repository;

import com.tht.demo.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoom , Long> {
}
