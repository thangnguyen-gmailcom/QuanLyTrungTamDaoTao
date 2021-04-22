package com.tht.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private float point;

    @ManyToOne
    @JoinColumn(name = "student_class_id")
    @JsonIgnore
    private StudentClass studentClass;

    @ManyToOne
    @JoinColumn(name = "examp_type_id")
    private ExamType examType;
}
