package starbucks3355.starbucksServer.domainProduct.controller;

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
import starbucks3355.starbucksServer.domainProduct.dto.responseDto.ProductResponseDto;
import starbucks3355.starbucksServer.domainProduct.service.ProductService;
import starbucks3355.starbucksServer.domainProduct.vo.out.ProductResponseVo;

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

}
