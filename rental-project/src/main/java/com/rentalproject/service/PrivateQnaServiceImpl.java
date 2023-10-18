package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.PrivateQnaAnswerDto;
import com.rentalproject.dto.PrivateQnaAttachDto;
import com.rentalproject.dto.PrivateQnaDto;
import com.rentalproject.mapper.PrivateQnaAnswerMapper;
import com.rentalproject.mapper.PrivateQnaMapper;

public class PrivateQnaServiceImpl implements PrivateQnaService {

	@Autowired
	private PrivateQnaMapper privateQnaMapper;
	
	@Autowired
	private PrivateQnaAnswerMapper privateQnaAnswerMapper;
	
	@Override
	public void writeBoard(PrivateQnaDto privateqna)  {
	
			privateQnaMapper.insertBoard(privateqna);
	
			for(PrivateQnaAttachDto privateQnaAttach : privateqna.getPrivateQnaAttachList()) {
				privateQnaAttach.setQnaNo(privateqna.getQnaNo());
				privateQnaMapper.insertPrivateQnaAttach(privateQnaAttach);
				
				
			}
	
	
	}
	

	/* 리스트조회 */ 
	
	
	@Override
	public List<PrivateQnaDto> listBoard(int from, int count) {
		
		   List<PrivateQnaDto>  qnaBoardList = privateQnaMapper.selectAllBoard(from,count);
		   return qnaBoardList;
	}
	
	
	/* 페이징 */
	
	@Override 
	public List<PrivateQnaDto> listPrivateQnaByPage(int from, int count) {
		   List<PrivateQnaDto> privateQnaList = privateQnaMapper.selectPrivateQnaByPage(from, count);
		  
		  return privateQnaList;
	}
		
	
	
	@Override // 페이징 - 총 게시물 개수를 db에서 가져오기 
	public int getPrivateQnaCount() {
		   int count = privateQnaMapper.selectPrivateQnaCount();
		return count;
	}


	/* 멤버본인이 쓴 글 카운트 */
	
	@Override
	public int getPrivateQnaCountByMemberNo(int memberNo) {
	    	int count = privateQnaMapper.selectPrivateQnaCountByMemberNo(memberNo);
	     return count;
	}
	
	
	
	
	/* 여기 디테일 */
	 @Override
	 public PrivateQnaDto findQnaBoardByQnaNo(int qnaNo) {
		 	
		 	PrivateQnaDto  qnaBoardList = privateQnaMapper.selectQnaBoardByQnaNo(qnaNo);
		
	  
		 	
		 	
		 	
		 	List<PrivateQnaAttachDto> privateQnaAttachList = privateQnaMapper.selectPrivateQnaAttachByQnaNo(qnaNo);
		 	qnaBoardList.setPrivateQnaAttachList(privateQnaAttachList);
		 	
		 	
		 	List<PrivateQnaAnswerDto> answerList = privateQnaAnswerMapper.selectPrivateQnaAnserbyQnaNo(qnaNo);
		 	qnaBoardList.setPrivateQnaAnswerList(answerList);
	   		   
	   
	   return  qnaBoardList;
	}
	 
	 
	 
	 @Override
		public PrivateQnaAttachDto selectPrivateQnaAttachByAttachNo(int attachNo) {
		 	PrivateQnaAttachDto privateQnaAttach = privateQnaMapper.selectPrivateQnaAttachByAttachNo(attachNo);
			
			
			
			return  privateQnaAttach;
		}
 
	 
	 
	 

	 @Override //답변 완료 업데이트
	 public void updateAnswerStatus(int qnaNo, boolean answered) {
	      
	  privateQnaMapper.updateAnswerStatus(qnaNo, answered);
 }
	 @Override
	 public boolean getAnswerStatus(int qnaNo) {
		   boolean answerStatus = privateQnaMapper.getAnswerStatus(qnaNo);
		 	
		 	return answerStatus;
	 }

	 @Override
	 public String getMemberIdByQnaNo(int qnaNo) {
		 	String memberId = privateQnaMapper.getMemberIdByQnaNo(qnaNo);
		 
		 	return memberId;
	 }

		
	 
	/*
	 * @Override public List<PrivateQnaDto> listBoardByUserId(int userId) {
	 * List<PrivateQnaDto> boardList;
	 * 
	 * if (userId == 17) { boardList = privateQnaMapper.selectAllBoard(); } else {
	 * boardList = privateQnaMapper.selectBoardByUserId(userId); }
	 * 
	 * return boardList; }
	 */
	 
	 //관리자는 모든 리스트 조회 , 일반 회원은 본인글만 조회
	 @Override
	 public List<PrivateQnaDto> listBoardByMemberNo(int memberNo, int from, int count) {
	     	List<PrivateQnaDto> qnaBoardList;

	     if (memberNo == 17) {
	    	 	qnaBoardList = privateQnaMapper.selectAllBoard(from, count);
	     } else {
	    	 	qnaBoardList = privateQnaMapper.selectBoardByMemberNo(memberNo, from, count);
	     }

	     return qnaBoardList;
	 }
	
	 
	 /* 미답변 목록 조회 */
	
	@Override
	public List<PrivateQnaDto> unAnswerlist(int from, int count) {
		   List<PrivateQnaDto> qnaBoardList;
		
				  qnaBoardList = privateQnaMapper.selectAllUnanswered(from, count);
	
		   return qnaBoardList;
	}
	
	@Override
	public int getUnanswerListCount() {
		int count = privateQnaMapper.selectPrivateQnaUnansweredCountByFalse();
		return count;
	}
	
	
	
	
	 
	 
	@Override
	public List<PrivateQnaDto> searchByMemberId(String memberId) {
		   List<PrivateQnaDto> qnaBoardList;
	
		   	   qnaBoardList=privateQnaMapper.searchPrivateQnaByMemberId(memberId, getPrivateQnaCount(), getPrivateQnaCount());
		
		return qnaBoardList;
	}
	
//	@Override
//	public PrivateQnaDto searchByQnaNo(int qnaNo) {
//	    PrivateQnaDto result = privateQnaMapper.searchQnaNoByQnaNo(qnaNo);
//	    return result;
//	}
	
	/* qnaNo검색 */
	
	@Override
	public List<PrivateQnaDto> searchByQnaNo(int qnaNo) {
		   List<PrivateQnaDto> result = privateQnaMapper.searchQnaNoByQnaNo(qnaNo);
	   
	    return result;
	}


	
	

	 
	
	


}