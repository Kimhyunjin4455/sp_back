package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductDetailsResponseVo;

@Getter
@Builder
public class ProductDetailsResponseDto {
	private Long productCode; // 상품 옵션의 id 필드의 값을 뜻함
	private Integer price;

	public ProductDetailsResponseDto(Long productCode, Integer price) {
		this.productCode = productCode;
		this.price = price;
	}

	public ProductDetailsResponseVo dtoToResponseVo() {
		return ProductDetailsResponseVo.builder()
			.productCode(productCode)
			.price(price)
			.build();
	}
}
