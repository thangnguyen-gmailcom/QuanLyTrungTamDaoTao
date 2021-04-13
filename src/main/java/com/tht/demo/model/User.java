package com.tht.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private String fullname;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "id_card")
    private String idCard;

    private int gender;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "edited_date")
    private LocalDateTime editedDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    private String image;

    private String information;

    @ManyToOne
    @JoinColumn(name = "staff_created_id")
    private User staffCreatedId;

    @OneToMany(mappedBy = "staffCreatedId")
    private List<User> users;

    @OneToMany(mappedBy = "teacher")
    private List<ClassRoom> classRooms2;

    @ManyToOne
    @JoinColumn(name = "staff_edited_id")
    private User staffEditedId;

    @OneToMany(mappedBy = "staffEditedId")
    private List<User> userList;

    @OneToMany(mappedBy = "user")
    private List<ClassRoom> classRooms;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles roles;

    @OneToMany(mappedBy = "user")
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
    private Province province;
}
