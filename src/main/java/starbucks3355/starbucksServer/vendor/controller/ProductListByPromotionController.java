package starbucks3355.starbucksServer.vendor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.BaseResponse;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.vendor.service.ProductListByPromotionService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/promotion")
@Tag(name = "Promotion", description = "기획전 API")
public class ProductListByPromotionController {
	private final ProductListByPromotionService productListByPromotionService;

	// 기획전의 uuid를 받아 상품 목록을 조회
	@GetMapping("/productsOfPromotion")
	@Operation(summary = "기획전별 상품 목록 조회")
	public BaseResponse<CursorPage<String>> getProductListByPromotion(
		@RequestParam String promotionUuid,
		@RequestParam(required = false) Long lastId,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size
	) {
		CursorPage<String> productListByPromotion = productListByPromotionService.getProductListByPromotion(
			promotionUuid, lastId, page, size);

		if (productListByPromotion == null) {
			return new BaseResponse<>(
				HttpStatus.OK,
				BaseResponseStatus.NO_EXIST_PRODUCT.isSuccess(),
				BaseResponseStatus.NO_EXIST_PRODUCT.getMessage(),
				BaseResponseStatus.NO_EXIST_PRODUCT.getCode(),
				null
			);
		}

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			productListByPromotion

		);
	}

	@GetMapping("/samePromotionProducts")
	@Operation(summary = "현재 상품과 같은 기획전 인 상품 목록 조회")
	public BaseResponse<CursorPage<String>> getProductsBySamePromotion(
		@RequestParam String productUuid,
		@RequestParam(required = false) Long lastId,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size
	) {
		CursorPage<String> productListByPromotion = productListByPromotionService.getProductsBySamePromotion(
			productUuid, lastId, page, size);

		if (productListByPromotion == null) {
			return new BaseResponse<>(
				HttpStatus.OK,
				BaseResponseStatus.NO_EXIST_PRODUCT.isSuccess(),
				BaseResponseStatus.NO_EXIST_PRODUCT.getMessage(),
				BaseResponseStatus.NO_EXIST_PRODUCT.getCode(),
				null
			);
		}

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			productListByPromotion

		);
	}
}
