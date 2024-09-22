package starbucks3355.starbucksServer.vendor.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.vendor.dto.out.ProductListByPromotionResponseDto;

@Repository
public interface ProductListByPromotionRepositoryCustom {
	List<ProductListByPromotionResponseDto> getProductByPromotionList(String promotionUuid);

	List<ProductListByPromotionResponseDto> getProductsBySamePromotion(String productUuid);
}
