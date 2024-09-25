package starbucks3355.starbucksServer.vendor.repository;

import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.common.utils.CursorPage;

@Repository
public interface ProductListByPromotionRepositoryCustom {
	CursorPage<String> getProductByPromotionList(
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
