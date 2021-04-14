package com.tht.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Course> courses;

    @OneToMany(mappedBy = "programme")
    @JsonIgnore
    private List<Lesson> lessonList;
    @Override
    public String toString() {
        return "Programme{" +
                "id=" + id +
                ", programmeName='" + programmeName + '\'' +
                ", description='" + description + '\'' +
                ", tuition=" + tuition +
                ", lessons=" + lessons +
                '}';
    }
}
