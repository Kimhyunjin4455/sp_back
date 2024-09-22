package starbucks3355.starbucksServer.domainProduct.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.common.entity.BaseResponse;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.common.entity.CommonResponseSliceEntity;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainProduct.dto.request.ProductRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.DiscountResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductDetailsPriceResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductsResponseDto;
import starbucks3355.starbucksServer.domainProduct.service.ProductService;
import starbucks3355.starbucksServer.domainProduct.vo.request.ProductRequestVo;
import starbucks3355.starbucksServer.domainProduct.vo.response.DiscountResponseVo;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductDetailsPriceResponseVo;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductResponseVo;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductsResponseVo;

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

	@GetMapping("/{productUuid}/productDetails")
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

	@GetMapping("/listWithPageable")
	@Operation(summary = "상품 목록 조회 (무한 스크롤 페이지 처리)")
	public CommonResponseSliceEntity<List<ProductsResponseVo>> getProducts(

		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size
	) {
		Slice<ProductsResponseDto> productResponseDtos = productService.getProducts(page, size);

		List<ProductsResponseVo> productResponseVos = productResponseDtos.stream()
			.map(ProductsResponseDto::dtoToResponseVo)
			.collect(Collectors.toList());

		return new CommonResponseSliceEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			productResponseVos,
			productResponseDtos.hasNext()
		);
	}

	@GetMapping("/listWithQueryDSL")
	@Operation(summary = "상품 목록 조회 2 (커서 페이지 처리)")
	public CommonResponseEntity<CursorPage<String>> getProducts(
		@RequestParam(value = "lastId", required = false) Long lastId,
		@RequestParam(value = "pageSize", required = false) Integer pageSize,
		@RequestParam(value = "page", required = false) Integer page
	) {
		CursorPage<String> productResponseDtos = productService.getProductList(lastId, pageSize, page);
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			productResponseDtos
		);
	}

	@GetMapping("/{productUuid}/productDiscountInfo")
	@Operation(summary = "상품 할인 정보 조회")
	public CommonResponseEntity<DiscountResponseVo> getProductRateDiscountInfo(
		@PathVariable String productUuid) {
		DiscountResponseDto discountRateResponseDto = productService.getDiscountInfo(productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			discountRateResponseDto.dtoToResponseVo()
		);
	}

	@GetMapping("/search")
	@Operation(summary = "상품 검색")
	public CommonResponseEntity<CursorPage<String>> searchProduct(
		@RequestParam String keyword,
		@RequestParam(value = "lastId", required = false) Long lastId,
		@RequestParam(value = "pageSize", required = false) Integer pageSize,
		@RequestParam(value = "page", required = false) Integer page
	) {
		CursorPage<String> productResponseDto = productService.getSearchedProductList(keyword, lastId, pageSize, page);
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			productResponseDto
		);
	}

	@PostMapping("/add")
	@Operation(summary = "상품 추가")
	public BaseResponse<Void> addProduct(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@RequestBody ProductRequestVo productRequestVo
	) {

		productService.addProduct(ProductRequestDto.of(productRequestVo));

		return new BaseResponse<>(
			BaseResponseStatus.SUCCESS
		);
	}

}
