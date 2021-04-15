package com.tht.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "programme_Name")
    @NotBlank(message = "* không được để trống")
    @Size(min = 2, message = "* phải dài trên 2 kí tự")
    private String programmeName;

    @NotBlank(message = "* không được để trống")
    @Size(min = 5, message = "* phải dài hơn 5 kí tự")
    private String description;


    @Min(1)
    private Double tuition;

    @Min(1)
    private int lessons ;

    private boolean deleted;
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
