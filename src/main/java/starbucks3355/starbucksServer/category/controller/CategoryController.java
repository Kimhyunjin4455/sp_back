package starbucks3355.starbucksServer.category.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.category.dto.request.BottomCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.request.MiddleCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.request.TopCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.response.BottomCategoryResponseDto;
import starbucks3355.starbucksServer.category.dto.response.MiddleCategoryListResponseDto;
import starbucks3355.starbucksServer.category.dto.response.MiddleCategoryResponseDto;
import starbucks3355.starbucksServer.category.dto.response.TopCategoryResponseDto;
import starbucks3355.starbucksServer.category.repository.TopCategoryRepository;
import starbucks3355.starbucksServer.category.sevice.CategoryService;
import starbucks3355.starbucksServer.category.vo.request.BottomCategoryRequestVo;
import starbucks3355.starbucksServer.category.vo.request.MiddleCategoryRequestVo;
import starbucks3355.starbucksServer.category.vo.request.TopCategoryRequestVo;
import starbucks3355.starbucksServer.category.vo.response.BottomCategoryResponseVo;
import starbucks3355.starbucksServer.category.vo.response.MiddleCategoryResponseVo;
import starbucks3355.starbucksServer.category.vo.response.TopCategoryResponseVo;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Tag(name = "카테고리", description = "카테고리 관련 API")
public class CategoryController {

	private final CategoryService categoryService;
	private final TopCategoryRepository topCategoryRepository;

	@PostMapping("/top-category")
	@Operation(summary = "Top 카테고리 생성")
	public CommonResponseEntity<Void> createTopCategory(
		@RequestBody TopCategoryRequestVo topCategoryRequestVo) {
		TopCategoryRequestDto topCategoryRequestDto = TopCategoryRequestDto.builder()
			.topCategoryName(topCategoryRequestVo.getTopCategoryName())
			.build();
		categoryService.createTopCategory(topCategoryRequestDto);
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@PostMapping("/middle-category")
	@Operation(summary = "Middle 카테고리 생성")
	public CommonResponseEntity<Void> createMiddleCategory(
		@RequestBody List<MiddleCategoryRequestVo> middleCategoryRequestVo) {

		List<MiddleCategoryRequestDto> middleCategoryRequestDto = middleCategoryRequestVo.stream()
			.map(vo -> MiddleCategoryRequestDto.builder()
				.middleCategoryName(vo.getMiddleCategoryName())
				.topCategoryId(vo.getTopCategoryId())
				.build())
			.collect(Collectors.toList());
		// 서비스에 전달하여 여러 미들 카테고리 생성
		categoryService.createMiddleCategory(middleCategoryRequestDto);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@PostMapping("/bottom-category")
	@Operation(summary = "Bottom 카테고리 생성")
	public CommonResponseEntity<Void> createBottomCategory(
		@RequestBody List<BottomCategoryRequestVo> bottomCategoryRequestVo) {
		List<BottomCategoryRequestDto> bottomCategoryRequestDto = bottomCategoryRequestVo.stream()
			.map(vo -> BottomCategoryRequestDto.builder()
				.bottomCategoryName(vo.getBottomCategoryName())
				.middleCategoryId(vo.getMiddleCategoryId())
				.build())
			.collect(Collectors.toList());
		// 서비스에 전달하여 여러 바텀 카테고리 생성
		categoryService.createBottomCategory(bottomCategoryRequestDto);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	// @PostMapping("/bottom-category")
	// @Operation(summary = "Bottom 카테고리 생성")
	// public CommonResponseEntity<Void> createBottomCategory(
	// 	@RequestBody BottomCategoryRequestVo bottomCategoryRequestVo) {
	// 	BottomCategoryRequestDto bottomCategoryRequestDto = BottomCategoryRequestDto.builder()
	// 		.bottomCategoryName(bottomCategoryRequestVo.getBottomCategoryName())
	// 		.build();
	// 	categoryService.createBottomCategory(bottomCategoryRequestDto);
	// 	return new CommonResponseEntity<>(
	// 		HttpStatus.OK,
	// 		CommonResponseMessage.SUCCESS.getMessage(),
	// 		null);
	// }

	@GetMapping("/top-categories")
	@Operation(summary = "Top 전체 카테고리 조회")
	public CommonResponseEntity<List<TopCategoryResponseVo>> getTopCategories() {

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			categoryService.getTopCategories()
				.stream()
				.map(TopCategoryResponseDto::toVo)
				.collect(Collectors.toList()));
	}

	// @GetMapping("/middle-categories")
	// @Operation(summary = "Middle 전체 카테고리 조회")
	// public CommonResponseEntity<List<MiddleCategoryResponseVo>> getMiddleCategories() {
	// 	return new CommonResponseEntity<>(
	// 		HttpStatus.OK,
	// 		CommonResponseMessage.SUCCESS.getMessage(),
	// 		categoryService.getMiddleCategories()
	// 			.stream()
	// 			.map(MiddleCategoryResponseDto::toVo)
	// 			.collect(Collectors.toList()));
	// }

	@GetMapping("/bottom-categories")
	@Operation(summary = "Bottom 전체 카테고리 조회")
	public CommonResponseEntity<List<BottomCategoryResponseVo>> getBottomCategories() {
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			categoryService.getBottomCategories()
				.stream()
				.map(BottomCategoryResponseDto::toVo)
				.collect(Collectors.toList()));
	}

	@GetMapping("/top-categories/{topCategoryId}/middle-categories")
	@Operation(summary = " Top 카테고리 id에 '카테고리' 이름을 가진 Middle 카테고리")
	public CommonResponseEntity<MiddleCategoryListResponseDto> getMiddleCategoryByNameAndTopCategoryId(
		@PathVariable Integer topCategoryId) {
		String middleCategoryName = "카테고리";
		MiddleCategoryListResponseDto response = categoryService.getMiddleCategoryByNameAndTopCategoryId(
			topCategoryId,
			middleCategoryName);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			response);
	}

