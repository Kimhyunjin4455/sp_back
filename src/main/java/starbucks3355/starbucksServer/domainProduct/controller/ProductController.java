package starbucks3355.starbucksServer.domainProduct.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductDetailsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductImgResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductResponseDto;
import starbucks3355.starbucksServer.domainProduct.service.ProductService;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductDetailsResponseVo;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductImgResponseVo;
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

	@GetMapping("/productImage/{productCode}")
	@Operation(summary = "상품 대표 이미지 조회")
	public CommonResponseEntity<ProductImgResponseVo> getProductImage(
		@PathVariable Long productCode) {
		// productCode는 상품의 PK(Product entity의 id값)를 지칭
		// Q. 프론트 단에서 url에 상품의id값을 지정하여 요청하는 방식으로 상품에 대한 대표 이미지 추출
		ProductImgResponseDto productImgResponseDto = productService.getImage(productCode);
		// productService.getImage: 상품id값을 기준으로 상품이미지엔티티에서 를 추출하는 로직

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			productImgResponseDto.dtoToResponseVo()
		);
	}

	@GetMapping("/productDetails/{productUuid}")
	@Operation(summary = "상품 상세정보 조회")
	public CommonResponseEntity<ProductDetailsResponseVo> getProductDetails(
		@PathVariable String productUuid) {
		ProductDetailsResponseDto productDetails = productService.getProductDetails(productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			productDetails.dtoToResponseVo()
		);
	}

	@GetMapping
	@Operation(summary = "상품 목록 조회")
	public CommonResponseEntity<List<ProductResponseVo>> getProducts() {
		List<ProductResponseDto> productResponseDtos = productService.getProducts();

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			productResponseDtos.stream()
				.map(dto -> ProductResponseVo.builder()
					.productUuid(dto.getProductUuid())
					.productName(dto.getProductName())
					.productDescription(dto.getProductDescription())
					.productInfo(dto.getProductInfo())
					.build())
				.collect(Collectors.toList())
		);
	}

}
