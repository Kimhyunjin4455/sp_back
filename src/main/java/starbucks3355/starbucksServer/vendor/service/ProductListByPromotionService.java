package starbucks3355.starbucksServer.vendor.service;

import java.util.List;

import starbucks3355.starbucksServer.vendor.dto.out.ProductListByPromotionResponseDto;

public interface ProductListByPromotionService {
	List<ProductListByPromotionResponseDto> getProductListByPromotion(String promotionUuid);

}
