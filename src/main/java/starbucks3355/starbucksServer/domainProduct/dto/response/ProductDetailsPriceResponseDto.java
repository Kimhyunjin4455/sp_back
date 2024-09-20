package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainProduct.entity.ProductDetails;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductDetailsPriceResponseVo;

@Getter
@NoArgsConstructor
public class ProductDetailsPriceResponseDto {
	private Integer price;

	@Builder
	public ProductDetailsPriceResponseDto(Integer price) {
		this.price = price;
	}

	public ProductDetailsPriceResponseVo dtoToResponseVo() {
		return ProductDetailsPriceResponseVo.builder()
			.price(price)
			.build();
	}

	public static ProductDetailsPriceResponseDto from(ProductDetails productDetails) {
		return ProductDetailsPriceResponseDto.builder()
			.price(productDetails.getProductPrice())
			.build();
	}
}
