package com.tht.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<District> districts;

    @Override
    public String toString() {
        return "Province{" +
                "provinceCode='" + provinceCode + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
