package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductDetailsPriceResponseVo;

@Getter
@Builder
public class ProductDetailsPriceResponseDto {
	private String productUuid; // 상품에 대한 상세정보 엔티티로 접근 하기 위한 필드
	private Integer price;

	public ProductDetailsPriceResponseDto(String productUuid, Integer price) {
		this.productUuid = productUuid;
		this.price = price;
	}

	public ProductDetailsPriceResponseVo dtoToResponseVo() {
		return ProductDetailsPriceResponseVo.builder()
			.productUuid(productUuid)
			.price(price)
			.build();
	}
}
