package com.tht.demo.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Arrays;

@Data
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "* không được để trống")
    @Size(min = 2, message = "* độ dài từ 2 kí tự trở lên")
    private String title;

    @Column(name = "short_content")
    @NotBlank(message = "* không được để trống")
    @Size(min = 2, message = "* độ dài từ 2 kí tự trở lên")
    private String shortContent;

    @Column(name = "full_content")
    @NotBlank(message = "* không được để trống")
    @Size(min = 2, message = "* độ dài từ 2 kí tự trở lên")
    private String fullContent;

    private String image;

    @Transient
    private MultipartFile[] imageUrl;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
    private boolean deleted;
    @ManyToOne
    @JoinColumn(name = "user_created_id")
    private User user;


    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortContent='" + shortContent + '\'' +
                ", fullContent='" + fullContent + '\'' +
                ", image='" + image + '\'' +
                ", imageUrl=" + Arrays.toString(imageUrl) +
                ", createdDate=" + createdDate +
                ", deleted=" + deleted +
                ", user=" + user +
                '}';
    }
}
