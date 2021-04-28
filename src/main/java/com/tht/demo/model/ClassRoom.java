package com.tht.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "class_name")
    @NotBlank(message = "* tên lớp không được để trống")
    private String className;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @NotNull(message = "* phải chọn giáo viên")
    private User teacher;

    private String studyTime;

    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @NotNull(message = "* phải chọn khóa học")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "staff_created")
    private User userCreated;

    @OneToMany(mappedBy = "classRoom")
    @JsonIgnore
    private List<StudentClass> studentClasses;

    @OneToMany(mappedBy = "classRoom")
    @JsonIgnore
    private List<TimeTable> timeTables;

    private boolean deleted;

    private boolean statusTimeTable;

    @Override
    public String toString() {
        return "ClassRoom{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", createdDate=" + createdDate +
                ", teacher=" + teacher +
                ", course=" + course +
                ", user=" + userCreated +
                ", deleted=" + deleted +
                '}';
    }
}
