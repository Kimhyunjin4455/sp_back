package starbucks3355.starbucksServer.domainPromotion.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainPromotion.dto.out.PromotionNameResponseDto;
import starbucks3355.starbucksServer.domainPromotion.dto.out.PromotionResponseDto;

@Repository
public interface PromotionRepositoryCustom {
	List<PromotionResponseDto> getPromotionUuidList(
	);

	PromotionNameResponseDto getPromotionName(String promotionUuid);

	// List<PromotionProductResponseDto> getPromotionProductList(String promotionUuid);

	List<PromotionNameResponseDto> getPromotionNameList();
}
