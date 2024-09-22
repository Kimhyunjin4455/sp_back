package starbucks3355.starbucksServer.vendor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.vendor.dto.out.CategoryAndCountOfSearchedProductListResponseDto;
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

	@Override
	public List<CategoryAndCountOfSearchedProductListResponseDto> getCategoryAndCountOfSearchedProductList(
		List<String> productUuidList) {
		return productListByCategoryRepositoryCustom.getCategoryAndCountOfSearchedProductList(productUuidList);
	}

	@Override
	public CursorPage<String> getProductByCategoryOfSearchedProducts(
		List<String> productUuidList,
		String topCategoryName,
		Long lastId,
		Integer pageSize,
		Integer page) {
		return productListByCategoryRepositoryCustom.getProductByCategoryOfSearchedProducts(
			productUuidList,
			topCategoryName,
			lastId,
			pageSize,
			page
		);
	}
}
