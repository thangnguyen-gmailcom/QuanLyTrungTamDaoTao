package com.tht.demo.model;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Arrays;

@Data
@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String image;


    @NotBlank(message = "* không được để trống")
    @Size(min = 2, message = "* độ dài từ 2 kí tự trở lên và dưới 80 kí tự ", max = 80)
    private String description;

    @Transient
    private MultipartFile[] imageUrl;

    private boolean deleted;

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl=" + Arrays.toString(imageUrl) +
                ", deleted=" + deleted +
                '}';
    }
}
