package starbucks3355.starbucksServer.vendor.dto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.BaseResponse;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.vendor.dto.out.ProductListByPromotionResponseDto;
import starbucks3355.starbucksServer.vendor.service.ProductListByPromotionService;
import starbucks3355.starbucksServer.vendor.vo.out.ProductListByPromotionResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/promotion")
@Tag(name = "Promotion", description = "기획전 API")
public class ProductListByPromotionController {
	private final ProductListByPromotionService productListByPromotionService;

	// 기획전의 uuid를 받아 상품 목록을 조회
	@GetMapping("/product/{promotionUuid}")
	@Operation(summary = "기획전별 상품 목록 조회")
	public BaseResponse<List<ProductListByPromotionResponseVo>> getProductListByPromotion(
		@PathVariable String promotionUuid
	) {
		List<ProductListByPromotionResponseDto> productListByPromotion = productListByPromotionService.getProductListByPromotion(
			promotionUuid);

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			productListByPromotion.stream()
				.map(ProductListByPromotionResponseDto::dtoToResponseVo)
				.toList()

		);
	}
}
