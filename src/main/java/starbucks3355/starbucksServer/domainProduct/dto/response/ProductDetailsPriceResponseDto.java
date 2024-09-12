package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductDetailsPriceResponseVo;

@Getter
@Builder
public class ProductDetailsPriceResponseDto {
	private Integer price;

	public ProductDetailsPriceResponseDto(Integer price) {
		this.price = price;
	}

	public ProductDetailsPriceResponseVo dtoToResponseVo() {
		return ProductDetailsPriceResponseVo.builder()
			.price(price)
			.build();
	}
}
