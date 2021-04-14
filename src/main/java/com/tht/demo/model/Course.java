package com.tht.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "course_name")
    @NotBlank(message = "* không được để trống")
    @Size(min = 2, message = "* độ dài phải lớn hơn 2 kí tự")
    private String courseName;
    @NotBlank(message = "* không được để trống")
    @Size(min = 2,message = "* độ dài phải lớn hơn 2 kí tự")
    private String description;
    private boolean deleted;
    @ManyToOne
    @JoinColumn(name = "programme_id")
    private Programme programme;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<ClassRoom> classRooms;



    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
