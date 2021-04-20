package com.tht.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "* email không được để trống")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "* email sai định dạng")
    private String email;

    private String password;

    @NotBlank(message = "* họ và tên không được để trống")
    @Size(min = 10, max = 100, message = "* họ và tên phải từ 10 đến 100 ký tự")
    private String fullname;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "phone_number")
    @NotBlank(message = "* số điện thoại không được để trống")
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "* số điện thoại không đúng định dạng")
    private String phoneNumber;

    @Column(name = "id_card")
    @NotNull(message = "* số chứng minh nhân dân không được để trống")
    @Pattern(regexp = "[0-9]{9}", message = "* số chứng minh nhân dân không đúng định dạng")
    private String idCard;

    private int gender;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "edited_date")
    private LocalDateTime editedDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    private String image;

    @Transient
    private MultipartFile[] imageUrl;

    private String information;

    @ManyToOne
    @JoinColumn(name = "staff_created_id")
    private User staffCreatedId;

    @OneToMany(mappedBy = "staffCreatedId")
    @JsonIgnore
    private List<User> users;


    @ManyToOne
    @JoinColumn(name = "staff_edited_id")
    private User staffEditedId;

    @OneToMany(mappedBy = "staffEditedId")
    @JsonIgnore
    private List<User> userList;

    @OneToMany(mappedBy = "userCreated")
    @JsonIgnore
    private List<ClassRoom> classRooms;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @NotNull(message = "Quyền không được để trống")
    private Roles roles;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<StudentClass> studentClasses;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs;

    @OneToMany(mappedBy = "user")
    private List<TimeTable> userCreated;

    @OneToMany(mappedBy = "teacher")
    private List<TimeTable> teacher;

    @OneToMany(mappedBy = "user")
    private List<Attend> attends;

    @ManyToOne
    @JoinColumn(name = "address")
    @NotNull(message = "xã-phường không được để trống")
    private Ward ward;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<ClassRoom> classRooms2;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", idCard='" + idCard + '\'' +
                ", gender=" + gender +
                ", isDeleted=" + isDeleted +
                ", image='" + image + '\'' +
                ", information='" + information + '\'' +
                ", roles=" + roles +
                '}';
    }
}
