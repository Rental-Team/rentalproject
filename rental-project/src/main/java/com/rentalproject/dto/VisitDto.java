package com.rentalproject.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class VisitDto {

    private Date visitDate;
    private int visitCount;
}
