package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductDetailsResponseVo;

@Getter
@Builder
public class ProductDetailsResponseDto {
	private String productUuid; // 상품에 대한 상세정보 엔티티로 접근 하기 위한 필드
	private Integer price;

	public ProductDetailsResponseDto(String productUuid, Integer price) {
		this.productUuid = productUuid;
		this.price = price;
	}

	public ProductDetailsResponseVo dtoToResponseVo() {
		return ProductDetailsResponseVo.builder()
			.productUuid(productUuid)
			.price(price)
			.build();
	}
}
