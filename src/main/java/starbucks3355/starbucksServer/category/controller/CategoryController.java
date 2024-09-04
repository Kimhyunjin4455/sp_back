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
import starbucks3355.starbucksServer.category.dto.response.MiddleCategoryResponseDto;
import starbucks3355.starbucksServer.category.dto.response.TopCategoryResponseDto;
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

	@PostMapping("/top-category")
	@Operation(summary = "Top 카테고리 생성")
	public CommonResponseEntity<Void> createTopCategory(
		@RequestBody TopCategoryRequestVo topCategoryRequestVo) {
		TopCategoryRequestDto topCategoryRequestDto = TopCategoryRequestDto.builder()
			.topCategoryName(topCategoryRequestVo.getTopCategoryName())
			.topCategoryDescription(topCategoryRequestVo.getTopCategoryDescription())
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
		@RequestBody MiddleCategoryRequestVo middleCategoryRequestVo) {

		MiddleCategoryRequestDto middleCategoryRequestDto = MiddleCategoryRequestDto.builder()
			.middleCategoryName(middleCategoryRequestVo.getMiddleCategoryName())
			.middleCategoryDescription(middleCategoryRequestVo.getMiddleCategoryDescription())
			.topCategoryCode(middleCategoryRequestVo.getTopCategoryCode())
			.build();
		categoryService.createMiddleCategory(middleCategoryRequestDto);
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@PostMapping("/bottom-category")
	@Operation(summary = "Bottom 카테고리 생성")
	public CommonResponseEntity<Void> createBottomCategory(
		@RequestBody BottomCategoryRequestVo bottomCategoryRequestVo) {
		BottomCategoryRequestDto bottomCategoryRequestDto = BottomCategoryRequestDto.builder()
			.bottomCategoryName(bottomCategoryRequestVo.getBottomCategoryName())
			.bottomCategoryDescription(bottomCategoryRequestVo.getBottomCategoryDescription())
			.middleCategoryCode(bottomCategoryRequestVo.getMiddleCategoryCode())
			.build();
		categoryService.createBottomCategory(bottomCategoryRequestDto);
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

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

	@GetMapping("/middle-categories/{topCategoryCode}")
	@Operation(summary = "Middle 전체 카테고리 조회")
	public CommonResponseEntity<List<MiddleCategoryResponseVo>> getMiddleCategories(
		@PathVariable String topCategoryCode) {
		{
			return new CommonResponseEntity<>(
				HttpStatus.OK,
				CommonResponseMessage.SUCCESS.getMessage(),
				categoryService.getMiddleCategories(topCategoryCode)
					.stream()
					.map(MiddleCategoryResponseDto::toVo)
					.collect(Collectors.toList()));
		}
	}

	@GetMapping("/bottom-categories/{middleCategoryCode}")
	@Operation(summary = "Bottom 전체 카테고리 조회")
	public CommonResponseEntity<List<BottomCategoryResponseVo>> getBottomCategories(
		@PathVariable String middleCategoryCode) {

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			categoryService.getBottomCategories(middleCategoryCode)
				.stream()
				.map(BottomCategoryResponseDto::toVo)
				.collect(Collectors.toList()));

	}
}
