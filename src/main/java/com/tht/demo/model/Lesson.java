package com.tht.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "lesson_number")
    private int lessonNumber;

    private String content;

    @ManyToOne
    @JoinColumn(name = "programme_id")
    private Programme programme;
}
