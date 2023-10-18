package com.rentalproject.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FreeBoardReviewDto {
	
	private int freeBoardReplyNo;
	private int freeBoardNo;
	private String replyContent;
	private Date replyCreateDate;
	private Date replyModifiYDate;
	private int replyParents;
	private int replySequence;
	private int replyDepth;
	private String replyWriter;
	private String replyWriterImage;
	private boolean replyDelete; 
	
}
