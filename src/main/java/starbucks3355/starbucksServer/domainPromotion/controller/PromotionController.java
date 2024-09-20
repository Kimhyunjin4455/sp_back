package starbucks3355.starbucksServer.domainPromotion.controller;

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
import starbucks3355.starbucksServer.domainPromotion.dto.out.PromotionNameResponseDto;
import starbucks3355.starbucksServer.domainPromotion.dto.out.PromotionResponseDto;
import starbucks3355.starbucksServer.domainPromotion.service.PromotionService;
import starbucks3355.starbucksServer.domainPromotion.vo.out.PromotionNameResponseVo;
import starbucks3355.starbucksServer.domainPromotion.vo.out.PromotionResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/promotion")
@Tag(name = "Promotion", description = "기획전 API")
public class PromotionController {
	private final PromotionService promotionService;

	@GetMapping("/list")
	@Operation(summary = "기획전 uuid 목록 조회")
	public BaseResponse<List<PromotionResponseVo>> getPromotionList() {

		List<PromotionResponseDto> promotionUuidList = promotionService.getPromotionUuidList();

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			promotionUuidList.stream()
				.map(PromotionResponseDto::dtoToResponseVo)
				.toList()
		);
	}

	// 기획전의 uuid를 받아 기획전 명을 조회
	@GetMapping("/name/{promotionUuid}")
	@Operation(summary = "기획전 이름 조회")
	public BaseResponse<PromotionNameResponseVo> getPromotionName(
		@PathVariable String promotionUuid
	) {
		PromotionNameResponseDto promotionName = promotionService.getPromotionName(promotionUuid);

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			promotionName.dtoToResponseVo()
		);
	}

	// 기획전의 uuid를 받아 기획전 상품 목록을 조회
	// @GetMapping("/products/{promotionUuid}")
	// @Operation(summary = "기획전 상품 목록 조회")
	// public BaseResponse<List<PromotionProductResponseVo>> getPromotionProductList(
	// 	@PathVariable String promotionUuid
	// ) {
	// 	List<PromotionProductResponseDto> promotionProductList = promotionService.getPromotionProductList(
	// 		promotionUuid);
	//
	// 	return new BaseResponse<>(
	// 		HttpStatus.OK,
	// 		BaseResponseStatus.SUCCESS.isSuccess(),
	// 		BaseResponseStatus.SUCCESS.getMessage(),
	// 		BaseResponseStatus.SUCCESS.getCode(),
	// 		promotionProductList.stream()
	// 			.map(PromotionProductResponseDto::dtoToResponseVo)
	// 			.toList()
	// 	);
	// }
}
