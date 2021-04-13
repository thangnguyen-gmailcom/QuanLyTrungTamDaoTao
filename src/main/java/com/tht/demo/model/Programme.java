package com.tht.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "programme_Name")
    private String programmeName;

    private String description;

    private Double tuition;

    private int lessons ;

    @OneToMany(mappedBy = "programme")
    private List<Course> courses;
}
