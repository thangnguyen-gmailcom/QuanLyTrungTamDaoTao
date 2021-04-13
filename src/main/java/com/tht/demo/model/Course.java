package com.tht.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "course_name")
    private String courseName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "programme_id")
    private Programme programme;

    @OneToMany(mappedBy = "course")
    private List<ClassRoom> classRooms;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;
}
