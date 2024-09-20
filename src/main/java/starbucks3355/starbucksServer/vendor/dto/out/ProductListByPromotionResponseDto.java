package starbucks3355.starbucksServer.vendor.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.vendor.vo.out.ProductListByPromotionResponseVo;

@Getter
@NoArgsConstructor
public class ProductListByPromotionResponseDto {
	private String productUuid;

	@Builder
	public ProductListByPromotionResponseDto(String productUuid) {
		this.productUuid = productUuid;
	}

	// dto -> vo
	public ProductListByPromotionResponseVo dtoToResponseVo() {
		return ProductListByPromotionResponseVo.builder()
			.productUuid(productUuid)
			.build();
	}

}
