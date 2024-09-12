package starbucks3355.starbucksServer.domainProduct.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.common.entity.CommonResponseSliceEntity;
import starbucks3355.starbucksServer.domainProduct.dto.response.DiscountPriceResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.DiscountRateResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductDetailsPriceResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductInfoResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductResponseDto;
import starbucks3355.starbucksServer.domainProduct.service.ProductService;
import starbucks3355.starbucksServer.domainProduct.vo.response.DiscountPriceResponseVo;
import starbucks3355.starbucksServer.domainProduct.vo.response.DiscountRateResponseVo;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductDetailsPriceResponseVo;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductInfoResponseVo;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
@Tag(name = "Product", description = "상품 API")
public class ProductController {
	private final ProductService productService;

	@GetMapping("/{productUuid}")
	@Operation(summary = "상품 조회")
	public CommonResponseEntity<ProductResponseVo> getProduct(
		@PathVariable String productUuid) {
		ProductResponseDto productResponseDto = productService.getProduct(productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			productResponseDto.dtoToResponseVo()
		);
	}

	@GetMapping("/productDetails/{productUuid}")
	@Operation(summary = "상품 가격만 조회")
	public CommonResponseEntity<ProductDetailsPriceResponseVo> getProductDetails(
		@PathVariable String productUuid) {
		ProductDetailsPriceResponseDto productDetails = productService.getProductPrice(productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			productDetails.dtoToResponseVo()
		);
	}

	@GetMapping
	@Operation(summary = "상품 목록 조회 (무한 스크롤 페이지 처리)")
	public CommonResponseSliceEntity<List<ProductResponseVo>> getProducts(

		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size
	) {
		Slice<ProductResponseDto> productResponseDtos = productService.getProducts(page, size);

		List<ProductResponseVo> productResponseVos = productResponseDtos.stream()
			.map(ProductResponseDto::dtoToResponseVo)
			.collect(Collectors.toList());

		return new CommonResponseSliceEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			productResponseVos,
			productResponseDtos.hasNext()
		);
	}

	@GetMapping("/productDiscountRateInfo/{productUuid}")
	@Operation(summary = "상품 할인률 정보 조회")
	public CommonResponseEntity<DiscountRateResponseVo> getProductRateDiscountInfo(
		@PathVariable String productUuid) {
		DiscountRateResponseDto discountRateResponseDto = productService.getDiscountRateInfo(productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			discountRateResponseDto.dtoToResponseVo()
		);
	}

	@GetMapping("/productDiscountPriceInfo/{productUuid}")
	@Operation(summary = "상품 할인금액 정보 조회")
	public CommonResponseEntity<DiscountPriceResponseVo> getProductPriceDiscountInfo(
		@PathVariable String productUuid) {
		DiscountPriceResponseDto discountPriceResponseDto = productService.getDiscountPriceInfo(productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			discountPriceResponseDto.dtoToResponseVo()
		);
	}

	@GetMapping("/search/{searchInfo}")
	@Operation(summary = "상품 검색을 통힌 정보 조회")
	public CommonResponseEntity<List<ProductInfoResponseVo>> getProductSearchInfo(
		@PathVariable String searchInfo
	) {
		List<ProductInfoResponseDto> productsInfoDtoList = productService.getProductsInfo(searchInfo);

		List<ProductInfoResponseVo> productsInfoVoList = productsInfoDtoList.stream()
			.map(ProductInfoResponseDto::dtoToResponseVo)
			.collect(Collectors.toList());

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			productsInfoVoList
		);
	}

}
