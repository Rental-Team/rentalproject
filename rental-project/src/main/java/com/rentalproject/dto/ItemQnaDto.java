package com.rentalproject.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ItemQnaDto {
    private int itemqnaNo;
    private int itemNo;
    private String itemQnaWriter;
    private String itemqnaContent;
    private Date itemqnaDate;
    private int parents;
    private int sequence;
    private int depth;
    private boolean itemqnaDelete;

	/////////////////////////////////
	
	
	
}
