package com.tht.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "lesson_number")
    @NotBlank(message = "* không được để trống")
    private String lessonNumber;
    @NotBlank(message = "* không được để trống")
    private String content;

    private boolean deleted;
    @ManyToOne
    @JoinColumn(name = "programme_id")
    private Programme programme;

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonNumber=" + lessonNumber +
                ", content='" + content + '\'' +
                ", deleted=" + deleted +
                '}';
    }

}
