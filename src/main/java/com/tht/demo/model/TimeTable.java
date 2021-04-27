package com.tht.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;

    @Column(name = "teacher_note")
    private String teacherNote;

    private String lessonTitle;

    private String lessonContent;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassRoom classRoom;

    @ManyToOne
    @JoinColumn(name = "user_created_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @OneToMany(mappedBy = "timeTable")
    @JsonIgnore
    private List<Attend> attends;
}
