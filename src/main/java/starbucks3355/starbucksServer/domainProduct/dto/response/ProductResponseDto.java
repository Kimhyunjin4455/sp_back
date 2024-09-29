package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainProduct.entity.Product;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductResponseVo;

@Getter
@Slf4j
@NoArgsConstructor
public class ProductResponseDto {
	private String productName;
	private String productDescription;
	private String productInfo;

	@Builder
	public ProductResponseDto(
		String productName,
		String productDescription,
		String productInfo
	) {
		this.productName = productName;
		this.productDescription = productDescription;
		this.productInfo = productInfo;
	}

	public ProductResponseVo dtoToResponseVo() {
		return ProductResponseVo.builder()
			.productName(productName)
			.productDescription(productDescription)
			.productInfo(productInfo)
			.build();
	}

	public static ProductResponseDto from(Product product) {
		log.info("ProductResponseDto 생성된 값: {}", product.getProductName());
		return ProductResponseDto.builder()
			.productName(product.getProductName())
			.productDescription(product.getProductDescription())
			.productInfo(product.getProductInfo())
			.build();
	}

}
