package com.tht.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "devvn_quanhuyen")
public class District {

    @Id
    @Column(name = "maqh")
    private String districtCode;

    @Column(name = "name")
    private String distinctName;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "matp")
    private Province province;

    @OneToMany(mappedBy = "district")
    @JsonIgnore
    private List<Ward> wards;
}
