package com.tht.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class SavePoint {

    private Long idStudentClass;

    private List<Float> points;

    private List<Long> examTypes;
}
