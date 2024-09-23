package starbucks3355.starbucksServer.vendor.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.vendor.dto.out.CategoryAndCountOfSearchedProductListResponseDto;

@Repository
public interface ProductListByCategoryRepositoryCustom {
	CursorPage<String> getProductsByCategoryList(
		String majorCategoryName,
		String middleCategoryName,
		Long lastId,
		Integer pageSize,
		Integer page
	);

	List<CategoryAndCountOfSearchedProductListResponseDto> getCategoryAndCountOfSearchedProductList(
		List<String> productUuidList
	);

	CursorPage<String> getProductByCategoryOfSearchedProducts(
		List<String> productUuidList,
		String topCategoryName,
		Long lastId,
		Integer pageSize,
		Integer page
	);
}
