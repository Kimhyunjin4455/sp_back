package starbucks3355.starbucksServer.vendor.service;

import starbucks3355.starbucksServer.common.utils.CursorPage;

public interface ProductListByCategoryService {
	// 상품 카테고리별 리스트 조회
	CursorPage<String> getProductsByCategoryList(
		String majorCategoryName,
		String middleCategoryName,
		Long lastId,
		Integer pageSize,
		Integer page
	);
}
