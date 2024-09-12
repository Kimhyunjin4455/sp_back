package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductInfoResponseVo;

@Getter
@Builder
public class ProductInfoResponseDto {
	// 검색을 통해 상품의 uuid를 받기 위한 dto
	private String productUuid;

	public ProductInfoResponseVo dtoToResponseVo() {
		return ProductInfoResponseVo.builder()
			.productUuid(this.productUuid)
			.build();
	}
}
