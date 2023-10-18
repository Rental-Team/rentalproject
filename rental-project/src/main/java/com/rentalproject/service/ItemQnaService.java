package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.ItemQnaDto;

public interface ItemQnaService {

    void writeItemQna(ItemQnaDto itemQna);
    
    List<ItemQnaDto> listItemQna(int itemNo);

	void deleteItemQna(int itemqnaNo);

	void editItemQna(ItemQnaDto itemQna);
    ////////////////////////////////////////////

	
	
	
	ItemQnaDto findItemQnaByItemQnaNo(int itemqnaNo);
	
	void updateSeqeunce(ItemQnaDto itemQna);
	
	void writeReplyQna(ItemQnaDto itemQna);

	

	
	
	
	
	
	
	

}
