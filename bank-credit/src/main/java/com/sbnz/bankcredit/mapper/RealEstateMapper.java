package com.sbnz.bankcredit.mapper;

import com.sbnz.bankcredit.dto.RealEstateDTO;
import com.sbnz.bankcredit.model.RealEstate;

public class RealEstateMapper {
	
	public static RealEstate getRealEstateFromDTO (RealEstateDTO reDTO) {
		RealEstate re = new RealEstate(reDTO.getZone(), reDTO.getRealEstateType(), reDTO.getSquareFootage());
		return re;
	}
}
