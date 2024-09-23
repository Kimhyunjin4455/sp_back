package starbucks3355.starbucksServer.vendor.service;

import java.util.List;

import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.vendor.dto.out.CategoryAndCountOfSearchedProductListResponseDto;

public interface ProductListByCategoryService {
	// 상품 카테고리별 리스트 조회
	CursorPage<String> getProductsByCategoryList(
		String majorCategoryName,
		String middleCategoryName,
		Long lastId,
		Integer pageSize,
		Integer page
	);

	// 상품의 uuid 리스트를 받아서 어떠한 topCategory에 속하며 몇개 속하는지 dto로 반환
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
