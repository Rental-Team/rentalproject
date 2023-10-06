package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.FreeBoardAttachDto;
import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.dto.FreeBoardReviewDto;
import com.rentalproject.dto.NoticeDto;
import com.rentalproject.mapper.FreeBoardMapper;
import com.rentalproject.mapper.FreeBoardReviewMapper;
import com.rentalproject.mapper.NoticeMapper;

public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public void writeNotice(NoticeDto notice) throws Exception {

		noticeMapper.writenotice(notice);

	}

	@Override
	public List<NoticeDto> listNotice() {

		List<NoticeDto> noticeList = noticeMapper.selectAllnotice();

		return noticeList;

	}

	@Override
	public NoticeDto findNoticeByNoticeNo(int noticeNo) {

		NoticeDto notice = noticeMapper.selectnoticeBynoticeNo(noticeNo);

		return notice;

	}

	@Override
	public void editNotice(NoticeDto notice) {
		noticeMapper.updatenotice(notice);

	}

	

	
	  @Override
	  public void updateviewCount(int viewCount) {
	  noticeMapper.updateviewCount(viewCount);
	  }

	@Override
	public int getNoticeCount() {
		
		int count = noticeMapper.selectNoticeCount();
		return count;
	}

	@Override
	public List<NoticeDto> listNoticeByPage(int from, int count) {
		List<NoticeDto> noticeList = noticeMapper.selectNoticeByPage(from, count);
		return noticeList;
	}


	 
}
