package com.tht.demo.model;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class ExamType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
     @NotBlank(message = "* không được để trống")
     @Size(min = 2,message = "* độ dài phải lớn hơn 2 kí tự")
     private String title;
    @NotBlank(message = "* không được để trống")
    @Size(min = 2,message = "* độ dài phải lớn hơn 2 kí tự")
    private String description;

    private boolean deleted;

    @OneToMany(mappedBy = "examType")
    private List<Point> points;

    @Override
    public String toString() {
        return "ExamType{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
