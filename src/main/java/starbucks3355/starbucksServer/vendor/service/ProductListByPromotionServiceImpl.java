package starbucks3355.starbucksServer.vendor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.vendor.dto.out.ProductListByPromotionResponseDto;
import starbucks3355.starbucksServer.vendor.repository.ProductListByPromotionRepositoryCustom;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductListByPromotionServiceImpl implements ProductListByPromotionService {
	private final ProductListByPromotionRepositoryCustom productListByPromotionRepositoryCustom;

	@Override
	public List<ProductListByPromotionResponseDto> getProductListByPromotion(String promotionUuid) {
		return productListByPromotionRepositoryCustom.getProductByPromotionList(promotionUuid);
	}
}
