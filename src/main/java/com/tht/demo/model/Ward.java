package com.tht.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "devvn_xaphuongthitran")
public class Ward {

    @Id
    @Column(name = "xaid")
    private String wardCode;

    @Column(name = "name")
    private String wardName;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "maqh")
    private District district;

    @OneToMany(mappedBy = "ward")
    @JsonIgnore
    private List<User> users;
}
