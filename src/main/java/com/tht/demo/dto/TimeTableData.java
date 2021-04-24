package com.tht.demo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TimeTableData {
    private long id;
    private List<String> DaysOfWeek;
    private LocalDate startDate;
    private long classId;
}
