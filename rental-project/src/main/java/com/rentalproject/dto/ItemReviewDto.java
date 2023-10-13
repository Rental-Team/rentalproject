package com.rentalproject.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class ItemReviewDto {
	private int reviewNo;
	private int itemNo;
	private String reviewWriter;
	private int rating;
	private String reviewContent;
	private Date reviewDate= Timestamp.valueOf(LocalDateTime.now());
	private int parents;
	private int sequence;
	private int depth;
	private boolean itemReviewDelete;
}
