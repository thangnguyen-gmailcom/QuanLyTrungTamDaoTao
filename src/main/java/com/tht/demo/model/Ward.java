package com.tht.demo.model;


import lombok.Data;

import javax.persistence.*;

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
}
