package starbucks3355.starbucksServer.vendor.service;

import starbucks3355.starbucksServer.common.utils.CursorPage;

public interface ProductListByPromotionService {
	CursorPage<String> getProductListByPromotion(
		String promotionUuid,
		Long lastId,
		Integer pageSize,
		Integer page);

	CursorPage<String> getProductsBySamePromotion(
		String productUuid,
		Long lastId,
		Integer pageSize,
		Integer page);

}
