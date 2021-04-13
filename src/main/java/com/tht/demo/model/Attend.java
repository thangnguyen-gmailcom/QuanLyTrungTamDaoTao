package com.tht.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Attend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int status;

    private String content;

    @ManyToOne
    @JoinColumn(name = "timetable_id")
    private TimeTable timeTable;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User user;
}
