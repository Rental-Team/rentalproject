package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.FreeBoardAttachDto;
import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.dto.NoticeDto;

public interface NoticeService {
	
	void writeNotice(NoticeDto notice) throws Exception;
	
	List<NoticeDto> listNotice();   
	
	NoticeDto findNoticeByNoticeNo(int noticeNo); 

	void editNotice(NoticeDto notice);
	
	int getNoticeCount();
	
	
	
	void updateviewCount(int viewCount);


	List<NoticeDto> listNoticeByPage(int from, int count);

	
}
