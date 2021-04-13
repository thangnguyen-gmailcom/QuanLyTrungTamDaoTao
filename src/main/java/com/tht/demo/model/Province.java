package com.tht.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "devvn_tinhthanhpho")
public class Province {

    @Id
    @Column(name = "matp")
    private String provinceCode;

    @Column(name = "name")
    private String provinceName;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "province")
    private List<District> districts;

    @OneToMany(mappedBy = "province")
    private List<User> users;
}
