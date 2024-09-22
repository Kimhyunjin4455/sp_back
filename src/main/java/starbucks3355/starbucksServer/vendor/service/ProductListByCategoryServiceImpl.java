package starbucks3355.starbucksServer.vendor.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.vendor.repository.ProductListByCategoryRepositoryCustom;

@RequiredArgsConstructor
@Service
public class ProductListByCategoryServiceImpl implements ProductListByCategoryService {
	private final ProductListByCategoryRepositoryCustom productListByCategoryRepositoryCustom;

	@Override
	public CursorPage<String> getProductsByCategoryList(String majorCategoryName, String middleCategoryName,
		Long lastId, Integer pageSize, Integer page) {
		return productListByCategoryRepositoryCustom.getProductsByCategoryList(
			majorCategoryName,
			middleCategoryName,
			lastId,
			pageSize,
			page
		);

	}
}
