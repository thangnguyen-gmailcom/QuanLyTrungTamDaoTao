package com.tht.demo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TimeTableData {
    private List<Integer> daysOfWeek;
    private long classId;
}
