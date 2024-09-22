package starbucks3355.starbucksServer.vendor.repository;

import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.common.utils.CursorPage;

@Repository
public interface ProductListByCategoryRepositoryCustom {
	CursorPage<String> getProductsByCategoryList(
		String majorCategoryName,
		String middleCategoryName,
		Long lastId,
		Integer pageSize,
		Integer page
	);
}
