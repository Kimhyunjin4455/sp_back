package starbucks3355.starbucksServer.vendor.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.vendor.repository.ProductListByPromotionRepositoryCustom;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductListByPromotionServiceImpl implements ProductListByPromotionService {
	private final ProductListByPromotionRepositoryCustom productListByPromotionRepositoryCustom;

	@Override
	public CursorPage<String> getProductListByPromotion(
		String promotionUuid,
		Long lastId,
		Integer pageSize,
		Integer page
	) {
		return productListByPromotionRepositoryCustom.getProductByPromotionList(promotionUuid, lastId, pageSize, page);
	}

	@Override
	public CursorPage<String> getProductsBySamePromotion(
		String productUuid,
		Long lastId,
		Integer pageSize,
		Integer page
	) {
		return productListByPromotionRepositoryCustom.getProductsBySamePromotion(productUuid, lastId, pageSize, page);
	}
}
