package starbucks3355.starbucksServer.domainPromotion.service;

import java.util.List;

import starbucks3355.starbucksServer.domainPromotion.dto.out.PromotionNameResponseDto;
import starbucks3355.starbucksServer.domainPromotion.dto.out.PromotionResponseDto;

public interface PromotionService {
	List<PromotionResponseDto> getPromotionUuidList();

	PromotionNameResponseDto getPromotionName(String promotionUuid);

	// List<PromotionProductResponseDto> getPromotionProductList(String promotionUuid);

	List<PromotionNameResponseDto> getPromotionNameList();

}