	// @GetMapping("/middle-categories/{topCategoryName}")
	// @Operation(summary = "Middle 카테고리 조회")
	// public CommonResponseEntity<List<MiddleCategoryResponseVo>> getMiddleCategories(
	// 	@PathVariable String topCategoryName) {
	// 	{
	// 		return new CommonResponseEntity<>(
	// 			HttpStatus.OK,
	// 			CommonResponseMessage.SUCCESS.getMessage(),
	// 			categoryService.getMiddleCategories(topCategoryName)
	// 				.stream()
	// 				.map(MiddleCategoryResponseDto::toVo)
	// 				.collect(Collectors.toList()));
	// 	}
	// }

	// @GetMapping("/bottom-categories/{middleCategoryCode}")
	// @Operation(summary = "Bottom 카테고리 조회")
	// public CommonResponseEntity<List<BottomCategoryResponseVo>> getBottomCategories(
	// 	@PathVariable String middleCategoryCode) {
	//
	// 	return new CommonResponseEntity<>(
	// 		HttpStatus.OK,
	// 		CommonResponseMessage.SUCCESS.getMessage(),
	// 		categoryService.getBottomCategories(middleCategoryCode)
	// 			.stream()
	// 			.map(BottomCategoryResponseDto::toVo)
	// 			.collect(Collectors.toList()));
	// }

	// @GetMapping("/top-category/{topCategoryCode}")
	// @Operation(summary = "단일 Top 카테고리 조회")
	// public CommonResponseEntity<TopCategoryResponseVo> getTopCategory(
	// 	@PathVariable String topCategoryCode) {
	// 	return new CommonResponseEntity<>(
	// 		HttpStatus.OK,
	// 		CommonResponseMessage.SUCCESS.getMessage(),
	// 		categoryService.getTopCategoryByCategoryCode(topCategoryCode).toVo());
	// }

	// @GetMapping("/middle-category/by-code/{middleCategoryCode}")
	// @Operation(summary = "단일 Middle 카테고리 조회")
	// public CommonResponseEntity<MiddleCategoryResponseVo> getMiddleCategory(
	// 	@PathVariable String middleCategoryCode) {
	// 	return new CommonResponseEntity<>(
	// 		HttpStatus.OK,
	// 		CommonResponseMessage.SUCCESS.getMessage(),
	// 		categoryService.getMiddleCategoryByCategoryCode(middleCategoryCode).toVo());
	// }

	// @GetMapping("/bottom-category/{bottomCategoryCode}")
	// @Operation(summary = "단일 Bottom 카테고리 조회")
	// public CommonResponseEntity<BottomCategoryResponseVo> getBottomCategory(
	// 	@PathVariable String bottomCategoryCode) {
	// 	return new CommonResponseEntity<>(
	// 		HttpStatus.OK,
	// 		CommonResponseMessage.SUCCESS.getMessage(),
	// 		categoryService.getBottomCategoryByCategoryCode(bottomCategoryCode).toVo());
	// }

	@GetMapping("/middle-categories/by-id/{topCategoryId}")
	@Operation(summary = "Top 카테고리 ID로 Middle 카테고리 조회")
	public CommonResponseEntity<List<MiddleCategoryResponseVo>> getMiddleCategoriesByTopCategoryId(
		@PathVariable Integer topCategoryId) {
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			// data -> 응답값에 실려 들어갈 데이터들
			categoryService.getMiddleCategoriesByTopCategoryId(topCategoryId)
				.stream()
				.map(MiddleCategoryResponseDto::toVo)
				.collect(Collectors.toList()));

	}

	@GetMapping("/bottom-categories/by-id/{middleCategoryId}")
	@Operation(summary = "Middle 카테고리 ID로 Bottom 카테고리 조회")
	public CommonResponseEntity<List<BottomCategoryResponseVo>> getBottomCategoriesByMiddleCategoryId(
		@PathVariable Integer middleCategoryId) {
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			// data -> 응답값에 실려 들어갈 데이터들
			categoryService.getBottomCategoriesByMiddleCategoryId(middleCategoryId)
				.stream()
				.map(BottomCategoryResponseDto::toVo)
				.collect(Collectors.toList()));
	}
}
