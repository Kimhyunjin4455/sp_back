package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainProduct.entity.Product;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductsResponseVo;

@Getter
@NoArgsConstructor
public class ProductsResponseDto {
	private String productUuid;

	@Builder
	public ProductsResponseDto(
		String productUuid
	) {
		this.productUuid = productUuid;
	}

	public ProductsResponseVo dtoToResponseVo() {
		return ProductsResponseVo.builder()
			.productUuid(productUuid)
			.build();
	}

	public static ProductsResponseDto from(Product product) {
		return ProductsResponseDto.builder()
			.productUuid(product.getProductUuid())
			.build();
	}

}
