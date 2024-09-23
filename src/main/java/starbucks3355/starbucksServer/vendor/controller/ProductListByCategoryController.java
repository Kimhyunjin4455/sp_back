package starbucks3355.starbucksServer.vendor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.entity.BaseResponse;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.vendor.service.ProductListByCategoryService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
@Tag(name = "카테고리", description = "카테고리 관련 API")
public class ProductListByCategoryController {

	private final ProductListByCategoryService productListByCategoryService;

	// 카테고리별 상품 목록 조회
	@GetMapping("/inCategoryProducts")
	@Operation(summary = "카테고리별 상품 목록 조회")
	public BaseResponse<CursorPage<String>> getProductsByCategoryList(
		@RequestParam(required = false) String majorCategoryName,
		@RequestParam(required = false) String middleCategoryName,
		@RequestParam(required = false) Long lastId,
		@RequestParam(required = false, defaultValue = "10") Integer pageSize,
		@RequestParam(required = false, defaultValue = "1") Integer page
	) {
		return new BaseResponse<>(productListByCategoryService.getProductsByCategoryList(
			majorCategoryName,
			middleCategoryName,
			lastId,
			pageSize,
			page
		));
	}

}
